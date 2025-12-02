##Kubernetes

## firewall
resource "aws_security_group" "k3s_node" {
  name="linkhub-k3s-node-sg"
  description="Security group for k3s node"
  vpc_id= aws_vpc.main.id

  # allow SSH
  ingress{
    description = "SSH"
    from_port = 22
    to_port = 22
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # allow HTTP traffic
  ingress{
    description = "HTTP"
    from_port = 80
    to_port = 80
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]

  }
  #allow HTTP port 443
  ingress {
    description = "HTTPS"
    from_port = 443
    to_port = 443
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  #allow Kubernetes API
  ingress {
    description = "Kubernetes API"
    from_port = 6443
    to_port = 6443
    protocol = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }
  #allow outbound traffic
  egress {
    from_port = 0
    to_port = 0
    protocol = "-1"
    cidr_blocks = ["0.0.0.0/0"]

  }
  tags = {
    Name="linkhub-k3s-node-sg"
  }
}

# --- EC2 INSTANCE (The Server) ---
resource "aws_instance" "k3s_server" {

  ami           = "ami-00bb6a80f01f03502"
  instance_type = "t2.micro" # Free Tier eligible!

  # Networking
  subnet_id                   = aws_subnet.public_a.id # Must be in a PUBLIC subnet to be reachable
  vpc_security_group_ids      = [aws_security_group.k3s_node.id]
  associate_public_ip_address = true # Give it a public IP so we can reach it

  # The SSH Key we created in the AWS Console
  key_name = "linkhub-key"

  tags = {
    Name = "linkhub-k3s-server"
  }
}