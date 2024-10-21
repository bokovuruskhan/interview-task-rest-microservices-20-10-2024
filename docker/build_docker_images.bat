@echo off
cd ..

REM
echo Building docker images...

set JAR_DIR=build\libs
set DOCKER_DIR=docker

copy users-rest\%JAR_DIR%\users-rest-*.jar %DOCKER_DIR%
copy roles-rest\%JAR_DIR%\roles-rest-*.jar %DOCKER_DIR%
copy users-roles-rest\%JAR_DIR%\users-roles-rest-*.jar %DOCKER_DIR%

cd %DOCKER_DIR%

docker build -t users-rest -f UsersRestDockerfile .
docker build -t roles-rest -f RolesRestDockerfile .
docker build -t users-roles-rest -f UsersRolesRestDockerfile .

echo Docker images built successfully.
pause
