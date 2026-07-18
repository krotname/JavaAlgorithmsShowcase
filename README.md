# Java Algorithms & Reliability Showcase

[English version](README.en.md)

Curated Java reference implementations for deterministic algorithms and stateful validation. The project demonstrates API design, edge-case handling, property-based testing, static analysis, and reproducible CI.

[![CI](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/maven.yml/badge.svg)](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/maven.yml)
[![Quality Gates](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/quality.yml/badge.svg)](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/quality.yml)
[![CodeQL](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/krotname/JavaAlgorithmsShowcase/actions/workflows/codeql-analysis.yml)
[![SonarCloud Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=krotname_JavaAlgorithmsShowcase&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=krotname_JavaAlgorithmsShowcase)
[![Coverage](https://codecov.io/gh/krotname/JavaAlgorithmsShowcase/branch/main/graph/badge.svg)](https://app.codecov.io/gh/krotname/JavaAlgorithmsShowcase)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/krotname/JavaAlgorithmsShowcase/badge)](https://securityscorecards.dev/viewer/?uri=github.com/krotname/JavaAlgorithmsShowcase)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/13151/badge)](https://www.bestpractices.dev/projects/13151)
[![License: GPL-3.0](https://img.shields.io/badge/license-GPL--3.0-0f8a16)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-007396.svg)](https://adoptium.net/)
[![JUnit](https://img.shields.io/badge/JUnit-6-25A162.svg)](https://junit.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36.svg)](https://maven.apache.org/)

![Java Algorithms & Reliability Showcase](docs/assets/project-icon.svg)

## Отобранные инженерные компоненты

| Компонент | Инженерная задача | Что демонстрирует |
| --- | --- | --- |
| [Transaction validation](src/main/java/transactions) | Порядок и состояние операций, каскадная невалидность, баланс и переполнение | Детерминированные переходы состояния, неизменяемый результат, интеграционные и property-based тесты |
| [Algorithm utilities](src/main/java/algorithms) | Граничные условия, сложность, поточный и файловый ввод-вывод | Детерминированные API, явные компромиссы по сложности и edge cases |
| [Parsing / validation](src/main/java/common/SafeParse.java) | Некорректный числовой и текстовый ввод | Явные контракты, сохранение причины исключения, unit-тесты и статический анализ |

Это демонстрационный инженерный репозиторий, а не коммерческий продукт. Реализации отобраны так, чтобы на ревью были видны качество кода, тестовая стратегия и воспроизводимый процесс разработки.

## Что проверяется

- **Smoke / unit:** базовые контракты публичных API.
- **Integration:** состояние и порядок транзакций, а также CLI-контракты алгоритмов.
- **Property-based (jqwik):** инварианты на широких наборах сгенерированных входов.
- **Static analysis:** Checkstyle, PMD и SpotBugs с нулевым допустимым числом новых нарушений.
- **Coverage:** JaCoCo останавливает `mvn verify`, если покрытие строк, ветвлений или инструкций ниже 70%.
- **Security:** CodeQL, dependency review и OpenSSF Scorecard.
- **Reproducibility:** Java 17/21 в CI и строгий офлайн-гейт на Windows после заполнения Maven-кэша.

## Локальная проверка

```bash
mvn -B verify
mvn -B test -Dgroups='smoke'
mvn -B test -Dgroups='integration'
mvn -B test -Dgroups='property'
mvn -B -DskipTests checkstyle:check pmd:check spotbugs:check
```

Полный офлайн-прогон на PowerShell:

```powershell
.\scripts\run-offline-gate.ps1
```

Подробная тестовая матрица и правила кэша описаны в [TESTING.md](TESTING.md).

## Карта репозитория

- `src/main/java/transactions` — stateful validation и доменная модель транзакций.
- `src/main/java/algorithms` — сортировка, графы, динамическое программирование, структуры данных и CLI-алгоритмы.
- `src/main/java/common`, `interview`, `leetcode`, `coderun`, `other`, `kyu*` — утилиты и реализации с сохранённой provenance-структурой.
- `src/test/java/quality` — точки входа для smoke-, integration- и property-наборов.
- `.github/workflows` — CI, quality gates, CodeQL, dependency review и supply-chain проверки.
- [ARCHITECTURE.md](ARCHITECTURE.md), [TESTING.md](TESTING.md), [SECURITY.md](SECURITY.md) — архитектура, тестовая стратегия и security policy.

## Sources & provenance

Задачи и API происходят из нескольких источников: Codewars, LeetCode, алгоритмические треки Яндекса, CodeRun, интервью и самостоятельные задачи. Ссылки на исходные условия и совместимые сигнатуры сохранены в коде там, где это важно для проверяемости.

Пакеты `kyu*` оставлены как внутренняя структура происхождения и совместимости. Они не являются основной таксономией проекта: витрина организована вокруг инженерных задач, контрактов и тестовой стратегии.
