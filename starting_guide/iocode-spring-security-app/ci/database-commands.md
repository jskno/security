
*********************
COMMANDS HOSPEDAJES
*********************
From iocode-auth-server-db Terminal (C:\My_Folder\jsknoGithubProjects\security\starting_guide\security-app-db)
ls (ci/db)
*********************
docker login https://hub.docker.com/
username
password
*********************
docker image build -t auth-server-db:1.0 -f ci/Dockerfile-db .
docker image tag auth-server-db:1.0 artifactory.emobg.io/docker-sbx/emobg/db_hospedajes_notifications:1.0
docker image push artifactory.emobg.io/docker-sbx/emobg/auth-server-db:1.0
**********
docker pull artifactory.emobg.io/docker-sbx/emobg/auth-server-db:1.0
*********************
docker image ls
docker container run -p 5432:5432 artifactory.emobg.io/docker-sbx/emobg/auth-server-db:1.0

*********************
docker image build -t security-app-db:1.0 -f ci/Dockerfile-db .
docker image ls
docker container run -p 5432:5432 security-app-db:1.0