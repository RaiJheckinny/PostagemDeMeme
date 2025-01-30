# Meme API

Este projeto é uma API para gerenciar memes, permitindo criar, buscar, listar, atualizar e excluir memes.

## Tecnologias Utilizadas

- Java
- Spring Boot
- Swagger para documentação
- H2

## Endpoints

### Criar um Meme

**POST** `/api/meme/create`

- Cria um novo meme.
- Parâmetros: Title, Autor, Descrição e URL do meme.

### Buscar Memes por Título

**GET** `/api/meme/search/{title}`

- Retorna uma lista de memes com base no título informado.

### Listar Todos os Memes

**GET** `/api/meme/getall`

- Retorna todos os memes cadastrados.

### Obter um Meme Aleatório

**GET** `/api/meme/randommeme`

- Retorna um meme aleatório.

### Deletar um Meme

**DELETE** `/api/meme/delete/{id}`

- Remove um meme pelo ID.

### Atualizar um Meme

**PUT** `/api/meme/update/{id}`

- Atualiza as informações de um meme existente.

## Como Executar

1. Clone o repositório.
2. Configure o ambiente Java e Spring Boot.
3. Execute o projeto.
4. Acesse a documentação no Swagger: `/swagger-ui.html`

## Contribuição

Sinta-se à vontade para contribuir com melhorias! Abra uma issue ou faça um pull request.

