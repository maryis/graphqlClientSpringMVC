I tested different ways for graphql-client in java
    - apollo
    - some spring-related resources
their links:
https://github.com/aoudiamoncef/apollo-client-maven-plugin  
https://github.com/graphql-java/awesome-graphql-java
https://github.com/graphql-java/graphql-java-spring
https://github.com/graphql-java-kickstart/graphql-spring-webclient

at the time, they are not commonly used and sometimes have problem.
--------------------------
in this project I call a graphql-server using restTemplate.
by using ObjectMapper of jackson, we can easily handle the result of graphql-server-call
that is a valid json in following format:

{ "data": "   ", "errors":"  "}

if there is no error, the second part does not exist.
    