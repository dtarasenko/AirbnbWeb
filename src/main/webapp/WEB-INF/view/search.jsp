	<section>
		<div class="apartmentBlocks">
			<c:forEach var="apartment" items="${apartmentList}">
				<div class="apartmentBlock fadeIn">
					<a href="contacts?hostId=${apartment.getUser().getUserId()}">
						<h3>${apartment.getApartmentDescription()}</h3>
						<p>City: ${apartment.getCity().getCityName()}</p>
						<p>Guests: ${apartment.getNumberOfGuests()}</p>
						<p>Apartment type: ${apartment.getApartmentType()}</p>
						<p>Price: $${apartment.getPrice()}</p>
					</a>
				</div>
			</c:forEach>
		</div>
	</section>