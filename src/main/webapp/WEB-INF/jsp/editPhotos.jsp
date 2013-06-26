<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css"
	href="../css/viewAdvertisement.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<fieldset>
						<legend class="formTitle">Agregar Foto</legend>
					</fieldset>
					<form class="form-vertical" enctype="multipart/form-data"
						action='<c:url value="../private/editPhotos?propertyId=${id}"/>'
						method="POST">
						<div class="control-group">
							<div class="controls">
								<input id="photoInput" class="span3" type="file" name="photo" accept="image/*">
							</div>
							<div class="controls">
								<button type="submit" class="btn btn-primary">Guardar
									Cambios</button>
								<p class="error">
									<c:out value="${MissingInformationError}" />
								</p>
								<p class="error">
									<c:out value="${BadInformationError}" />
								</p>
							</div>
						</div>
					</form>
					<fieldset>
						<legend class="formTitle">Eliminar Fotos</legend>
					</fieldset>
					<form class="form-horizontal"
						action='<c:url value="../private/deletePhotos?propertyId=${id}"/>'
						method="POST">
						<div class="control-group">
							<c:if test="${empty photos}">
								<p>No hay más fotos cargadas.</p>
							</c:if>
							<ul class="photos">
								<c:forEach var="photo" items="${photos}">
									<li class="span2"><img
										src="../public/photoServer?photoId=${photo}" alt=""> <input
										type="checkbox" name="photoId=${photo}"></li>
								</c:forEach>
							</ul>
						</div>
						<div class="controls" id="deletePhotos">
							<button type="submit" class="btn btn-primary" name="deletePhotos">Eliminar
								Fotos</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>