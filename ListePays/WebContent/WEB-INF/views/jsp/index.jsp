<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
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
			<a class="navbar-brand" href="/ListePays">Liste des pays</a>
		</div>
	</div>
</nav>

<div class="jumbotron">
	<div class="container">
		<h1>Welcome!</h1>
		<p>
			Vous d�sirez savoir quelle est la capital d'un pays ainsi que sa
			population.<br /> N'h�sitez plus, choisissez vite le pays qui vous
			int�resse!
		</p>
		<div class="row">
			<div class="col-lg-12"></div>
		</div>
	</div>
</div>

<div class="container">
	<div class="row">
		<div class="col-md-6">
			<c:if test="${not empty pays}">
				<form role="form" name="myform" id="myform" action="">
					<div class="form-group">
						<label for="pays">Pays :</label> <select name="pays">
							<c:forEach var="un_pays" items="${pays}">
								<option value="${un_pays}">${un_pays}</option>
							</c:forEach>
						</select>
					</div>
					<button type="submit" class="btn btn-primary">Envoyer</button>
				</form>
			</c:if>
		</div>
		<div class="col-md-6">
			<c:if test="${not empty pays_sel}">
				<p>
					<b>Pays selectionn� :</b> ${pays_sel}
				</p>
			</c:if>
			<c:if test="${not empty capitale}">
				<p>
					<b>Capitale :</b> <span id="test">${capitale}</span>
				</p>
			</c:if>
			<c:if test="${not empty nb_habs}">
				<p>
					<b>Nombre d'habitants :</b>
					<fmt:formatNumber value="${nb_habs}" type="number" />
				</p>
			</c:if>
		</div>
	</div>

	<c:if test="${not empty capitale}">
		<div id="googleMap"></div>
	</c:if>

	<footer>
		<hr>
		<p>2015 &copy; Quentin &amp; S�bastien &amp; J�r�my</p>
	</footer>
</div>



<script src="http://maps.googleapis.com/maps/api/js?"></script>
<script>
	function initialize() {
		var geocoder, map;
		codeAddress(document.getElementById('test').innerHTML);
		function codeAddress(address) {
			geocoder = new google.maps.Geocoder();
			geocoder.geocode({
				'address' : address
			}, function(results, status) {
				if (status == google.maps.GeocoderStatus.OK) {
					var myOptions = {
						zoom : 6,
						center : results[0].geometry.location,
						mapTypeId : google.maps.MapTypeId.ROADMAP
					}
					map = new google.maps.Map(document
							.getElementById("googleMap"), myOptions);

					var marker = new google.maps.Marker({
						map : map,
						position : results[0].geometry.location
					});
				}
			});
		}
	}
	google.maps.event.addDomListener(window, 'load', initialize);
</script>


<spring:url value="/resources/core/css/hello.js" var="coreJs" />
<spring:url value="/resources/core/css/bootstrap.min.js"
	var="bootstrapJs" />

<script src="${coreJs}"></script>
<script src="${bootstrapJs}"></script>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>

</body>
</html>