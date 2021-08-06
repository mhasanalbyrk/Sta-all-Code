package com.example.contactwebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/search-contact-servlet")
public class SearchContactServlet extends HttpServlet {
    private static final String searchContact = "WEB-INF/search-contact.jsp";

    private static final String editContacts = "edit-contact";
    private static final String success = "WEB-INF/operation-succesful.jsp";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
    private static final String user = "dbuser";
    private static final String password = "dbuser123";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(searchContact).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameToSearch = req.getParameter("name");
        System.out.println(nameToSearch);
        Contact contactToSearch = new Contact(nameToSearch);
        ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, user, password);
        Connection cnn = connectionFactory.openConnection();
        List<Contact> contacs = contactToSearch.searchContact(contactToSearch, cnn);
        if (contacs.size() != 0){

            req.setAttribute("Contacts", contacs);
            req.getRequestDispatcher(searchContact).forward(req,resp);
        }else {
            resp.getWriter().println("Contact not found");
        }
        connectionFactory.closeConnection(cnn);

    }
}
