import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet("/viewTransactions")
public class ViewTransactionsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String url = "jdbc:mysql://localhost:3306/praveen";
        String user = "root";
        String password = "Praveen@123";

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(url, user, password);
            Statement stmt = conn.createStatement();
            String query = "SELECT * FROM transactions";
            ResultSet rs = stmt.executeQuery(query);

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>View Transactions</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 20px; background-color: #f4f4f9; }");
            out.println("h1 { color: #333; }");
            out.println("table { width: 100%; border-collapse: collapse; background-color: #fff; border-radius: 8px; overflow: hidden; box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); }");
            out.println("th, td { border: 1px solid #ddd; padding: 12px; text-align: left; }");
            out.println("th { background-color: #4CAF50; color: white; }");
            out.println("tr:nth-child(even) { background-color: #f2f2f2; }");
            out.println("tr:hover { background-color: #ddd; }");
            out.println("a { color: #007BFF; text-decoration: none; }");
            out.println("a:hover { text-decoration: underline; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Transaction List</h1>");
            out.println("<table>");
            out.println("<tr>");
            out.println("<th>Description</th>");
            out.println("<th>Amount</th>");
            out.println("<th>Type</th>");
            out.println("<th>Date</th>");
            out.println("</tr>");

            while (rs.next()) {
                out.println("<tr>");
                out.println("<td>" + rs.getString("description") + "</td>");
                out.println("<td>" + rs.getDouble("amount") + "</td>");
                out.println("<td>" + rs.getString("type") + "</td>");
                out.println("<td>" + rs.getDate("date") + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
            out.println("<br/>");
            out.println("<a href='addTransaction.html'>Back to Add Transaction</a>");
            out.println("</body>");
            out.println("</html>");

            rs.close();
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
    }
}
