# Codewars

[![Java CI with Maven](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml)
[![CodeQL](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml)

Набор решений алгоритмических задач разного уровня сложности с https://www.codewars.com/kata/ на Java по методу TDD.

Профиль: https://www.codewars.com/users/krotname

## Запуск

В IntelliJ IDEA можно нажать правой кнопкой на `src/main/java` и выбрать `Run 'All Tests'`.

Через Maven:

```bash
mvn test
```

Для полной проверки CI используется:

```bash
mvn -B package --file pom.xml
```
