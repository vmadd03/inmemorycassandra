# inmemorycassandra
# Goal(s): 

1.	Spring Boot app with a rest endpoint to connect to real cassandra instance. 
2.	Spring Boot component tests to connect to an embedded cassandra (These can be integrated and run as part of our CI)

# Step(s) towards building it

1. Create a stand alone spring boot maven project using SPRING INITIALIZR at https://start.spring.io/
2. Add all the spring boot dependencies required. Critical ones are listed below
	1. spring-boot-starter-data-cassandra - spring boot jpa for cassandra to support CRUD operations
	2. cassandra-driver-core	      - Cassandra driver to connect to real cassandra or embedded cassandra
	3. cassandra-unit-spring	      -	For embedded cassandra
	4. cassandra-unit-shaded	      -  For embedded cassandra
3. Add a spring boot rest endpoint which inserts the data into a real cassandra instance by defining the controller(s), repositories(s)
4. Add a spring boot component tests to connect to embedded cassandra and validate the results.

