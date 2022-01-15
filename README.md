## Execução do programa

É necessário possuir o maven e o java 11 instalados na máquina.

Basta utilizar o comando **mvn springboot:run** na raiz do projeto.

## End points da aplicação

- */api/conta* **POST** -> requisição para criar uma conta, é necessário, enviar um *JSON* com os campos **nome** e **cpf**, ambos strings e o CPF precisa ser válido

Exemplo de requisição:

```
{
    "nome" : "teste", 
    "cpf" : "86113283020"
}

```

Obs: o CPF desta requisição foi gerado aleatoriamente.

- */api/conta* **GET** -> retorna todas as contas.
- */api/conta/{id}* **GET** -> retorna a conta de acordo com o id especificado.
- */api/conta/{id}* **PUT** -> requisição que possibilita alterar o nome ou cpf de uma conta existente, utiliza o mesmo *body* da requisição POST.
- */api/conta/{id}* **DELETE** -> Exclui uma conta de acordo com o id especificado
- */api/conta/depositar/{id}* **PATCH** -> Faz o depósito na conta identificada pelo *id*, o valor não pode ser maior que 2000.

Exemplo de requisição: 
```
{
    "valor" : 1000.20
}
```

- */api/conta/transferir/{idOrigem}* **PATCH** -> transfere saldo de uma conta para outra, não é possível ficar com saldo negativo.

Exemplo de requisição:

```
{
    "idDestino" : 2,
    "valor" : 1000
}
```