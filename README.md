# empresa-area
API Rest para representar os voos de uma empresa aérea com seus respectivos horários.


# **Pipeline**

Para CI apesar de utilizar branch com master, poderia adotar o [gitflow](https://marketplace.atlassian.com/apps/1212520/git-flow-chart?hosting=cloud&tab=overview "gitflow"). Para CD este projeto possui  um dockerfile para utilizar com EC2 e compose para orquestração, a configuração do log esta sendo gerado no console e arquivo configurados no logback.xml.

# Swagger

Para acessar as api, podera utilizar o swagger em http://<host>:8089/empresa-aerea/swagger-ui.html


# DataSource

As configuração de banco podem-ser feita no application.yml




