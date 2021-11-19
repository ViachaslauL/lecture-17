<h2>Home task #17</h2>
<h4>Description</h4>
<p>This application inserts data about a person. Surname, name and patronymic,
 as well as address.</p>
<h4>Task list</h4>
<ol>
<li>Git repo
<li>README file with rules from lecture
<li>create new maven/gradle project
<li>Create 2 POJO classes with 1 to 1 relation
<li>Create DB schema for classes from #4
<li>Add JPA + hibernate libs to project
<li>Configure POJO mapping with JPA annotations
<li>Create CRUD DAO (use EntityManager) for POJOs
<li>Tests
</ol>
<h4>Technologies</h4>
<ol>
<li>Java version: 11
<li>MySQL database
<li>docker-compose
<li>Flyway
<li>Hibernate
<li>Slf4j+logback
<li>Lombok
<li>jUnit-jupiter
<li>Mockito
</ol>
<h4>How to run</h4>
<p>In order to build and run the application, follow these steps. 
In the console, go to the project directory and type:</p>
<ol>
<li>mvn clean package
<li>docker-compose up -d
<li>mvn flyway:migrate
</ol>