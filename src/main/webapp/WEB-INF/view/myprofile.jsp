<section>
    <div class="contactBlock fadeIn">
        <hr>
        <h3>My Profile</h3>

        <form action="update_profile" method="POST">
            <p><label for="name" size="20">Name:</label><input type="text" id="name" name="name" required placeholder="${user.getName()}"></p>
            <p><label for="surname" size="20">Surname:</label><input type="text" id="surname" name="surname" required placeholder="${user.getSurname()}"></p>
            <p><label for="email">Email:</label><input type="email" id="email" name="email" required placeholder="${user.getEmail()}"></p>
            <p><label for="password">Password:</label><input type="password" id="password" name="password" placeholder="******" required></p>
            <p><label for="confirm_password">Confirm password:</label><input type="password" id="confirm_password" name="confirm_password" placeholder="******" required></p>
            <button type="submit">Book!</button>
        </form>

        <hr>
    </div>
</section>