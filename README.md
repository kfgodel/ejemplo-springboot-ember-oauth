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

#### Pasos seguidos Frontend
1. Crear carpeta ember-frontend
2. Definir la version de node con un .nvmrc 
> nvm install  
> nvm use  

3. Instalar ember
> npm install -g ember-cli  

4. Crear proyecto ember
> ember new ember-frontend    
> rm -fR ember-frontend/.git  
> rm -f ember-frontend/README.md  
> mv -f ember-frontend/* .  
> mv -f ember-frontend/.* .  
> rm -fR ember-frontend   

(Ignorar tmp, dist, public, vendor)

5. Probar que funciona hasta aca
> ember serve  
Abrir [http://localhost:4200/](http://localhost:4200/)  

6. Quitar la pagina de welcome
7. Instalar simple auth
> ember install ember-simple-auth

8. Customizar [vista](app/templates/application.hbs) y [controller](app/controllers/application.js) de application [segun pagina](https://github.com/simplabs/ember-simple-auth#installation)
9. Usar [authenticator password](app/authenticators/oauth2.js) para autenticar
10. Crear [form de login](app/templates/login.hbs) y [su controller](app/controllers/login.js)
11. Agregar [mixin](app/routes/application.js) para ser desloguear automaticamente

12. Agregar ejemplo de [ruta protegida](app/routes/authenticated/protected.js)
13. Agregar las rutas en el [router](app/router.js)

14. Agregar [authorizer](app/authorizers/oauth2.js) para decorar los request con headers de oauth
15. Asegurar requests de ember data [adapter](app/adapters/application.js)

16. Configurar endpoint de autenticacion en [authenticator](app/authenticators/oauth2.js)
17. Agregar endpoint y clientId como parametros del [environment](config/environment.js)
18. Enviar requests de api al backend en [package.json](package.json)

19. Agregar ejemplo de [pagina protegida por autenticacion](app/templates/authenticated/protected.hbs)

### Verificar comportamiento

1. Levantar backend [app java](https://github.com/kfgodel/ejemplo-oauth)
2. Levantar este frontend
> npm start

3. Abrir [http://localhost:4200/protected](http://localhost:4200/protected)
4. Verificar que no esta disponible hasta que no se autentica (click en login)
`user:password` las credenciales
5. Volver a [http://localhost:4200/protected](http://localhost:4200/protected) una vez autenticado

6. Se puede probar también desloguear desde una solapa, o dar refresh
