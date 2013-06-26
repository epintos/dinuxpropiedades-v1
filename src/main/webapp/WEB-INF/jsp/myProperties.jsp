<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/searchResults.css">
<link rel="stylesheet" type="text/css" href="../css/welcome.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<%@ include file="advancedSearch.jsp"%>

			<div class="span8">
				<c:if test="${empty propertiesList}">
					<p>No se encontraron resultados.</p>
				</c:if>
				<c:forEach var="property" items="${propertiesList}">
					<div class="row">
						<div class="span8">

							<div class="hero-unit searchItem">
								<div class="row">
									<div class="span2">
										<ul class="thumbnails">
											<li class="span2"><a href='<c:url value="#"/>'
												class="thumbnail"><c:if
														test="${empty property.mainPhoto}">
														<img class="mainPhoto" src="../img/defaultImg.jpg" alt="">
													</c:if> <c:if test="${not empty  property.mainPhoto}">
														<img class="mainPhoto"
															src="../public/photoServer?photoId=${property.mainPhoto}"
															alt="">
													</c:if> </a></li>
										</ul>
									</div>
									<div class="span4">
										<span class="infoTitle">Estado:</span>
										<c:set var="str" value="available" />
										<c:if test="${property.status eq str}">
											Disponible
										</c:if>
										<c:set var="str" value="finished" />
										<c:if test="${property.status eq str}">
											Finalizado
										</c:if>
										<br> <span class="infoTitle">Operacion:</span>
										<c:set var="str" value="sale" />
										<c:if test="${property.operationType eq str}">
											Venta
										</c:if>
										<c:set var="str" value="rent" />
										<c:if test="${property.operationType eq str}">
											Alquiler
										</c:if>
										<br> <span class="infoTitle">Inmueble:</span>
										<c:set var="str" value="house" />
										<c:if test="${property.propertyType eq str}">
											Casa
										</c:if>
										<c:set var="str" value="apartment" />
										<c:if test="${property.propertyType eq str}">
											Departamento
										</c:if>
										<br> <span class="infoTitle">Dirección:</span>
										<c:out value="${property.street}" />
										<c:out value="${property.numbering}" />

										<c:if test="${not empty property.floor}">
											<span class="infoTitle">Piso:</span>
											<c:out value="${property.floor}" />
											<span class="infoTitle">Dep:</span>
											<c:out value="${property.apartment}" />
										</c:if>

										<br> <span class="infoTitle">Barrio:</span>
										<c:out value="${property.neighbourhood}" />
										<br> <span class="infoTitle">Precio:</span> U$D
										<c:out value="${property.price}" />
										<c:set var="more_information"
											value="../public/viewAdvertisement?id=${property.id}"
											scope="request" />
										<br> <i class="icon-plus-sign"></i> <a
											href="<c:url value='${more_information}'/>">Más
											Información</a> <br>
										<c:set var="edit_information"
											value="editInformation?id=${property.id}"
											scope="request" />
										<i class="icon-edit"></i> <a
											href="<c:url value='${edit_information}'/>">Editar
											Información</a><br>
										<c:set var="editPhotos"
											value="../private/editPhotos?id=${property.id}"
											scope="request" />
										<i class=" icon-picture"></i> <a
											href="<c:url value='${editPhotos}'/>">Editar Fotos</a>
										<c:set var="modifyStatus"
											value="modifyStatus?id=${property.id}&status=${property.status}"
											scope="request" />
										<br>
										<c:set var="str" value="available" />
										<c:if test="${property.status eq str}">
											<i class="icon-check"></i>
											<a href="<c:url value='${modifyStatus}'/>">Finalizar</a>
											<br>
										</c:if>
										<c:set var="str" value="finished" />
										<c:if test="${property.status eq str}">

											<i class="icon-share"></i>
											<a href="<c:url value='${modifyStatus}'/>">Anunciar</a>
											<br>
										</c:if>
										<br>
									</div>
									<div class="span3"></div>
								</div>

							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>

		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>