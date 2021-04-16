# Lanchonete

## Melhorias
- Tratamento de erros
- Implementar o docker (Docker da aplicação e Docker do Banco de Dados)

## Instruções Banco de Dados
- Precisa do MySQL instalado e nele uma base de dados com nome "lanchonete".
- Credenciais configuradas para o MySQL: "Username: root", "Password: root".

## Instruções Segurança
- A segurança foi implementada para funcionar com o uso de um token Bearer.
- Antes de fazer qualquer requisição, devemos estar autenticados.
### Para realizar a autenticação: 
- 1 - Entre no endereço http://localhost:8080/swagger-ui.html
- 2 - Clique em "jwt-autenticacao-controller" e em seguida em "POST /authenticate"
- 3 - No campo authenticationRequest, entre com o Json abaixo e clique no botão "Try id out!" 
  {
    "password": "password",
    "username": "user"
  }
- 4 - Copie o token gerado no campo Response Body 
  {
   "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjE4NjAwNjc2LCJpYXQiOjE2MTg1ODI2NzZ9.8-qHfrRr3JjODu2QkYeVAeA0OtHIkEKWuMaGP0gFSYzwXodraRuS9pfD_VpT2O1vzd3Xh2Nysp_ccvSPGzFbIw"
  }
- 5 - Clique no botão Authorize no topo da pagina e entre com o token copiado e adicione a palavra Bearer no inicio, conforme o exemplo abaixo
  "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ1c2VyIiwiZXhwIjoxNjE4NjAwNjc2LCJpYXQiOjE2MTg1ODI2NzZ9.8-qHfrRr3JjODu2QkYeVAeA0OtHIkEKWuMaGP0gFSYzwXodraRuS9pfD_VpT2O1vzd3Xh2Nysp_ccvSPGzFbIw"
- 6 - Clique no Botão Authorize e pronto, ja pode usar os endpoints documentados no Swagger.
- Obs: no caso de uso do Postman, colocamos "Bearer Token" no campo de Authorization e entramos com o token sem a palavra Bearer no inicio.


## Instruções Gerais
Projeto rodando na porta padrão (8080).

## Exemplos de requests
### GET
- Endpoint: localhost:8080/ingredientes
- Endpoint: localhost:8080/lanches
- Endpoint: localhost:8080/pedidos

### POST
#### Ingredientes
- Endpoint: localhost:8080/ingredientes/save

- Json: {"nome":"Alface","preco":0.40}

- Json: {"nome":"Bacon","preco":2.00}

- Json: {"nome":"Hamburger","preco":3.00}

- Json: {"nome":"Ovo","preco":0.80}

- Json: {"nome":"Queijo","preco":1.50}

#### Lanches
- Endpoint: localhost:8080/lanches/save
- Json: 
{
    "nome": "X-Bacon",
    "ingredientesDTO":[{"id":2,"nome":"Bacon", "quantidade":1},{"id":3,"nome":"Hamburger", "quantidade":1},{"id":5,"nome":"Queijo", "quantidade":1}]
}

- Json: 
{
    "nome": "X-Burger",
    "ingredientesDTO":[{"id":3,"nome":"Hamburger", "quantidade":1},{"id":5,"nome":"Queijo", "quantidade":1}]
}

- Json: 
{
    "nome": "X-Egg",
    "ingredientesDTO":[{"id":3,"nome":"Hamburger", "quantidade":1},{"id":4,"nome":"Ovo", "quantidade":1},{"id":5,"nome":"Queijo", "quantidade":1}]
}

- Json: 
{
    "nome": "X-Egg Bacon",
    "ingredientesDTO":[{"id":3,"nome":"Hamburger", "quantidade":1},{"id":4,"nome":"Ovo", "quantidade":1},{"id":5,"nome":"Queijo", "quantidade":1}, {"id":"2", "nome":"Bacon", "quantidade":1}]
}

- Json: 
{
    "nome": "X-Personalizado",
    "ingredientesDTO":[
        {"id":2,"nome":"Bacon","quantidade":"1"},
        {"id":3,"nome":"Hamburger", "quantidade":"2"},
        {"id":5,"nome":"Queijo","quantidade":"1"}]
}

- Json: 
{
    "nome": "X-Personalizado-Light",
    "ingredientesDTO":[
        {"id":1,"nome":"Alface","quantidade":1},
        {"id":3,"nome":"Hamburger", "quantidade":1},
        {"id":5,"nome":"Queijo","quantidade":1}]
}

- Json: 
{
    "nome": "X-Personalizado-MuitaCarne",
    "ingredientesDTO":[
        {"id":2,"nome":"Bacon","quantidade":1},
        {"id":3,"nome":"Hamburger", "quantidade":6},
        {"id":5,"nome":"Queijo","quantidade":1}]
}

- Json: 
{
    "nome": "X-Personalizado-MuitoQueijo",
    "ingredientesDTO":[
        {"id":2,"nome":"Bacon","quantidade":1},
        {"id":3,"nome":"Hamburger", "quantidade":1},
        {"id":5,"nome":"Queijo","quantidade":3}]
}

### Pedidos
- Endpoint: localhost:8080/pedidos/save
- Json: 
{
    "id": 1,
    "lanches":[
    {"id":1,"nome":"X-Bacon"},
    {"id":2,"nome":"X-Burger"},
    {"id":3,"nome":"X-Egg"},
    {"id":4,"nome":"X-Egg Bacon"},
    {"id":5,"nome":"X-Personalizado"},
    {"id":6,"nome":"X-Personalizado Light"},
    {"id":7,"nome":"X-Personalizado Muita Carne"},
    {"id":8,"nome":"X-Personalizado Muito Queijo"}]
}
