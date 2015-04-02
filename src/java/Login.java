import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
@WebServlet("/Login")

public class Login extends HttpServlet {

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

        String password = request.getParameter("password");
        for (int i = 0; i < 10; i++) {
            System.out.println(email + password);
        }

        try (PrintWriter out = response.getWriter()) {
            try {

                Class.forName("com.mysql.jdbc.Driver");
                java.util.Properties sysprops = System.getProperties();
                sysprops.put("user", "root");
                sysprops.put("password", "sunny");
                //connect to the database

                Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/school", sysprops);
                Statement st = con.createStatement();

                String query = "SELECT * FROM  user  WHERE email = \"" + email + "\" ;";
                System.out.println(query);
                ResultSet rs = st.executeQuery(query);

                if (rs.next()) {
                    System.out.println("The user is " + rs.getString("name") + " \nIs in the database");
                    if (rs.getString("password").equals(password)) {
                        out.println("Welcome User "+ rs.getString("name"));
                    } else {
                        out.println("Invalid Login, password");
                    }
                } else {
                    System.out.println("The user is " + email + " \nIs in the database");

                    out.println("Invalid Login, name");
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                System.out.println("error");
            }

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
