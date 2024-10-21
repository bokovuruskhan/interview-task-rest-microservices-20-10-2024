@echo off
cd ..

echo Building JAR files...
gradlew clean build -x test

echo JAR files created successfully.
pause
