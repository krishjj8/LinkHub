

# 1. Security Group for our future Web Load Balancer
resource "aws_security_group" "web" {
  name        = "linkhub-web-sg"
  description = "Allow inbound HTTP and HTTPS traffic"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port   = 80
    to_port     = 80
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  ingress {
    from_port   = 443
    to_port     = 443
    protocol    = "tcp"
    cidr_blocks = ["0.0.0.0/0"]
  }

  # Allow all outbound traffic
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "linkhub-web-sg"
  }
}

# 2. Security Group for our Application Servers (link-service)

resource "aws_security_group" "app" {
  name        = "linkhub-app-sg"
  description = "Allow inbound traffic from the web security group"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = 8080
    to_port         = 8080
    protocol        = "tcp"
    security_groups = [aws_security_group.web.id]
  }

  # Allow all outbound traffic
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "linkhub-app-sg"
  }
}

# 3. Security Group for our Database
resource "aws_security_group" "db" {
  name        = "linkhub-db-sg"
  description = "Allow inbound traffic from the app security group"
  vpc_id      = aws_vpc.main.id

  ingress {
    from_port       = 5432
    to_port         = 5432
    protocol        = "tcp"
    security_groups = [aws_security_group.app.id] #
  }

  # Allow all outbound traffic (can be restricted further in high-security environments)
  egress {
    from_port   = 0
    to_port     = 0
    protocol    = "-1"
    cidr_blocks = ["0.0.0.0/0"]
  }

  tags = {
    Name = "linkhub-db-sg"
  }
}