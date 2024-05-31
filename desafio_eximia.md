# Desafio Eximia
Temos um cliente que precisa de uma plataforma web onde as pessoas poderão consultar dados de pokemons. Para consultar um pokemon, a pessoa deverá estar cadastrada na plataforma.

### 💾 API:
A API deverá buscar um Pokemon por nome através da API [PokeAPI](https://pokeapi.co). A busca deverá retornar o nome do Pokemon, uma sprite dele 
de frente e suas habilidades. O retorno deve ser semelhante ao seguinte:
```json
{
  "name": "Ditto" ,
  "sprite": "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/132.png",
  "abilities": [
    {
      "name": "limber"
    },
    {
      "name": "imposter"
    }
  ]
}
```

A API deverá possuir um sistema de autenticação de usuário contendo dois tipos de usuários: `ADMIN` e `DEFAULT`. 

🔐 Usuários do tipo `ADMIN` poderão:
- Listar usuários
- Editar usuário
- Deletar usuário
- Criar usuário
- Consultar Pokemons

🔐 Usuários do tipo `DEFAULT` poderão:
- Editar usuário
- Deletar usuário
- Consultar Pokemons
- Criar usuário

🙅 O que não pode ocorrer:

- Um usuário `DEFAULT` não pode ser capaz de deletar outro usuário que não seja ele mesmo.
- Um usuário `DEFAULT` não pode ser capaz de editar outro usuário que não seja ele mesmo.
- Uma conta não poderá ser criada caso já exista um usuário com o mesmo nome.
- A senha de um usuário não pode aparecer na listagem.
- Usuários não logados não deverão ter acesso a nada, exceto, login e criar conta.

👔 Regras de negócio:
- A senha deverá ser criptografada.
- A senha deverá ter no minimo 6 caracteres, uma letra maiuscula, uma minuscula e um numero.

### 🌐 Front
O front pode ser bem simples, use sua criatividade para montar as telas.

💻 Telas:
```
Login: {
  Logar,
  Criar Conta
}

Admin Dashboard: {
  Listar usuarios,
  Editar usuario,
  Deletar usuario
}

Home: {
  Pesquisar pokemon,
  Exibir resultados da busca,
  Exibir algo indicando que o Pokemon nao foi encontrado, caso ocorra
}
```

👔 Regras de negócio:
- Um usuário `DEFAULT` ao tentar acessar a rota de Admin, deverá se deparar com uma tela de "Voce nao tem permissao para isso"
- A opçao de menu Admin não deverá ser exibida para usuarios `DEFAULT`
- Ao tentar acessar uma URL não existente, deverá se deparar com uma tela de "Pagina não encontrada"

## 🖥️ Tecnologias
Para este desafio, na parte de API, deverá ser utilizado Java Spring Boot ou NestJS. No front, deverá ser utilizado NextJS.
