package com.example.firstwebapp.day2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/atm-servlet")
public class AtmServlet extends HttpServlet {
    private Object lock = new Object();
    private int balance = 1000;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean flag = true;
        String trans = req.getParameter("trans");
        int amount = Integer.parseInt(req.getParameter("amount"));
        switch (trans){
            case "withdraw":
                try {
                   flag = withdraw(amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            case "deposit":
                try {
                    deposit(amount);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                break;
            default:resp.getWriter().println("No transaction method given");
        }
        if (flag){
            resp.getWriter().println(balance + "balance left");
        }

        else {
            resp.getWriter().println("Not enough balance");
        }

    }
    private   boolean  withdraw(int amount) throws InterruptedException {
        synchronized (lock){
            if (balance < amount){

                return false;
            }
            Thread.sleep(5000);
            balance -= amount;
        }

        return true;



    }
    private void deposit(int amount) throws InterruptedException {
        Thread.sleep(5000);
        balance += amount;
    }
}
