# locations-api
This API is responsible to bring vehicles location data based on latitude and longitude. 
It also calculates the minimum distance between one location and a list of them. Moreover,
it retrieves establishments that can be used to search.

# Requirements

* [Java Open JDK 8](https://www.digitalocean.com/community/tutorials/how-to-install-java-with-apt-on-ubuntu-18-04)
* [Maven](https://maven.apache.org/install.html)
* [Docker](https://docs.docker.com/engine/install/ubuntu/#installation-methods)
* [Docker Compose](https://docs.docker.com/compose/install/#install-compose-on-linux-systems)
* [Git](https://git-scm.com/downloads)

# Setting up the environment

Preparing environment
```
git clone https://github.com/rafaelpcustodio/locations-api.git
```

Starting application locally
```
In the root of the project type the following command on terminal:

mvn clean install

After finishing the previous command, type the next one:

docker-compose up

This command will build two images: localstack and location-api. 
The first one is the image responsible to start SQS and DynamoDB from 
localstack image. The second one will start this API using Dockerfile.
```

Checking swagger:
```
After the application is already up, you can check the end-points of 
this application on:

http://localhost:8080/swagger-ui.html

You'll see 4 end-points:

- GET /api/v1/establishments 
(returns all establishments created with localstack shell script)

- GET /api/v1/locations/{license-plate}?dateTime=(ISO DateTime query param)  
(returns all locations of the license plate and optionally with a 
dateTime as a query param. The locations retrieved will be from this date 
time passed until now)

- POST /api/v1/locations 
(create a location to a license plate based on the request body)

- POST /api/v1/locations/minimum-distance 
(calculate distance between a location and a list of locations)
```

Additional info and Troubleshooting:
```
1- If you want to add more establishments locally. You can change
the shell script located on:

src/main/resources/aws/services/create-table-establishments.sh


2- Don't forget to use the command docker-compose down after stopping it.
If any additional issue happen during the start of docker-compose, use 
the command "docker-compose down", followed by "docker system prune" 
(it will remove all unused containers, networks, images and volumes).
After it, docker-compose up will work normally. 

Localstack URL for any further information:

https://github.com/localstack/localstack

3- This application to run locally must use localstack. If you have an 
Amazon Environment and you  want to start it inside an EC2, all you have
to do is to check its image on docker hub:

https://hub.docker.com/repository/docker/rafaelpcustodio/locations-api

Don't forget to change the environment varialbes for the names of your
queues and tables url inside amazon services. Additionally, you have to 
remove the "build" statement on docker-compose and place "image" followed
by the image name: rafaelpcustodio/locations-api:v1.
```
