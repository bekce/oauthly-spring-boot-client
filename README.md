# oauthly-spring-boot-client

This project authenticates against an OAuthly server (an OAuth 2.0 Authorization Server).
It uses the standard `@EnableOAuth2Sso` of Spring.
You can follow <https://spring.io/guides/tutorials/spring-boot-oauth2/> link for advanced configuration. 

## Instructions

1. Run [oauthly](https://github.com/bekce/oauthly) on port 9000. 
2. Using an admin account on oauthly, create a client, put its `clientId` and `clientSecret` 
in [`application.yml`](https://github.com/bekce/oauthly-spring-boot-client/blob/master/src/main/resources/application.yml)
3. Start this server by `./mvnw spring-boot:run`.
4. Go to <http://localhost:8080> and try logging in.
