#processo seletivo dti digital 
API criada para atender o desafio proposto pelo recrutamento da DTI Digital.

###Tecnologias Adotadas
- [x] Java 8 (*1.8.0_191*)
- [x] Apache Maven
- [x] Spring Boot Framework
- [x] JPA
- [x] Hibernate
- [x] Lombok
- [x] H2 Database

###Configuração do Backend
Este projeto foi desenvolvido utilizando a arquitetura Spring e conta com apenas um módulo para seu completo funcionamento.

Antes de subir a aplicação, é necessário executar o comando abaixo dentro da pasta **jogodavelha**:

>$ mvn clean install -U

Este comando irá compilar o módulo, executar todos os testes do mesmo.

Para iniciar a aplicação fora da IDE, basta executar o comando abaixo dentro da pasta **jogodavelha**:

>./mvnw

Após iniciar a aplicação, a API já estará pronta para uso, basta apenas utilizar uma ferramenta que teste os serviços RESTful.

*Foi adotado como padrão a porta **8080** e o prefixo **jogo-da-velha***:
>localhost:8080/jogo-da-velha

#####URL's
>POST /game

>POST /game/{id}/movement

>GET /game/show-atual/{id}


###Considerações
Foi utilizado o banco de dados H2 (memory) para que fosse feita a persistência dos dados na aplicação, toda vez que a
aplicação é iniciada, o banco é re-criado.
O script responsável pela criação das tabelas é o: **data.sql**.

Para salvar os jogos efetutados, ou seja, a matriz utilizada para montar o jogo da velha foi utilizada a baseada em arquivos, 
gerando um arquivo com o UUID da partida e os dados da sua matriz no caminho:
>src/main/resources/jogos/

Sendo assim, ao iniciar um novo jogo é salvo no banco os dados referentes a partida, como seu UUID, data da criação, jogador
que irá começar, jogador atual e qual o arquivo que representa aquela partida.
A medida em que as jogadas são feitas, é feita uma validação para checar se partida já não está concluída, se é
realmente o turno do jogador, e se a partida realmente existe. Caso passe em todos esses cenários, é persistida a jogada, atualizando
o arquivo que guarda os dados daquela partida, e alterando a vez de jogar.

###Implementações Adicionais
Além do preposto pela prova, eu optei em incluir alguns novos cenários que no meu entidimento poderia agregar valor a aplicação.
São eles:
- [x] Foi incluído no modelo de dados colunas para guardar o ínicio e o fim de uma partida, para que, se no futuro for necessário checar a duração
de uma partida é possível.
- [x] Também foi incluído uma flag para guardar o status da partida, se ela foi finalizada ou não, para que não seja possível alterar dados de uma partida já finalizada.
- [x] Para as mensagens de retorno eu optei por criar uma classe genérica de mensagens, visando o clean-code, que contém os atributos *msg* e *winner*, cobrindo 
os retornos (não criei o atributo *status*, pois não exerguei a real necessidade do mesmo, já que, ele possuía um mesmo padrão da *msg*).
- [x] Toda a regra responsável pelas ações do tabuleiro (checar vitória, checar colunas, linhas, diagonais e velha) foram criadas numa classe a parte fora da camada de implementação 
do serviço. Utilizando a lógica de posições 1 e -1 para checar os vencedores. 
- [x] Foi criado um endpoint para que ao ser chamado passando o UUID da partida, seja retornado no console a condição atual do tabuleiro, seguindo o padrão 
enviado na prova.

#####Dica
É possivel acessar o banco em memória acessando: 
>**localhost:8080/jogo-da-velha/h2**
 
informando o login e senha (bancomemoria)
com isso é possível observar os dados criados, observar a estrutura de dados, e se necessário, incluir/editar os dados na mão.

