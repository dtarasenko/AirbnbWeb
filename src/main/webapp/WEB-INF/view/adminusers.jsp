<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List"%>

<%@ page import="com.gojava6.airbnb.services.UserService"%>
<%@ page import="com.gojava6.airbnb.model.User"%>

<section>
    <%
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        UserService userService = (UserService) context.getBean("userService");
        List<User> userList = userService.getUserList();

        for (User user : userList) {
    %>
        <p><%= user.toString() %></p>
    <% } %>
</section>