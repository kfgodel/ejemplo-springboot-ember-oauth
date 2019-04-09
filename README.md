# ejemplo-springboot-ember-oauth
(Text on spanish)
Repo con un backend springboot y un frontend ember autenticando contra el back con oauth

Este repo sirve de ejemplo de aplicación integrando un front en ember contra un backend 
hecho con springboot donde el mecanismo de autenticacion es OAuth2

Esta basado en dos repos separados:
- Front: https://github.com/kfgodel/ejemplo-ember-oauth
- Back: https://github.com/kfgodel/ejemplo-oauth

#### Pasos seguidos Backend
1. Crear el proyecto con [Spring Initialzr](https://start.spring.io/)
   - Indicar que es proyecto con web, security, oauth resource server
   - Nombre: springboot-backend
2. Unzipear en este repo
3. Agregar [una pagina index](springboot-backend/src/main/resources/static/index.html) 
para mostrar algo en el login.
Ya se puede probar en http://localhost:8080/ con `user` y el pass que sale por consola
4. Agregar dependencias para spring-security-oauth en el [pom](springboot-backend/pom.xml)
5. Definir credenciales hardcodeadas de usuario en [config de security](springboot-backend/src/main/java/com/example/springbootbackend/config/WebSecurityConfig.java)
5. Marcar la aplicacion con el annotation `@EnableAuthorizationServer`
6. Declarar un cliente para grant client en [autorization server](src/main/java/info/kfgodel/oauthtest/AuthorizationServerConfig.java) 
para soportar grant_type: autorization_code
   - Verificar flujo de autorizacion y login con [este link](http://localhost:8080/oauth/authorize?grant_type=authorization_code&response_type=code&client_id=clientId&state=1234) segun [ejemplo de spring](https://docs.spring.io/spring-security-oauth2-boot/docs/current-SNAPSHOT/reference/htmlsingle/#oauth2-boot-testing-authorization-code-flow)
7. Explicitar un authentication manager en [la configuracion del autorization server](src/main/java/info/kfgodel/oauthtest/AuthorizationServerConfig.java) para permitir el grant password.

Verificar que anda con curl:
Client credentials:
> curl clientId:@localhost:8080/oauth/token -dgrant_type=client_credentials -dscope=any

Password
> curl clientId:@localhost:8080/oauth/token -dgrant_type=password -dscope=any -dusername=user -dpassword=password