# Codewars

Набор решения алгоритмических задач разного уровня сложности 
с https://www.codewars.com/kata/ на Java по методу TDD 

Профиль https://www.codewars.com/users/krotname

Для запуска в IntelliJ IDEA нажать правой кнопкой на src/main/java и Run 'All Tests'

## Amnezia WireGuard proxy in Docker Compose

Добавлен `docker-compose.yml`, который поднимает:
- WireGuard-клиент с подключением к существующему серверу Amnezia.
- Proxy-сервис (3proxy), работающий через VPN-туннель.
- Опционально `v2ray-server` (профиль `v2ray`) в том же network namespace, что и WireGuard.

Логи отключены для контейнеров через `logging.driver: "none"`.

### Конфиги сервера
- `wireguard/wg_confs/wg0.conf` — WireGuard-конфигурация клиента.
- `proxy/3proxy.cfg` — прокси с авторизацией.
- `v2ray/server-config.json` — конфиг V2Ray-сервера (VMess + WS).

### Сгенерированные доступы
- Proxy user: `proxy_9b93b2`
- Proxy password: `DTvWPDSk6UeD3HGXiji7`
- V2Ray UUID #1: `229545ae-a4b5-4fd6-8146-77c3f3267bce`
- V2Ray UUID #2: `ad1ead97-6a7c-433d-9b53-03d7d881f510`

### Доступ к сервисам
- SOCKS5 (3proxy): `127.0.0.1:1080`
- HTTP (3proxy): `127.0.0.1:3128`
- V2Ray VMess+WS: `127.0.0.1:10000`

Перед запуском обязательно замените шаблонные значения:
- `REPLACE_WITH_*` в `wireguard/wg_confs/wg0.conf`
- `YOUR_SERVER_IP` в клиентских файлах

### Конфиги клиента для подключения к этому серверу
- `client/proxy.env.example` — переменные окружения для `curl`, `wget`, CLI и приложений, которые читают `HTTP_PROXY/HTTPS_PROXY/ALL_PROXY`.
- `client/proxychains.conf` — готовый конфиг для `proxychains` (SOCKS5 + логин/пароль).
- `client/v2ray-client.example.json` — пример клиента для V2Ray UUID #1.
- `client/v2ray-client-2.example.json` — пример клиента для V2Ray UUID #2.

Перед использованием замените `YOUR_SERVER_IP` на реальный IP/домен сервера.

### Проверка конфигов
```bash
./scripts/validate.sh
```

Скрипт проверяет YAML/JSON синтаксис, наличие обязательных файлов и запускает `docker compose config`, если Docker доступен в системе.

### Запуск
```bash
docker compose up -d
```

### Запуск с V2Ray сервером
```bash
docker compose --profile v2ray up -d
```
