MYSQL DOCKER
docker pull mysql/mysql-server:latest
docker run -p 3306:3306 --name=tracker -d mysql/mysql-server:latest
docker exec -it tracker mysql -uroot -p

ALTER USER 'root'@'localhost' IDENTIFIED BY 'password';

C:\cognizant\flyway-6.3.3\flyway -url="jdbc:mysql://localhost:3306/tracker_dev" -locations=filesystem:databases/tracker clean migrate
C:\cognizant\flyway-6.3.3\flyway -url="jdbc:mysql://localhost:3306/tracker_test" -locations=filesystem:databases/tracker clean migrate



5uKsaLkEBuvcenbahZorGOk+OG
password


1. Building a Spring Boot App

cd ~/workspace/assignment-submission 
gradlew cloudNativeDeveloperSimpleApp -PserverUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/

https://pal-tracker-bikram-pal-arch1.cfapps.io/


2. Configuring an App

gradlew cloudNativeDeveloperCloudFoundry -PserverUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/

3. Deployment Pipelines
gradlew cloudNativeDeveloperReviewPipeline -PreviewUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/

4. Spring MVC with REST Endpoints
gradlew cloudNativeDeveloperRest -PserverUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/

5. Cloud Foundry Services & Database Migrations
cf create-service p.mysql db-small tracker-database

gradlew cloudNativeDeveloperDatabaseMigrations -PserverUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/

6. Spring JDBC Template
gradlew cloudNativeDeveloperDatabaseAccess -PserverUrl=https://pal-tracker-bikram-pal-arch1.cfapps.io/
