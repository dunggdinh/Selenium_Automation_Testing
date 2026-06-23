#!/usr/bin/env bash

set -e

ROOT_DIR="$(cd "$(dirname "$0")" && pwd)"
WRAPPER_DIR="$ROOT_DIR/.mvn/wrapper"
JAR="$WRAPPER_DIR/maven-wrapper.jar"
JAR_URL="https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar"

mkdir -p "$WRAPPER_DIR"

if [ ! -f "$JAR" ]; then
  echo "Downloading maven wrapper jar..."
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL -o "$JAR" "$JAR_URL"
  elif command -v wget >/dev/null 2>&1; then
    wget -q -O "$JAR" "$JAR_URL"
  else
    echo "Error: curl or wget is required to download the maven wrapper jar." >&2
    exit 1
  fi
fi

java -jar "$JAR" "$@"
