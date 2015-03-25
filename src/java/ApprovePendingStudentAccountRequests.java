
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author rhododendron
 */
@WebServlet("/ApprovePendingStudentAccountRequests")
public class ApprovePendingStudentAccountRequests extends HttpServlet {

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
        Enumeration pendingAccounts = request.getParameterNames();
        String[] emails = request.getParameterValues("email");
        JSONArray jsonArray = new JSONArray();

        for(int i = 0; i < emails.length; i++){
            try (PrintWriter out = response.getWriter()) {
                /* TODO output your page here. You may use following sample code. */
                try {

                    Class.forName("com.mysql.jdbc.Driver");
                    java.util.Properties sysprops = System.getProperties();
                    sysprops.put("user", "root");
                    sysprops.put("password", "sunny");
                    //connect to the database

                    Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/school", sysprops);
                    Statement st = con.createStatement();

                    String query = "SELECT name,student.email FROM user,student "
                            + "WHERE user.email = student.email AND student.approved = 'n'"
                            + "AND student.email = '" + emails[i] + "'";

                    ResultSet rs = st.executeQuery(query);

                    while (rs.next()) {
                        JSONObject employeeToAdd = new JSONObject();
                        employeeToAdd.put("email", rs.getString("email"));
                        employeeToAdd.put("name", rs.getString("name"));
                        //System.out.println(rs.getString("name"));
                        jsonArray.add(employeeToAdd);
                    }

                    String updateQ = "UPDATE student "
                            + "SET approved = 'y' "
                            + "WHERE student.approved = 'n' AND student.email = '" + emails[i] + "'";

                    st.executeUpdate(updateQ);
                    out.println("The user is " + emails[i] + " \nThere is a world of ajax out here");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    System.out.println("error");
                }
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
