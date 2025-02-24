# Read Me First
The following was discovered as part of building this project:

* The original package name 'com.thINK-organisation-mb.article-service.article-service' is invalid and this project uses 'com.thINK_organisation_mb.article_service.article_service' instead.

# Getting Started

### Reference Documentation
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.4.2/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.4.2/maven-plugin/build-image.html)
* [Spring Boot Testcontainers support](https://docs.spring.io/spring-boot/3.4.2/reference/testing/testcontainers.html#testing.testcontainers)
* [Testcontainers RabbitMQ Module Reference Guide](https://java.testcontainers.org/modules/rabbitmq/)
* [Testcontainers Vault Module Reference Guide](https://java.testcontainers.org/modules/vault/)
* [Testcontainers Kafka Modules Reference Guide](https://java.testcontainers.org/modules/kafka/)
* [Testcontainers Postgres Module Reference Guide](https://java.testcontainers.org/modules/databases/postgres/)
* [Spring Web](https://docs.spring.io/spring-boot/3.4.2/reference/web/servlet.html)
* [Spring Security](https://docs.spring.io/spring-boot/3.4.2/reference/web/spring-security.html)
* [Spring Data JPA](https://docs.spring.io/spring-boot/3.4.2/reference/data/sql.html#data.sql.jpa-and-spring-data)
* [PostgresML](https://docs.spring.io/spring-ai/reference/api/embeddings/postgresml-embeddings.html)
* [PGvector Vector Database](https://docs.spring.io/spring-ai/reference/api/vectordbs/pgvector.html)
* [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/current/reference/htmlsingle/)
* [Testcontainers](https://java.testcontainers.org/)
* [Spring for Apache Kafka](https://docs.spring.io/spring-boot/3.4.2/reference/messaging/kafka.html)
* [Spring for RabbitMQ](https://docs.spring.io/spring-boot/3.4.2/reference/messaging/amqp.html)
* [Spring for RabbitMQ Streams](https://docs.spring.io/spring-amqp/reference/stream.html)
* [WebSocket](https://docs.spring.io/spring-boot/3.4.2/reference/messaging/websockets.html)
* [Spring Boot Actuator](https://docs.spring.io/spring-boot/3.4.2/reference/actuator/index.html)
* [Spring gRPC [Experimental]](https://docs.spring.io/spring-grpc/reference/index.html)
* [Vault Configuration](https://docs.spring.io/spring-cloud-vault/reference/)
* [Eureka Server](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#spring-cloud-eureka-server)
* [Eureka Discovery Client](https://docs.spring.io/spring-cloud-netflix/reference/spring-cloud-netflix.html#_service_discovery_eureka_clients)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/rest/)
* [Securing a Web Application](https://spring.io/guides/gs/securing-web/)
* [Spring Boot and OAuth2](https://spring.io/guides/tutorials/spring-boot-oauth2/)
* [Authenticating a User with LDAP](https://spring.io/guides/gs/authenticating-ldap/)
* [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
* [Messaging with RabbitMQ](https://spring.io/guides/gs/messaging-rabbitmq/)
* [Using WebSocket to build an interactive web application](https://spring.io/guides/gs/messaging-stomp-websocket/)
* [Building a RESTful Web Service with Spring Boot Actuator](https://spring.io/guides/gs/actuator-service/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)
* [Service Registration and Discovery with Eureka and Spring Cloud](https://spring.io/guides/gs/service-registration-and-discovery/)

### Additional Links
These additional references should also help you:

* [Various sample apps using Spring gRPC](https://github.com/spring-projects-experimental/spring-grpc/tree/main/samples)

### Testcontainers support

This project uses [Testcontainers at development time](https://docs.spring.io/spring-boot/3.4.2/reference/features/dev-services.html#features.dev-services.testcontainers).

Testcontainers has been configured to use the following Docker images:

* [`apache/kafka-native:latest`](https://hub.docker.com/r/apache/kafka-native)
* [`pgvector/pgvector:pg16`](https://hub.docker.com/r/pgvector/pgvector)
* [`postgres:latest`](https://hub.docker.com/_/postgres)
* [`rabbitmq:latest`](https://hub.docker.com/_/rabbitmq)

Please review the tags of the used images and set them to the same as you're running in production.

### Maven Parent overrides

Due to Maven's design, elements are inherited from the parent POM to the project POM.
While most of the inheritance is fine, it also inherits unwanted elements like `<license>` and `<developers>` from the parent.
To prevent this, the project POM contains empty overrides for these elements.
If you manually switch to a different parent and actually want the inheritance, you need to remove those overrides.

