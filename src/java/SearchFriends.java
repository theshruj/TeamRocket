import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Rhododendron
 */
public class SearchFriends extends HttpServlet {

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
        String name = request.getParameter("name");
        JSONArray jsonArray = new JSONArray();

        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            Class.forName("com.mysql.jdbc.Driver");
            java.util.Properties sysprops = System.getProperties();
            sysprops.put("user", "root");
            sysprops.put("password", "sunny");
            //connect to the database

            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3310/school", sysprops);
            Statement st = con.createStatement();

            String query = "SELECT user.name FROM user,student WHERE user.email = student.email AND user.name = '" + name + "'";
            // AND student.approved='y'
            
            ResultSet rs = st.executeQuery(query);
            
            while (rs.next()) {
                JSONObject employeeToAdd = new JSONObject();
                employeeToAdd.put("name", rs.getString("name"));
                jsonArray.add(employeeToAdd);
            }
            System.out.println(jsonArray);
            //set the content type of our response
            response.setContentType("application/json");
            //printout prints it to our ajax call and it shows up there as data. you can use this data in the success function.

            out.print(jsonArray);
            out.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("error");
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
