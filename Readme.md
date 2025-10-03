BASH 

curl -s http://localhost:8080/api/users visualizar todos
curl -X POST http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name":"dANIEL PERICLES DO NASCIMENTO","email":"dani@gmail.com"}' criar
curl -s http://localhost:8080/api/users/1 get por ID

$ curl -X PUT http://localhost:8080/api/users -H "Content-Type: application/json" -d '{"name":"Cristina da Silva","email":"Maria@gmail.com"}' atualizar


$ curl -X DELETE http://localhost:8080/api/users/5 -H "Content-Type: application/json" -d '{"name":"Cristina da Silva","email":"Maria@gmail.com"}' apagar
