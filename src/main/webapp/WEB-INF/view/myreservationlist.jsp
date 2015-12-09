<section>
    <div class="apartmentBlocks">
        <c:forEach var="reservation" items="${reservationList}">
            <div class="apartmentBlock fadeIn">

                <hr>

                <h3>${reservation.getApartment().getApartmentDescription()} in ${reservation.getApartment().getCity().getCityName()}</h3>
                <p>Check-in Date: ${reservation.getStart()}</p>
                <p>Check-out Date: ${reservation.getEnd()}</p>

                <p>Location: ${reservation.getApartment().getCity().getCityName()}</p>
                <p>Home Type: ${reservation.getApartment().getApartmentType()}</p>
                <p>Accommodates: ${reservation.getApartment().getNumberOfGuests()}</p>
                <p>Price: ${reservation.getApartment().getPrice()}</p>

                 <form action="delete_reservation" method="POST">
                     <input type="hidden" name="reservation_id" value="${reservation.getReservationId()}">
                     <button type="submit">Delete</button>
                 </form>

                <hr>

            </div>
        </c:forEach>
    </div>
</section>