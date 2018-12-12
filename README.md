# webapp-jetty-maven
Aplicação Web com Jetty e Maven

A outra [projeto Java](https://github.com/skatesham/maven-course-products) desenvolvido explica uma sequencia de comandos e depdendencias do Maven no [README](https://github.com/skatesham/maven-course-products/blob/master/README.md).

### Criação do projeto por linha de comando
Descrição da arquitetura [Maven Webapp Archetype](https://maven.apache.org/archetypes/maven-archetype-webapp/).
```
mvn archetype:generate  -DartifactId=webapp -DgroupId=br.sham.maven.web -DinteractiveMode=false -DarchetypeGroupId=org.apache.maven.archetypes -DarchetypeArtifactId=maven-archetype-webapp -DarchetypeVersion=1.3
```

### Configuraçãoes no `pom.xml`

#### Dependencias
As dependencias a ser adicionadas são as seguentes.
```
<project>
...
	<dependencies>

		<!-- Dependencia para HttpServlet -->
		<dependency>
			<groupId>javax.servlet</groupId>
			<artifactId>javax.servlet-api</artifactId>
			<version>3.1.0</version>
			<scope>provided</scope>
		</dependency>

		<!-- Dependencia para Validação de CPF brasileiro -->
		<dependency>
			<groupId>br.com.caelum.stella</groupId>
			<artifactId>caelum-stella-core</artifactId>
			<version>2.1.2</version>
		</dependency>
    </dependencies>
...
</project>
```

#### Plugins
O plugin adicionar é para utilização do Web Container Jetty, e pode ser utilizado da seguinte forma.
```
<project>
...
	<build>
		<finalName>webapp</finalName>
		<plugins>
			<plugin>
				<groupId>org.eclipse.jetty</groupId>
				<artifactId>jetty-maven-plugin</artifactId>
				<version>9.3.7.v20160115</version>
				<configuration>
					<scanIntervalSeconds>10</scanIntervalSeconds>
					<webApp>
						<contextPath>/</contextPath>
					</webApp>
				</configuration>
			</plugin>
		</plugins>
	</build>
</project>
```

### Configurando o Projeto

#### Configurando `web.xml`
A tag ```<servlet>``` mapeia um servlet para rota (route), como mostra a configurações do desse arquivo.
```
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee 
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1">
	<servlet>
		<servlet-name>
			home
		</servlet-name>
		<servlet-class>
			br.sham.maven.web.webapp.Home
		</servlet-class>
	</servlet>
	<servlet-mapping>
		<servlet-name>
			home
		</servlet-name>
		<url-pattern>
			/
		</url-pattern>
	</servlet-mapping>
</web-app>
```

#### Criando um Servlet, no caso `Home.java`

```
package br.sham.maven.web.webapp;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// O Servelet pode ser mapedo com a tag @WebServlet ou pelo web.xml
//@WebServlet(urlPatterns={"/"})
public class Home extends HttpServlet {
	private static final long serialVersionUID = 2806421523585360625L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/templates/index.jsp").forward(req, resp);
	}
}
```

##### `index.jsp`
```
<%@ include file="base/header.jsp"%>

<%@ include file="base/navbar.jsp"%>

<div class="container text-center">
	<div class="jumbotron jumbotron-fluid">
		<div class="container">
			<h1 class="display-4">Bem Vindo ao Web APP</h1>
			<p class="lead">Esse � um servidor web com Jetty que utiliza o maven para
como Controle de dependencias e automatizador de contru��o</p>
		</div>
	</div>
</div>

<%@ include file="base/footer.jsp"%>
```

##### `header.jsp`
```
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt">

<head>
    <meta charset="UTF-8">
    <title>WEB APP</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO"
        crossorigin="anonymous">
    <link rel="stylesheet" href="../../static/css/styles.css">
</head>

<body class="h-100 bg-warning">
```

##### `footer.jsp`
```
<footer class="bg-primary p-2 fixed-bottom text-light text-center">Copyright &copy; Sham Vinicius Fiorin 2018</footer>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
    crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js" integrity="sha384-ZMP7rVo3mIykV+2+9J3UJ46jBk0WLaUAdn689aCwoqbBJiSnjAK/l8WvCWPIPm49"
    crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js" integrity="sha384-ChfqqxuZUCnJSK3+MXmPNIyE6ZbWh2IMqE241rYiqJxyMiZ6OW/JmZQ5stwEULTy"
    crossorigin="anonymous"></script>
</body>

</html>
```

##### 'navbar.jsp'
```
<nav class="navbar navbar-expand-lg navbar-dark fixed-top bg-primary">
  <a class="navbar-brand" href="#">Web App</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>
  <div class="collapse navbar-collapse" id="navbarNav">
    <ul class="navbar-nav">
      <li class="nav-item active">
        <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Features</a>
      </li>
      <li class="nav-item">
        <a class="nav-link" href="#">Pricing</a>
      </li>
      <li class="nav-item">
        <a class="nav-link disabled" href="#">Disabled</a>
      </li>
    </ul>
  </div>
</nav>
<br>
<br>
<br>
```

### Tela Inicial
A tela inicial utiliza de aproveitamento de template na pagina `index.jsp`

![alt text](https://raw.githubusercontent.com/skatesham/webapp-jetty-maven/master/src/main/webapp/static/img/P%C3%A1gina%20inicial.png).

### Certificado do Curso
![alt text](https://raw.githubusercontent.com/skatesham/webapp-jetty-maven/master/src/main/webapp/static/img/certificado.png).

#### Curso Maven: Build do zero a web
Esse projeto é o baseado no trabalho final do [Curso Maven: Build do zero a web](https://cursos.alura.com.br/course/maven-build-do-zero-a-web).

##### Desenvolvido por Sham Vinicius Fiorin