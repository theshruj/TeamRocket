/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author spari_000
 */
@WebServlet("/Register")

public class Register extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String email = request.getParameter("email");
        String name = request.getParameter("name");
        String password = request.getParameter("password");
        
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            try {
                System.out.println(email + "--");

                Class.forName("com.mysql.jdbc.Driver");
                java.util.Properties sysprops = System.getProperties();
                sysprops.put("user", "root");
                sysprops.put("password", "sunny");
                //connect to the database

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/school", sysprops);
                Statement st = con.createStatement();

                String query = "INSERT INTO user  " + "VALUES('" + email + "','" + name + "','" + password + "'," + "'s')";
                System.out.println(query);
                st.executeUpdate(query);
                
                query = "INSERT INTO student  " + "VALUES('" + email + "','" + "n')";
                System.out.println(query);
                st.executeUpdate(query);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("error");
            }

            out.println("The user is " + name + " \nThere is a world of ajax out here");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
