# E-commerce Application 
  -> DESAFIO 03 – Desenvolvendo aplicações para um eCommerce

Este projeto é uma aplicação E-commerce construída usando Java 17 e Spring Boot 3.3.1. Ele suporta gerenciamento de produtos e vendas, com uma arquitetura RESTful.

## Tecnologias Utilizadas

- **Java 17**: Linguagem de programação.
- **Spring Boot 3.3.1**: Framework para facilitar a configuração e o desenvolvimento de aplicações.
- **MySQL**: Sistema de gerenciamento de banco de dados relacional.
- **Swagger**: Ferramenta para documentação de APIs REST.
- Este projeto segue uma **Arquitetura em Camadas** e é construído seguindo os princípios **RESTful**
  para organizar a aplicação de forma eficiente e facilitar a manutenção.


## Configuração do Ambiente

Para configurar e executar este projeto, você precisará ter o Java 17 instalado em seu ambiente. 
Além disso, é necessário configurar um banco de dados MySQL.

### Diagrama ER 

![image](https://github.com/Ellenrms/E-commerce-challenge/assets/96744488/bf871669-4b75-4452-9d79-23d776dc13d6)


### Passos para Configuração

Configurar o Banco de Dados

Crie um banco de dados no MySQL.

Atualize o arquivo src/main/resources/application.properties 
com as credenciais do seu banco de dados.

''
spring.datasource.url=jdbc:mysql://localhost:3306/seu_banco_de_dados
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
''


### Utilização da API
Uma vez que a aplicação esteja "rodando", você pode acessar a documentação da API e testar os endpoints através 
do Swagger acessando:

http://localhost:8080/swagger-ui.html

### Funcionalidades

Gerenciamento de Produtos: Permite realizar operações CRUD (Create, Read, Update, Delete) em produtos.
Gerenciamento de Vendas: Permite realizar operações CRUD em vendas, além de gerar relatórios de vendas.
Autenticação e Autorização: Implementação de segurança com JWT para proteger as APIs e controlar o acesso.
Envio de E-mails: Funcionalidade para enviar e-mails de recuperação de senha.

###Endpoints Principais

##Produtos:
GET /api/products - Listar todos os produtos.
POST /api/products - Adicionar um novo produto.
PUT /api/products/{id} - Atualizar um produto existente.
DELETE /api/products/{id} - Excluir um produto.

##Vendas:
GET /api/sales - Listar todas as vendas.
POST /api/sales - Criar uma nova venda.
PUT /api/sales/{id} - Atualizar uma venda existente.
DELETE /api/sales/{id} - Excluir uma venda.

##Autenticação:
POST /auth/register - Registrar um novo usuário.
POST /auth/login - Autenticar um usuário e gerar um token JWT.

##Principais Anotações Utilizadas
@RestController: Indica que a classe é um controlador REST.
@RequestMapping: Mapeia requisições HTTP para métodos específicos.
@PostMapping, @GetMapping, @PutMapping, @DeleteMapping: Mapeiam métodos HTTP POST, GET, PUT e DELETE, respectivamente.
@Valid: Indica que o objeto deve ser validado.
@Autowired: Injeta dependências automaticamente.
@Service: Indica que a classe é um serviço.
@Repository: Indica que a classe é um repositório, responsável por operações de acesso a dados.
@Entity: Indica que a classe é uma entidade JPA.



##Autora
Ellen Mateus 
