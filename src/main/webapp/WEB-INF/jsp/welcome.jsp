<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/welcome.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Propiedades</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<%@ include file="advancedSearch.jsp"%>
			<div class="span7">
				<ul class="thumbnails">
					<li class="span7"><img class="welcomeImg" src="../img/welcomeImage.jpg">
					</li>
				</ul>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>
</body>
</html>