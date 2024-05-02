# Blog

## Setup development environment

- Install Java 11
- Install Docker
- Install Postgres
  + Create database `blog`
    * `CREATE DATABASE blog;`
  + Create user `blog` with password `blog`
    * `CREATE USER blog WITH PASSWORD 'blog';`
  + Grant all privileges to user `blog`
    * `GRANT USAGE ON SCHEMA public TO blog;`
    * `GRANT CREATE ON SCHEMA public TO blog;`
    * `GRANT ALL PRIVILEGES ON ALL TABLES IN SCHEMA public TO blog;`
    * `ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL PRIVILEGES ON TABLES TO blogА;`

## Run the project locally

- (Optional) To run the project locally create a file `application-local.yml` 
  in `src/main/resources` folder and override all sensitive properties from 
  `application.yml` file. This file is ignored by git.

- Start Milvus database with docker. From project root folder run 
  `docker-compose up -d` command.

- Run `BlogApplication` class.
  + set VM options: `-Dspring.profiles.active=local,nocache`
    * `local` - to use `application-local.yml` file
    * `nocache` - to disable caching of static resources
  + set environment variables:
    ```
    DB_HOST=localhost
    DB_NAME=blog
    DB_PASSWORD=blog
    DB_PORT=5432
    DB_USERNAME=blog
    DELETE_SECRET=12345
    GIT_DIRECTORY=blog.com
    ```
    
- Open `http://localhost:8080/blog` in browser to open list of posts.

## Run the project locally from jar 

- Please update `.env` file with correct values.

- Run `bash run-local.sh 1` command to build jar, stop/start docker-compose 
  and run application

- Run `bash run-local.sh 2` command to stop/start docker-compose and run  
  application. Expected that jar is already built.

- Run `bash run-local.sh 3` command to run application only. Expected that 
  docker-compose is already running and jar is already built.

- Open `http://localhost:8080/blog` in browser to open list of posts.

## Push changes to repository 

- Run `bash git-push-all.sh` command to push changes (project and site repo) 
  to repository.