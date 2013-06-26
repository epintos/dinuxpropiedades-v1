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
					<div class="row">
						<div class="span4">
							<ul class="thumbnails">
								<li class="span4"><a href='<c:url value="#"/>'
									class="thumbnail"> <c:if test="${empty mainPhoto}">
											<img class="mainPhoto" src="../img/defaultImg.jpg" alt="">
										</c:if> <c:if test="${not empty mainPhoto}">
											<img class="mainPhoto"
												src="../public/photoServer?photoId=${mainPhoto}" alt="">
										</c:if></a>
									<p>
										<span class="infoTitle">Descripción:</span>
									</p>
									<p>
										<c:out value="${property.description}" />
									</p></li>
								<c:set var="map"
									value="http://maps.google.com/maps/api/staticmap?center=${property.street} ${property.numbering}, ${property.neighbourhood}&zoom=14&size=300x268&markers=${property.street} ${property.numbering}, ${property.neighbourhood}&sensor=false" />
								<li class="span4"><a class="thumbnail" target="_blank"
									href="http://mapof.it/${property.street} ${property.numbering}, ${property.neighbourhood}, Argentina/"><img
										src='${map}' alt=""></a>
							</ul>
						</div>
						<div class="span4">
							<p>
								<span class="infoTitle">Operación:</span>
								<c:set var="str" value="sale" />
								<c:if test="${property.operationType eq str}">
											Venta
										</c:if>
								<c:set var="str" value="rent" />
								<c:if test="${property.operationType eq str}">
											Alquiler
										</c:if>
							</p>
							<p>
								<span class="infoTitle">Inmueble:</span>
								<c:set var="str" value="house" />
								<c:if test="${property.propertyType eq str}">
											Casa
										</c:if>
								<c:set var="str" value="apartment" />
								<c:if test="${property.propertyType eq str}">
											Departamento
										</c:if>
							</p>
							<p>
								<span class="infoTitle">Dirección:</span>
								<c:out value="${property.street}" />
								<c:out value="${property.numbering}" />
								<c:if test="${not empty property.floor}">
									<span class="infoTitle">Piso:</span>
									<c:out value="${property.floor}" />
									<br>
									<span class="infoTitle">Dep</span>: <c:out
										value="${property.apartment}" />
								</c:if>
							</p>
							<p>
								<span class="infoTitle">Barrio:</span>
								<c:out value="${property.neighbourhood}" />
							</p>
							<p>
								<span class="infoTitle">Superficie Cubierta:</span>
								<c:out value="${property.coveredsurface}" />
								m2
							</p>
							<p>
								<span class="infoTitle">Superficie Descubierta:</span>
								<c:out value="${property.uncoveredsurface}" />
								m2
							</p>
							<p>
								<span class="infoTitle">Habitaciones:</span>
								<c:out value="${property.rooms}" />
							</p>
							<p>
								<span class="infoTitle">Antigüedad:</span>
								<c:out value="${property.age}" />
								año/s
							</p>

							<c:if test="${empty servicesList }">
								<p>
									<span class="infoTitle">Servicios:</span> No posee.
								</p>
							</c:if>
							<c:if test="${not empty servicesList}">
								<p>
									<span class="infoTitle">Servicios:</span>
								</p>
								<ul>
									<c:forEach var="service" items="${servicesList}">
										<li><c:out value="${service.name}" /></li>
									</c:forEach>
								</ul>
							</c:if>

							<p>
								<span class="infoTitle">Precio:</span> U$D
								<c:out value="${property.price}" />
							</p>
						</div>

						<div class="span9">

							<fieldset>
								<legend class="formTitle">Fotos</legend>
							</fieldset>
							<c:if test="${empty photos}">
								<p>No hay más fotos cargadas.</p>
							</c:if>
							<ul class="photos">
								<c:forEach var="photo" items="${photos}">
									<li class="span2"><a
										href="../public/photoServer?photoId=${photo}" target="_blank"
										class="thumbnail"> <img
											src="../public/photoServer?photoId=${photo}" alt="">
									</a></li>
								</c:forEach>
							</ul>
						</div>
						<c:if
							test="${ not empty sessionManager.user.id && property.userId == sessionManager.user.id}">
							<div class="span9">
								<c:set var="editPhotos"
									value="../private/editPhotos?id=${property.id}" scope="request" />
								<i class=" icon-picture icon-white"></i> <a
									href="<c:url value='${editPhotos}'/>">Editar Fotos</a>
							</div>
						</c:if>
						<c:if
							test="${( not empty sessionManager.user.id && property.userId != sessionManager.user.id) || empty sessionManager.user.id }">
							<div class="span9">
								<form class="form-vertical"
									action='<c:url value="../public/viewAdvertisement?id=${property.id}"/>'
									method="post">
									<fieldset>
										<legend class="formTitle">¿Estás interesado?</legend>

										<div class="row formRow">
											<c:if test="${empty sessionManager.user}">
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Nombre</label>
														<div class="controls">
															<input type="text" name="name" class="span2"
																value="<c:out value='${name}' />"><span
																class="required"> *</span>
															<p class="error">
																&nbsp
																<c:out value="${nameError}" />
															</p>
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Email</label>
														<div class="controls">
															<input type="text" name="email" class="span2"
																placeholder="Ej: a@mail.com"
																value="<c:out value='${email}' />"><span
																class="required"> *</span>
															<p class="error">
																&nbsp
																<c:out value="${emailError}" />
															</p>
														</div>
													</div>
												</div>
												<div class="span3">
													<div class="control-group">
														<label class="control-label" for="input01">Teléfono</label>
														<div class="controls">
															<input type="text" name="phone" class="span2"
																placeholder="Ej: 4-345-3456"
																value="<c:out value='${phone}' />"><span
																class="required"> *</span>
															<p class="error">
																&nbsp
																<c:out value="${phoneError}" />
															</p>
														</div>
													</div>
												</div>
											</c:if>
											<c:if test="${not empty sessionManager.user}">
												<INPUT type=hidden value="${sessionManager.user.name}"
													name="name" />
												<INPUT type=hidden value="${sessionManager.user.email}"
													name="email" />
												<INPUT type=hidden value="${sessionManager.user.phone}"
													name="phone" />
											</c:if>
											<div class="span7">
												<div class="control-group">
													<label class="control-label" for="input01">Comentario</label>
													<div class="controls">
														<textarea class="input x-large" id="description" rows="3"
															cols="9" name="comment"><c:out value='${comment}' /></textarea>
														<p class="error">
															&nbsp
															<c:out value="${commentError}" />
														</p>
														<INPUT type=hidden value="${property.id}"
															name="propertyId" /> <INPUT type=hidden
															value="${property.operationType}" name="operationType" />
													</div>
													<button type="submit" class="btn btn-primary">Enviar</button>

													<span class="required">(*) Campos obligatorios.</span>
												</div>
											</div>
										</div>
									</fieldset>
								</form>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>