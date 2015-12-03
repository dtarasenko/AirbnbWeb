<section>
    <table>
        <thead>
            <tr>
                <td>Id</td>
                <td>Type</td>
                <td>Name</td>
                <td>Surname</td>
                <td>Email</td>
                <td>Password</td>
            </tr>
        </thead>
        <tbody>

        <c:forEach var="user" items="${userList}">

            <tr>
                <td>${user.getUserId()}</td>
                <td>${user.getUserType()}</td>
                <td>${user.getName()}</td>
                <td>${user.getSurname()}</td>
                <td>${user.getEmail()}</td>
                <td>${user.getPassword()}</td>
            </tr>

        </c:forEach>

        </tbody>
    </table>
</section>