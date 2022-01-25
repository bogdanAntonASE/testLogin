# testLogin

Hello,

This is my login implementation.

Technologies used:
Java, Spring, Spring Boot, H2 database and for tests Mockito, JUnit and AssertJ.

My main models are Roles and Users.
Each Role has an id and a Name.
Each User has an username, a password and a list of Roles.

For registration I have used JpaRepositories in order to store the data and I have used an instance of BCryptPasswordEncoder in order to encode my passwords.
For login I kept the Spring Security provided endpoint and added a SecurityConfig class which extends WebSecurityConfigurerAdapter and has been added to Spring context using the @Configuration annotation.
Each user (registered or not) is able access endpoints like "/register" or "/login".
In order to access the endpoint "/user", which retrieves all users from database, the user needs to has as Role the "ADMIN" one, otherwise for the rest of requests our user has to be authenticated. (use a valid JWT token, not an expired one)

For Login part, I have added a class called AuthenticationFilter which extends UsernamePasswordAuthenticationFilter and is using an authenticationManager received as parameter in constructor. The constructor is called when we set our SecurityConfig and we retrieve the authenticationManager from WebSecurityConfigurerAdapter class.

In order to encode the password I have used a secret stored in application.properties for classes which are kept in Spring context, and hardcoded for the rest (AuthenticationFilter and AuthorizationFilter).

If the username and password are present in our application, then an UsernamePasswordAuthenticationToken is generated and sent as parameter in authenticate from AuthenticationMangaer, if the attempt was successful, then we can generate our JWT which contains as Subject the username and as claims we have just the list of roles which are converted/mapped to GrantedAuthorities in advance. Also, as algorithm I have used HMAC256.

For the authorization part I have the class AuthorizationFilter which extends OncePerRequestFilter and override the doInternalFilter method. This method if checking for "Authorization" header, if it exists then extracts in a local variable the jwt token excluding "Bearer " prefix. I am veryfing the jwt using JWTVerifier (to not be expired) and then we decode the token and basically deserialize it in order to create another token to be stored into SecurityContextHolder.

For error handling I have created an ExceptionPayload class which should be the payload to be displayed by each custom exception in my application. Also, I have created a BaseException which extends RuntimeException and has as fields the errorHttpcode and the message. Each custom exception extends BaseException and call the super constructor. Also, I have created the ExceptionHandler which is annotated with @ControllerAdvice and the main method handleRequestException is annotated with @ExceptionHandler(value = {[Exception].class}), here I think we may use the BaseException class in order to capture all exceptions, but for now I have captured only the BadRequestException.

For logging I have used Log4j2 with classes Logger and LogManager. Also, I have a configuration file log4j2.xml (pretty standard one:D).
