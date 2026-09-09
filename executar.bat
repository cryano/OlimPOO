@echo off
setlocal
set BASE_DIR=%~dp0
set OUT_DIR=%BASE_DIR%out
if not exist "%OUT_DIR%" mkdir "%OUT_DIR%"
dir /s /b "%BASE_DIR%src\*.java" > "%BASE_DIR%fontes.txt"
javac -encoding UTF-8 -d "%OUT_DIR%" @"%BASE_DIR%fontes.txt"
if errorlevel 1 exit /b 1
java -cp "%OUT_DIR%" br.edu.ucsal.olimpoo.OlimPOOConsole
del "%BASE_DIR%fontes.txt"
endlocal
