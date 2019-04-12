# ejemplo-springboot-ember-oauth
(Text on spanish)
Repo con un backend springboot y un frontend ember autenticando contra el back con oauth

Este repo sirve de ejemplo de aplicación integrando un front en ember contra un backend 
hecho con springboot donde el mecanismo de autenticacion es OAuth2

Esta basado en [esta pagina](https://www.baeldung.com/rest-api-spring-oauth2-angular)

#### Pasos seguidos
1. Crear el proyecto con [Spring Initialzr](https://start.spring.io/)
   - Indicar que es proyecto con web, security
2. Modificar el pom para agregar oauth2 como dependencia   