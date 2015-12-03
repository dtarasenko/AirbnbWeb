<section>
    <table>
        <thead>
            <tr>
                <td>Id</td>
                <td>Start date</td>
                <td>End date</td>
                <td>User id</td>
                <td>Apartment id</td>
            </tr>
        </thead>
        <tbody>

        <c:forEach var="reservation" items="${reservationList}">

            <tr>
                <td>${reservation.getReservationId()}</td>
                <td>${reservation.getStart()}</td>
                <td>${reservation.getEnd()}</td>
                <td>${reservation.getUser().getUserId()}</td>
                <td>${reservation.getApartment().getApartmentId()}</td>

            </tr>

        </c:forEach>

        </tbody>
    </table>
</section>