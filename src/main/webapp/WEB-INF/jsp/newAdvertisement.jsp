<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css"
	href="../css/newAdvertisement.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<form class="form-vertical"
						action='<c:url value="../private/newAdvertisement"/>'
						method="POST">
						<fieldset>
							<legend>Aviso nuevo</legend>
							<div class="row formRow">
								<div class="span4">
									<div class="control-group">
										<label class="control-label formItem" for="select01">Inmueble:
											<span class="required"> *</span>
										</label>
										<div class="controls">
											<select id="tipoProp" name="propertyType">
												<c:set var="str" value="house" />
												<c:if test="${propertyType eq str}">
													<option value="house" selected>Casa</option>
													<option value="apartment">Departamento</option>
												</c:if>
												<c:set var="str" value="apartment" />
												<c:if test="${propertyType eq str}">
													<option value="house">Casa</option>
													<option value="apartment" selected>Departamento</option>
												</c:if>
												<c:if test="${empty propertyType}">
													<option value="house">Casa</option>
													<option value="apartment">Departamento</option>
												</c:if>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="select01">Operación:
											<span class="required"> *</span>
										</label>
										<div class="controls">
											<select id="tipoProp" name="operationType">
												<c:set var="str" value="sale" />
												<c:if test="${operationType eq str}">
													<option value="sale" selected>Venta</option>
													<option value="rent">Alquiler</option>
												</c:if>
												<c:set var="str" value="rent" />
												<c:if test="${operationType eq str}">
													<option value="sale">Venta</option>
													<option value="rent" selected>Alquiler</option>
												</c:if>
												<c:if test="${empty operationType}">
													<option value="sale">Venta</option>
													<option value="rent">Alquiler</option>
												</c:if>
											</select>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Dirección:</label>
										<div class="controls docs-input-sizes">
											<input class="span3" type="text" name="street"
												placeholder="Calle" value="<c:out value="${street}" />">
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${streetError}" />
											</p>
											<input class="span3" type="text" name="numbering"
												placeholder="Número" value="<c:out value="${numbering}" />">
											<span class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${numberingError}" />
											</p>
											<input class="span3" type="text" name="floor"
												placeholder="Piso" value="<c:out value="${floor}" />">
											<span class="required"> **</span>
											<p class="error">
												&nbsp
												<c:out value="${floorError}" />
											</p>

											<input class="span3" type="text" name="apartment"
												placeholder="Departamento"
												value="<c:out value="${apartment}" />"> <span
												class="required"> **</span>
											<p class="error">
												&nbsp
												<c:out value="${apartmentError}" />
											</p>
											<input class="
											span3" type="text"
												name="neighbourhood" placeholder="Barrio"
												value="<c:out value="${neighbourhood}" />"> <span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${neighbourhoodError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Precio
											(U$D):</label>
										<div class="controls">
											<input class="span3" type="text" name="price"
												value="<c:out value="${price}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${priceError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Cantidad
											de ambientes:</label>
										<div class="controls">
											<input class="span3" type="text" name="rooms"
												value="<c:out value="${rooms}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${roomsError}" />
											</p>
										</div>
										<span class="required">(*) Campo obligatorio.</span><br>
										<span class="required">(**) Campo obligatorio para
											departamentos.</span>
									</div>
								</div>
								<div class="span4">
									<div class="control-group">
										<label class="control-label formItem" for="input01">Antigüedad
											(año/s):</label>
										<div class="controls">
											<input class="span3" type="text" name="age"
												value="<c:out value="${age}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${ageError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Superficie
											cubierta (m2):</label>
										<div class="controls">
											<input class="span3" type="text" name="coveredSurface"
												value="<c:out value="${coveredSurface}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${coveredSurfaceError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Superficie
											descubierta (m2):</label>
										<div class="controls">
											<input class="span3" type="text" name="uncoveredSurface"
												value="<c:out value="${uncoveredSurface}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${uncoveredSurfaceError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Servicios:</label>
										<div class="controls">
											<label class="checkbox"> <c:if
													test="${not empty cable}">
													<input type="checkbox" name="cable" checked>
												</c:if> <c:if test="${empty cable}">
													<input type="checkbox" name="cable">
												</c:if> Cable
											</label> <label class="checkbox"><c:if
													test="${not empty telefono}">
													<input type="checkbox" name="telefono" checked>
												</c:if> <c:if test="${empty telefono}">
													<input type="checkbox" name="telefono">
												</c:if>Teléfono </label> <label class="checkbox"> <c:if
													test="${not empty pileta}">
													<input type="checkbox" name="pileta" checked>
												</c:if> <c:if test="${empty pileta}">
													<input type="checkbox" name="pileta">
												</c:if>Pileta
											</label> <label class="checkbox"> <c:if
													test="${not empty salon}">
													<input type="checkbox" name="salon" checked>
												</c:if> <c:if test="${empty salon}">
													<input type="checkbox" name="salon">
												</c:if>Salón
											</label> <label class="checkbox"> <c:if
													test="${not empty canchaDePaddle}">
													<input type="checkbox" name="cancha de paddle" checked>
												</c:if> <c:if test="${empty canchaDePaddle}">
													<input type="checkbox" name="cancha de paddle">
												</c:if>Cancha de paddle
											</label> <label class="checkbox"> <c:if
													test="${not empty quincho}">
													<input type="checkbox" name="quincho" checked>
												</c:if> <c:if test="${empty quincho}">
													<input type="checkbox" name="quincho">
												</c:if>Quincho
											</label>
										</div>
									</div>
									<div class="control-group">
										<label class="control-label formItem" for="input01">Descripción:</label>
										<div class="controls">
											<textarea class="input x-large" id="description" rows="3"
												name="description">
												<c:out value="${description}" />
											</textarea>
											<p class="error">
												&nbsp
												<c:out value="${descriptionError}" />
											</p>
										</div>
									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Crear
												Aviso</button>
										</div>
									</div>

								</div>
							</div>
						</fieldset>
					</form>
				</div>
			</div>
		</div>

	</div>
</body>
</html>