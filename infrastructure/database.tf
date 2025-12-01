#DATABASE SUBNET GROUP

resource "aws_db_subnet_group" "main" {
  name="linkhub-db-subnet-group"
  subnet_ids=[aws_subnet.private_a.id, aws_subnet.private_b.id]

  tags={
    Name="linkhub-db-subnet-group"
  }
}

#RDS INSTANCE
resource "aws_db_instance" "main" {
  identifier        = "linkhub-db-prod"
  engine            = "postgres"
  engine_version    = "16"  # Using a stable, modern Postgres version
  instance_class    = "db.t3.micro" # Included in Free Tier!
  allocated_storage = 20            # 20 GB is included in Free Tier

  db_name  = "linkhub"
  username = var.db_username
  password = var.db_password

  # Networking
  db_subnet_group_name   = aws_db_subnet_group.main.name
  vpc_security_group_ids = [aws_security_group.db.id] # Connects to our "DB Bouncer" firewall
  publicly_accessible    = false # CRITICAL: Never let the internet touch the DB directly!
  skip_final_snapshot    = true  # For dev/learning only (prevents hanging on destroy)

  tags = {
    Name = "linkhub-rds"
  }
}