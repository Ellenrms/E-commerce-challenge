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

### Executar o Projeto

Execute o projeto através do seu ambiente de desenvolvimento integrado (IDE) ou através do comando java -jar 
se estiver usando um arquivo JAR executável.


### Utilização da API
Uma vez que a aplicação esteja rodando, você pode acessar a documentação da API e testar os endpoints através 
do Swagger acessando:

http://localhost:8080/swagger-ui.html

### Funcionalidades
Gerenciamento de Produtos: Permite realizar operações CRUD em produtos.
Gerenciamento de Vendas: Permite realizar operações CRUD em vendas, além de gerar relatórios de vendas.
Contribuições
Contribuições são sempre bem-vindas. Para contribuir, por favor, crie um fork do repositório, faça suas alterações, e envie um pull request.



Autora
Ellen Mateus 
