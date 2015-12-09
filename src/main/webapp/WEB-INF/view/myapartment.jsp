<section>
    <div class="contactBlock fadeIn">
        <hr>
        <h3>My Apartment</h3>

        <form action="update_apartment" method="POST">

            <input type="hidden" name="apartment_id" value="${apartment.getApartmentId()}">

            <ul>
                <li>
                    <label for="description">Description:</label>
                    <input type="text" id="description" name="description" value="${apartment.getApartmentDescription()}">
                </li>
                <li>
                    <label for="type">Home Type:</label>
                    <input type="text" id="type" name="type" value="${apartment.getApartmentType()}">
                </li>
                <li>
                    <label for="number_of_guests">Accommodates:</label>
                    <input type="text" id="number_of_guests" name="number_of_guests" value="${apartment.getNumberOfGuests()}">
                </li>
                <li>
                    <label for="price">Price:</label>
                    <input type="text" id="price" name="price" value="${apartment.getPrice()}">
                </li>
            </ul>

            <button type="submit">Update</button>
        </form>

        <hr>
    </div>
</section>