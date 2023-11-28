# Scraping API

### Build project
The Maven is used to build application. Navigate to project root catalog and run:
```shell script
mvn clean package
```

### Build docker image
To build docker image navigate to project root catalog and run:
```shell script
docker build -t scraping-api .
```

#### Development Tool
The following tools required to be installed on development machine:

* JDK 17
* Maven
* Git
* Docker
* Postgres
* IntelliJ IDEA

Navigate to project-dir and type the next command:
```shell script
docker-compose up -d
```
This command run all necessary tools in docker containers.
Then if you will need start already created containers for example after the system reboot just use:

```shell script
docker-compose start
```
This command will start container whih has been created before and keep all data.