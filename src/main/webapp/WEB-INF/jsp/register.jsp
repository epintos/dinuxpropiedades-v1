<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ include file="headerInfo.jsp"%>
<html lang="en">
<head>
<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="../css/header.css">
<link rel="stylesheet" type="text/css" href="../css/register.css">
<link rel="stylesheet" type="text/css" href="../css/style.css">
<title>Dinux Properties</title>
</head>
<body>
	<div class="container">
		<%@ include file="header.jsp"%>
		<div class="row" id="content">
			<div class="span10 offset1">
				<div class="hero-unit formHero">
					<form class="form-vertical"
						action='<c:url value="../public/register"/>' method="POST">
						<fieldset>
							<legend id="formTitle">Regístrate</legend>
							<div class="row formRow">
								<div class="span3 offset1">
									<div class="control-group">
										<label class="control-label" for="input01">Nombre:</label>
										<div class="controls">
											<input type="text" name="name" class="span2"
												value="<c:out value="${name}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${nameError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Apelido:</label>
										<div class="controls">
											<input type="text" name="surname" class="span2"
												value="<c:out value="${surname}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${surnameError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Email:</label>
										<div class="controls">
											<input type="text" name="emailRegister" class="span2"
												placeholder="Ej: user@domain.com"
												value="<c:out value="${emailRegister}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${emailRegisterError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Teléfono:</label>
										<div class="controls">
											<input type="text" name="phone" class="span2"
												placeholder="Ej: 4-345-3456"
												value="<c:out value="${phone}" />"> <span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${phoneError}" />
											</p>
										</div>
										<span class="required">(*) Campo obligatorio.</span>
									</div>
								</div>
								<div class="span3 offset1">
									<div class="control-group">
										<label class="control-label" for="input01">Nombre de
											Usuario:</label>
										<div class="controls">
											<input type="text" name="username" class="span2"
												value="<c:out value="${username}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${usernameError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Contraseña:</label>
										<div class="controls">
											<input type="password" name="password" class="span2"
												value="<c:out value="${password}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${passwordError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<label class="control-label" for="input01">Confirme
											Contraseña:</label>
										<div class="controls">
											<input type="password" name="passwordConfirm" class="span2"
												value="<c:out value="${passwordConfirm}" />"><span
												class="required"> *</span>
											<p class="error">
												&nbsp
												<c:out value="${passwordConfirmError}" />
											</p>
										</div>

									</div>
									<div class="control-group">
										<div class="controls">
											<button type="submit" class="btn btn-primary">Regístrate</button>
										</div>
									</div>
								</div>
							</div>


						</fieldset>
					</form>
				</div>
			</div>
		</div>
		<%@ include file="footer.jsp"%>
	</div>

</body>
</html>