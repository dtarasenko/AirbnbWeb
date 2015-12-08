<section>
    <div class="contactBlock fadeIn">
        <hr>
        <h3>My Profile</h3>

        <form action="update_profile" method="POST">

            <ul>
                <li>
                    <label for="name">Name:</label>
                    <input type="text" id="name" name="name" value="${user.getName()}">
                </li>
                <li>
                    <label for="surname">Surname:</label>
                    <input type="text" id="surname" name="surname" value="${user.getSurname()}">
                </li>
                <li>
                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.getEmail()}">
                </li>
                <li>
                    <label for="password">Password:</label>
                    <input type="text" id="password" name="password" value="${user.getPassword()}">
                </li>
            </ul>

            <button type="submit">Update</button>
        </form>

        <hr>
    </div>
</section>