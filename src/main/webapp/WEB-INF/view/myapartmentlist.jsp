<section>
    <div class="apartmentBlocks">
        <c:forEach var="apartment" items="${apartmentList}">
            <div class="apartmentBlock fadeIn">
                <hr>
                <a href="myapartment?apartmentId=${apartment.getApartmentId()}">
                    <h3>${apartment.getApartmentDescription()}</h3>
                    <p>City: ${apartment.getCity().getCityName()}</p>
                    <p>Guests: ${apartment.getNumberOfGuests()}</p>
                    <p>Apartment type: ${apartment.getApartmentType()}</p>
                    <p>Price: $${apartment.getPrice()}</p>
                </a>
               <hr>
            </div>
        </c:forEach>
    </div>
</section>