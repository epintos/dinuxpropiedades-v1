<div class="row">
	<div class="span4 offset8 error">
		<c:if test="${param.wrongCredentials == 1}">
			<c:out value="Usuario o contraseña inválidos" />
		</c:if>
		<c:if
			test="${param.wrongCredentials == 0 || empty param.wrongCredentials}">
			&nbsp
		</c:if>
	</div>
</div>

<c:if test="${empty sessionManager.user}">
	<div class="row" id="header">
		<div class="span4">
			<a href="../public/welcome" class="logo">Dinux Propiedades</a>
		</div>
		<div class="span8">
			<div class="navbar">
				<div class="navbar-inner" id="logBar">
					<div class="container">
						<ul class="nav">
							<li><a href='<c:url value="../public/register"/>'>Regístrate</a></li>
						</ul>
						<ul class="nav">
							<li>
								<form class="form-inline" id="log_in"
									action='<c:url value="../public/login"/>' method="post">
									<input type="text" class="input-small" placeholder="Usuario"
										name="username" value="<c:out value="${ loginUsername}" />">
									<input type="password" class="input-small"
										placeholder="Contraseña" name="password"> <label
										class="checkbox remember_me"> <input type="checkbox"
										name="rememberUsername"> Recordar usuario<br> <input
										type="checkbox" name="rememberLogin"> Recordar acceso
									</label>
									<button type="submit" class="btn">Entrar</button>
								</form>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>

<c:if test="${not empty sessionManager.user }">
	<div class="row" id="header">
		<div class="span4">
			<a href='<c:url value="../public/welcome"/>' class="logo">Dinux
				Propiedades</a>
		</div>
		<div class="span8">
			<div class="navbar">
				<div class="navbar-inner">
					<div class="container">
						<ul class="nav pull-left">
						<a class="brand" href="#"> <c:out value='${sessionManager.user.username}'/> </a>
						</ul>
						<ul class="nav">
							<li><a href='<c:url value="../private/newAdvertisement"/>'>Crear
									Aviso</a></li>
							<li><a href='<c:url value="../private/myProperties"/>'>Mis
									Propiedades</a></li>
						</ul>
						<ul class="nav pull-right">
							<li><a href="../private/logout">Log Out</a></li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
</c:if>