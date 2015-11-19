<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List"%>

<%@ page import="com.gojava6.airbnb.services.ReservationService"%>
<%@ page import="com.gojava6.airbnb.model.Reservation"%>

<section>
    <%
        ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
        ReservationService reservationService = (ReservationService) context.getBean("reservationService");
        List<Reservation> reservationList = reservationService.getReservationList();

         for (Reservation reservation : reservationList) {
    %>
        <p><%= reservation.toString() %></p>
    <% } %>
</section>