<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<title>Liste des pays</title>

<spring:url value="/resources/core/css/hello.css" var="coreCss" />
<spring:url value="/resources/core/css/bootstrap.min.css"
	var="bootstrapCss" />
<link href="${bootstrapCss}" rel="stylesheet" />
<link href="${coreCss}" rel="stylesheet" />
</head>

<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="#">Liste des pays</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>${title}</h1>
		<p>Bienvenue sur notre site internet !</p>
		<div class="row">
			<div class="col-lg-12">
				<c:if test="${not empty villes}">
					<form role="form" name="myform" id="myform" action="">
						<div class="form-group">
							<label for="pays">Pays :</label> 
							<select name="pays">
								<c:forEach var="ville" items="${villes}">
									<option value="${ville}">${ville}</option>
								</c:forEach>
							</select>
						</div>
						<button type="submit" class="btn btn-default">Envoyer</button>
					</form>
				</c:if>
			</div>
		</div>
	</div>
</div>

<div class="container">
	<c:if test="${not empty pays_sel}">
		<p>Pays selectionné : ${pays_sel}</p>
	</c:if>
	<hr>
	<footer>
		<p>&copy; Quentin Sébastien Jérémy 2015</p>
	</footer>
</div>

<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>