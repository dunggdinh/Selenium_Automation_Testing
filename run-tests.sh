#!/usr/bin/env bash

set -e

# Change to script directory (project root)
cd "$(dirname "$0")"

mvn test
