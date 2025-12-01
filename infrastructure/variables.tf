variable "db_username" {
  description = "linkhub"
  type        = string
  sensitive   = true # Hides the value from logs
}

variable "db_password" {
  description = "krish080106miggi"
  type        = string
  sensitive   = true
}