<section>

	<div class="blocks">
		<c:forEach var="city" items="${cityList}">
			<div class="block fadeIn">
				<a href="search?city=${city.getCityName()}">${city.getCityName()}</a>
				<img src="<c:url value="/resources/img/cities/${city.getImgName()}" />" class="img">
			</div>
		</c:forEach>
	</div>

</section>
