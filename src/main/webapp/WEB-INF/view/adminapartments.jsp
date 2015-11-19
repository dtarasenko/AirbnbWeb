<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@ page import="java.util.List"%>

<%@ page import="com.gojava6.airbnb.services.ApartmentService"%>
<%@ page import="com.gojava6.airbnb.model.Apartment"%>

    <section>
        <%
            ApplicationContext context = new ClassPathXmlApplicationContext("Beans.xml");
            ApartmentService apartmentService = (ApartmentService) context.getBean("apartmentService");
            List<Apartment> apartmentList = apartmentService.getApartmentList();

             for (Apartment apartment : apartmentList) {
        %>
            <p><%= apartment.toString() %></p>
        <% } %>
    </section>