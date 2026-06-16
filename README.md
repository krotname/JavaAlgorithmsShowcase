# Решения с Codewars

[![CI](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/maven.yml)
[![Quality Gates](https://github.com/krotname/CodewarsKataJava/actions/workflows/quality.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/quality.yml)
[![CodeQL](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/krotname/CodewarsKataJava/actions/workflows/codeql-analysis.yml)
[![Coverage Gate](https://img.shields.io/badge/coverage%20gate-JaCoCo%2070%25%2B-2ea44f)](TESTING.md)
[![OpenSSF Scorecard](https://api.securityscorecards.dev/projects/github.com/krotname/CodewarsKataJava/badge)](https://securityscorecards.dev/viewer/?uri=github.com/krotname/CodewarsKataJava)
[![OpenSSF Best Practices](https://www.bestpractices.dev/projects/13151/badge)](https://www.bestpractices.dev/projects/13151)
[![License: GPL-3.0](https://img.shields.io/badge/license-GPL--3.0-0f8a16)](LICENSE)
[![Java](https://img.shields.io/badge/Java-17%2B-007396.svg)](https://adoptium.net/)
[![JUnit](https://img.shields.io/badge/JUnit-6-25A162.svg)](https://junit.org/)
[![Maven](https://img.shields.io/badge/Maven-3.9%2B-C71A36.svg)](https://maven.apache.org/)

![Codewars Kata Java](docs/assets/project-icon.svg)

[English README](README.en.md)

## Русский

### Зачем нужен этот репозиторий

- Показать не только решения задач, но и зрелый рабочий процесс: тестирование, CI, проверки качества, документацию.
- Сохранить код понятным для ревью: читаемые структуры, воспроизводимые команды и прозрачные критерии качества.
- Поддерживать русско- и англоязычную документацию.

### Карта репозитория

- `src/main/java` — решения/бизнес-логика
- `src/test/java` — тесты и саппортивные suites
  - `quality.SmokeSuite` (unit/smoke)
  - `quality.IntegrationSuite` (интеграционные проверки транзакций)
  - `quality.PropertySuite` (property-тесты на jqwik, сгруппировано)
- `.github` — CI, dependabot, шаблоны
- `ARCHITECTURE.md` — архитектурные заметки для ревью.
- `CHANGELOG.md`, `CONTRIBUTING.md`, `CODE_OF_CONDUCT.md`, `SECURITY.md`, `TESTING.md`

### Стратегия тестов

- **Smoke / Unit**: базовая проверка API каждого решения.
- **Интеграционные**: последовательности и состояние в `transactions`.
- **Property-based (jqwik)**: инварианты в подходящих задачах.
- **UI**: не применимо (`N/A`), в репозитории нет UI-слоя.
- **Статический контроль**: Checkstyle, PMD, SpotBugs в `verify`; сборка падает при нарушениях.
- **Coverage-gate**: JaCoCo ломает `mvn verify`, если покрытие ниже 70% строк, ветвлений
  или инструкций.
- **Отдельный quality-гейт**: workflow `.github/workflows/quality.yml` для чистых статических проверок без прогона тестов.
- **Checkstyle / PMD / SpotBugs**: чистый текущий baseline.
- **Checkstyle**: проектный набор правил в `config/checkstyle/checkstyle.xml` и явные suppressions для generated smoke-тестов.
- **SpotBugs**: чистый отчёт с явными исключениями совместимости в `config/spotbugs-exclude.xml`.
- **Текущий baseline**: 455 тестов; 91.4% line, 85.1% branch и 92.7% instruction coverage.

### Как запускать

```bash
mvn -B verify
mvn -B test
mvn -B test -Dgroups='smoke'
mvn -B test -Dgroups='integration'
mvn -B test -Dgroups='property'
mvn -B -DskipTests checkstyle:check pmd:check spotbugs:check
mvn -B test -Dtest='quality.SmokeSuite'
mvn -B test -Dtest='quality.IntegrationSuite'
mvn -B test -Dtest='quality.PropertySuite'
```

### Что отражает репозиторий как "публично привлекательный"

- Есть CI с понятными этапами и артефактами (`surefire`, `jacoco`).
- Есть требования и процесс (`CONTRIBUTING`, `SECURITY`, `CODE_OF_CONDUCT`).
- Есть явная стратегия тестирования и локальные команды.
- Есть заметки о покрытии и безопасности.
- Есть архитектурная карта для быстрой оценки качества решений (`ARCHITECTURE.md`).

### Особенность управления default branch

`main` намеренно остается с облегченным branch governance. Это персональный учебный kata-журнал, где небольшие правки решений, тестов и пояснений часто вносятся напрямую, чтобы сохранять быстрый цикл тренировки. Публичные гарантии качества обеспечиваются CI, CodeQL, quality gates, Dependabot, Security Policy и воспроизводимыми локальными командами. Для содержательных изменений, влияющих на поведение задач или процесс качества, по-прежнему используется обычный PR/review-подход.
Отсутствие branch protection для `main` является осознанным исключением этого проекта и не должно трактоваться как дефект hardening-модели.

### Default branch governance note

`main` intentionally uses lightweight branch governance. This is a personal educational kata journal where small solution, test, and explanation updates are often pushed directly to preserve a fast practice loop. Public quality assurance comes from CI, CodeQL, quality gates, Dependabot, the Security Policy, and reproducible local commands. Substantive changes that affect kata behavior or quality policy still use the normal PR/review workflow.
The absence of branch protection on `main` is an intentional project-specific exception and should not be treated as a hardening defect.
