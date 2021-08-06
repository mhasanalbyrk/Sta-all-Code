package com.example.contactwebapp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet("/edit-contact-servlet")
public class EditContact extends HttpServlet {
    private static final String editContacts = "WEB-INF/edit-contact.jsp";
    private static final String jdbcUrl = "jdbc:mysql://localhost:3306/db_jss?useSSL=false";
    private static final String user = "dbuser";
    private static final String password = "dbuser123";
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("id" , req.getParameter("id"));
        Contact contactToEdited = new Contact(Integer.parseInt(req.getParameter("id")));
        ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, user, password);
        Connection cnn = connectionFactory.openConnection();
        List<Contact> contacs = contactToEdited.searchContact(contactToEdited, cnn);
        req.setAttribute("Contact", contacs.get(0));
        req.getRequestDispatcher(editContacts).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("name" ) == null){
            req.setAttribute("id" , req.getParameter("id"));
            req.getRequestDispatcher(editContacts).forward(req, resp);
        }else {
            Contact contactToEdited = new Contact(req.getParameter("name"), req.getParameter("number"), Integer.parseInt(req.getParameter("id")));
            ConnectionFactory connectionFactory = new ConnectionFactory(jdbcUrl, user, password);
            Connection cnn = connectionFactory.openConnection();
            contactToEdited.updateContact(contactToEdited, cnn);
            connectionFactory.closeConnection(cnn);
            req.getRequestDispatcher("WEB-INF/operation-succesful.jsp").forward(req, resp);
        }
    }
}
