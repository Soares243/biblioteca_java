# Biblioteca Java

Sistema de biblioteca em Java com menus no terminal, JPA/Hibernate e PostgreSQL.

## Integrantes

Luiz Miguel Mazzega Lamas, Ester Soares Serafim

## Banco de dados

Configuracao padrao:

- Host: `localhost`
- Porta: `5432`
- Banco: `biblioteca_java`
- Usuario: `postgres`
- Senha: `950915`

O Hibernate esta configurado com `hibernate.hbm2ddl.auto=update`, entao as tabelas sao criadas/atualizadas automaticamente quando o sistema inicia.

Se o banco ainda nao existir, crie antes de rodar:

```powershell
psql -h localhost -p 5432 -U postgres -c "CREATE DATABASE biblioteca_java;"
```

## Rodar pelo terminal

```powershell
mvn clean compile
mvn exec:java
```

Se o comando `mvn` nao for reconhecido, instale/configure o Apache Maven ou rode os mesmos comandos pelo Maven da IDE.

Ao iniciar, o sistema testa a conexao com o PostgreSQL, prepara as tabelas e abre o menu principal.

## Fluxo sugerido para demonstracao

1. Cadastrar usuario.
2. Cadastrar autor.
3. Cadastrar categoria.
4. Cadastrar livro e exemplar.
5. Fazer emprestimo.
6. Listar relatorios/reservas/multas conforme os dados cadastrados.
