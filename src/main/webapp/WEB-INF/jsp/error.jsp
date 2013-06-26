<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/error.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="span4 offset8 error">&nbsp</div>
		</div>
		<div class="row" id="header">
			<div class="span4">
				<a href="../public/welcome" class="logo">Dinux Propiedades</a>
			</div>

		</div>
		<div class="row" id="content">
			<div class="span4 offset2">
				<ul class="thumbnails">
					<li class="span7"><c:if test="${errorType == 404}">
							<img class="errorImg" src="../img/404error.jpg">
						</c:if> <c:if test="${errorType == 500}">
							<a href="<c:url value='../public/welcome'/>"> <img
								class="errorImg" src="../img/500Error.gif"></a>
						</c:if>
						 <c:if test="${errorType == 401}">
							<a href="<c:url value='../public/welcome'/>"> <img
								class="errorImg" src="../img/401error.gif"></a>
						</c:if></li>
				</ul>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>