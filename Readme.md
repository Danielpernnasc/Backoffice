ADMIN_TOKEN=$(curl -s -X POST http://localhost:8080/auth/login \
  -H "Content-Type: application/json" \
  -d '{"email":"admin@email.com","senha":"123456"}' | jq -r '.token')
echo "$ADMIN_TOKEN"

