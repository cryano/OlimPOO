#!/usr/bin/env bash
set -euo pipefail

BASE_DIR="$(cd "$(dirname "$0")" && pwd)"
OUT_DIR="$BASE_DIR/out"

mkdir -p "$OUT_DIR"
find "$BASE_DIR/src" -name '*.java' -print0 | xargs -0 javac -encoding UTF-8 -d "$OUT_DIR"
java -cp "$OUT_DIR" br.edu.ucsal.olimpoo.OlimPOOConsole
