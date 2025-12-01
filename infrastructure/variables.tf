variable "db_username" {
  description = "username"
  type        = string
  sensitive   = true # Hides the value from logs
}

variable "db_password" {
  description = "password"
  type        = string
  sensitive   = true
}