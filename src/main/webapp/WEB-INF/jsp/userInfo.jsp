<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css"
	href="../css/viewAdvertisement.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<fieldset >
						<legend class="formTitle">Datos del Publicador</legend>
					</fieldset>
					<div class="row">
						
						<div class="span4">
							<p>
								<span class="infoTitle">Email:</span>
								<c:out value="${user.email}" />
							</p>
							<p>
								<span class="infoTitle">Teléfono:</span>
								<c:out value="${user.phone}" />
							</p>
						</div>
					</div>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>