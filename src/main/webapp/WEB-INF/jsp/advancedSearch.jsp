<div class="span4" id="search">
	<div class="hero-unit">
		<form class="form-vertical" action='<c:url value="../public/search"/>'
			method="GET">
			<fieldset>
				<legend>Búsqueda</legend>
				<div class="control-group">
					<label class="control-label" for="select01">Operación:</label>
					<div class="controls">
						<select name="operation" class="span2">
							<c:set var="str" value="all" />
							<c:if test="${operationType eq str}">
								<option value="all" selected>Todos</option>
								<option value="sale">Venta</option>
								<option value="rent">Alquiler</option>
							</c:if>
							<c:set var="str" value="sale" />
							<c:if test="${operationType eq str}">
								<option value="all">Todos</option>
								<option value="sale" selected>Venta</option>
								<option value="rent">Alquiler</option>
							</c:if>
							<c:set var="str" value="rent" />
							<c:if test="${operationType eq str}">
								<option value="all">Todos</option>
								<option value="sale">Venta</option>
								<option value="rent" selected>Alquiler</option>
							</c:if>

						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="select01">Inmueble:</label>
					<div class="controls">
						<select name="property" class="span2">
							<c:set var="str" value="all" />
							<c:if test="${propertyType eq str}">
								<option value="all" selected>Todos</option>
								<option value="apartment">Departamento</option>
								<option value="house">Casa</option>
							</c:if>
							<c:set var="str" value="house" />
							<c:if test="${propertyType eq str}">
								<option value="all">Todos</option>
								<option value="apartment">Departamento</option>
								<option value="house" selected>Casa</option>
							</c:if>
							<c:set var="str" value="apartment" />
							<c:if test="${propertyType eq str}">
								<option value="all">Todos</option>
								<option value="apartment" selected>Departamento</option>
								<option value="house">Casa</option>
							</c:if>

						</select>
					</div>
				</div>
				<div class="control-group">
					<label class="control-label" for="input01">Precio (U$D):</label>
					<div class="controls">
						<input type="text" name="priceFrom" placeholder="desde"
							class="span2" value="<c:out value='${priceFrom}'/>">
						<p class="error">
							<c:out value="${priceFromError}" />
						</p>
						<input type="text" name="priceTo" placeholder="hasta"
							class="span2" value="<c:out value='${priceTo}'/>">
						<p class="error">
							<c:out value="${priceToError}" />
						</p>
					</div>

				</div>
				<div class="control-group">
					<div class="controls">
						<button type="submit" class="btn btn-primary">Buscar</button>
					</div>
				</div>
			</fieldset>
		</form>
	</div>
</div>