# teste-attus
Projeto para teste técnico da empresa Attus - Procuradoria Digital

Pontos de melhoria: 
    - Adicionar keycloak no api gateway
    - Adicionar cache para a verificação das partes envolvidas
    - Adicionar kafka para distribuir os eventos de criação
    - Em produção o banco de dados não compartilharia a mesma conexão, cada um seria um container separado
    - Implementar um circuit breaker
    - Adicionar docker e usar o compose para orquestração

Como rodar o projeto:

Tecnologias necessárias:
 - Java versão 21 (com o plugin Lombok)
 - Maven 3
 - Docker

Rodando o projeto localmente:

Primeiro acesse o diretório raiz do repositório "teste-attus" e rode o comando:
docker compose up -d

Esse passo irá iniciar os containeres do pgadmin, pgsql e zipkin

Para rodar os serviços acesse a pasta raiz de cada projeto e execute o comando "mvn spring-boot:run"
Caso ocorra algum erro de dependência, pode ser executado "mvn clean install" e depois executar o run novamente
Rode os microsserviços na seguinte ordem:
    1 - config-server
    2 - discovery
    3 - contrato
    4 - parte_envolvida
    5 - gateway

O zipkin pode ser acessado na porta 9411

O swagger pode ser acessado nas portas:
  - 8070 (API de gerenciamento do contrato)
  - 8090 (API de gerenciamento das partes envolvidas)