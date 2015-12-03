<section>
    <table>
        <thead>
            <tr>
                <td>Id</td>
                <td>Description</td>
                <td>Type</td>
                <td>Number of guests</td>
                <td>Price</td>
                <td>User id</td>
                <td>City id</td>
            </tr>
        </thead>
        <tbody>

        <c:forEach var="apartment" items="${apartmentList}">

            <tr>
                <td>${apartment.getApartmentId()}</td>
                <td>${apartment.getApartmentDescription()}</td>
                <td>${apartment.getApartmentType()}</td>
                <td>${apartment.getNumberOfGuests()}</td>
                <td>${apartment.getPrice()}</td>
                <td>${apartment.getUser().getUserId()}</td>
                <td>${apartment.getCity().getCityId()}</td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
</section>