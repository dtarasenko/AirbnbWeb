	<section>
		<div class="contactBlock fadeIn">
		    <hr>
			<h3>About Apartment</h3>
			<p>Description: ${apartment.getApartmentDescription()}</p>
			<p>Accommodates: ${apartment.getNumberOfGuests()}</p>
			<p>Home Type: ${apartment.getApartmentType()}</p>
            <hr>
            <h3>Host Contact Info</h3>
            <p>Name: ${apartment.getUser().getName()}</p>
            <p>Surname: ${apartment.getUser().getSurname()}</p>
            <p>Email: ${apartment.getUser().getEmail()}</p>
            <hr>
            <h3>Reservation Info</h3>
            <p>Check-in Date: ${startDate}</p>
            <p>Check-out Date: ${endDate}</p>
            <hr>
            <form action="reservation" method="GET">
                <input type="hidden" name="apartmentId" value="${apartment.getApartmentId()}">
                <input type="hidden" name="startDate" value="${startDate}">
                <input type="hidden" name="endDate" value="${endDate}">
                <button type="submit">Book!</button>
            </form>
        </div>
	</section>