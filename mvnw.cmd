@echo off
setlocal enabledelayedexpansion

set ROOT_DIR=%~dp0
set WRAPPER_DIR=%ROOT_DIR%.mvn\wrapper
set JAR=%WRAPPER_DIR%\maven-wrapper.jar
set JAR_URL=https://repo1.maven.org/maven2/io/takari/maven-wrapper/0.5.6/maven-wrapper-0.5.6.jar

if not exist "%JAR%" (
  echo Downloading maven wrapper jar...
  powershell -NoProfile -Command "[Net.ServicePointManager]::SecurityProtocol = [Net.SecurityProtocolType]::Tls12; Invoke-WebRequest -Uri '%JAR_URL%' -OutFile '%JAR%'"
)

java -jar "%JAR%" %*
