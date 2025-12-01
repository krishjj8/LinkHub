# Output the address (endpoint) of the RDS database
output "db_endpoint" {
  description = "The connection endpoint for the RDS database"
  value       = aws_db_instance.main.endpoint
}

# Output the port number
output "db_port" {
  description = "The port the database is listening on"
  value       = aws_db_instance.main.port
}