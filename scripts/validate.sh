#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

echo "[1/4] Validate docker-compose YAML syntax"
ruby -e 'require "yaml"; YAML.load_file("docker-compose.yml"); puts "docker-compose.yml: OK"'

echo "[2/4] Validate JSON configs"
python - <<'PY'
import json
from pathlib import Path

files = [
    'v2ray/server-config.json',
    'client/v2ray-client.example.json',
]

for f in files:
    json.loads(Path(f).read_text(encoding='utf-8'))
    print(f"{f}: OK")
PY

echo "[3/4] Check required config files exist"
required=(
  "docker-compose.yml"
  "wireguard/wg_confs/wg0.conf"
  "proxy/3proxy.cfg"
  "v2ray/server-config.json"
  "client/proxy.env.example"
  "client/proxychains.conf"
  "client/v2ray-client.example.json"
)

for file in "${required[@]}"; do
  if [[ ! -f "$file" ]]; then
    echo "Missing file: $file" >&2
    exit 1
  fi
  echo "$file: OK"
done

echo "[4/4] Docker Compose semantic validation (if docker exists)"
if command -v docker >/dev/null 2>&1; then
  docker compose config >/dev/null
  echo "docker compose config: OK"
else
  echo "docker not found: skip docker compose config"
fi

echo "Validation completed"
