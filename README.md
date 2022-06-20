<p align="center"> 💻 Atualizado em 20 de Junho de 2022 💻</p>

<h1 align="center"> 📒 Shop Style 📒</h1>

<p align="center">
  <img alt="GitHub language count" src="https://img.shields.io/github/languages/count/gabrielcoelhox/Shop-Style">

  <img alt="Repository size" src="https://img.shields.io/github/repo-size/gabrielcoelhox/Shop-Style">

  <a href="https://github.com/gabrielcoelhox/course-angular-java/commits/main">
    <img alt="GitHub last commit" src="https://img.shields.io/github/last-commit/gabrielcoelhox/Shop-Style">
  </a>
</p>

[O Projeto](#id1)&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
[MS Customer](#id2)&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp; 
[MS Catalog](#id3)
  
# <a id="id1"> 💻 O Projeto </a>

O Shop Style é uma loja física que vende roupas de todos os tipos e estilos. Os fundadores do Shop Style desejam agora abrir uma loja virtual e contrataram uma equipe para implementar. Os arquitetos já desenharam a solução e agora cabe a você implementar essa solução. O projeto usará uma arquitetura de micro-serviços. Foi definindo a criação de cinco micro-serviços: customer, catalog, checkout, history e o bff-shop. Todos os microserviços devem ter testes unitários com cobertura de pelo menos 80%.

# <a id="id2"> 👨‍💼 MS Customer
  
O MS customer tem a responsabilidade de armazenar e gerenciar os dados de usuário. </br> 
O MS customer possui os seguintes endpoints:

<details>
<summary><strong>Ver mais</strong></summary>

```bash
# POST - /v1/login
# POST - /v1/users
# GET - /v1/users/:id
# PUT - /v1/users/:id
```
</details> 

Exemplo de um payload para cadastrar um usuário:

<details>
<summary><strong>Ver mais</strong></summary>

```bash
{
"firstName": "Maria",
"lastName": "Oliveira",
"sex": "Feminino",
"cpf": "000.000.000-00",
"birthdate": "00/00/0000",
"email": "maria@email.com",
"password": "12345678",
"active": true
}
```
</details> 
  
### ☑️ Validações necessárias

- Os campos firstName e lastName precisam ter no mínimo 3 caracteres.
- O campo sex só pode ter duas opções disponíveis Masculino e Feminino, caso
- contrário informar um erro ao usuário.
- O campo email precisa estar no formato de um email válido.
- O campo cpf precisa seguir o seguinte padrão (xxx-xxx-xxx-xx).
- O campo password precisa ter no mínimo 8 caracteres.
- O campo birthdate precisa ser salvo no formato brasileiro de datas (00/00/0000).

### ❗ Observações

- A senha deve estar encriptografada.
- Não deve permitir emails duplicados.
- O login será feito a partir do email e senha.
- Usar o PostgreSQL.

# <a id="id3"> 🔖 MS Catalog

O MS catalog é o responsável por armazenar os produtos e categorias que vão estar disponíveis na aplicação. Um produto tem uma ou mais variações, as variações têm os atributos cor, tamanho, preço e quantidade. O produto também está vinculado a uma ou mais categorias e uma categoria pode ter zero ou mais produtos. O MS catalog possui os seguintes endpoints:
  
<details>
<summary><strong>Ver mais</strong></summary>

```bash
# POST - /v1/products
# GET - /v1/products
# GET - /v1/products/:id
# PUT - /v1/products/:id
# DELETE - /v1/products/:id
  
# POST - /v1/variations
# PUT - /v1/variations/:id
# DELETE - /v1/variations/:id
  
# POST - /v1/categories
# GET - /v1/categories
# GET - /v1/categories/:id/products
# PUT - /v1/categories/:id
# DELETE - /v1/categories/:id
```
</details> 
  
Exemplo de um payload para cadastrar um produto:
  
<details>
<summary><strong>Ver mais</strong></summary>

```bash
{
"name": "Camisa Oficial do Flamengo",
"description": "A camisa pra você que é rubro negro de coração",
"active": true,
"category_ids": [1,2,3]
}
```
</details> 
  
Exemplo de um payload para cadastrar uma variação de um produto:
  
<details>
<summary><strong>Ver mais</strong></summary>

```bash
{
"color": "tricolor",
"size": "M",
"price": 249.99,
"quantity": 10,
"product_id": 1
}
```
</details>
  
Exemplo de um payload para cadastrar uma categoria:
  
<details>
<summary><strong>Ver mais</strong></summary>

```bash
{
"name": "Camisas de Futebol",
"active": true
}
```
</details>
  
### ☑️ Validações necessárias
  
- Os campos name, description, active e category_id são obrigatórios para salvar um produto.
- As categorias têm que estar ativa para um produto ser salvo.
- Todos os campos são necessários para cadastrar uma variação e categoria.
  
### ❗ Observações

- Usar o MongoDB.
- O Get de produtos deve retonar além das informações do produto, todas as suas variações.