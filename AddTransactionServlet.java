import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
@WebServlet("/submitTransaction")
public class AddTransactionServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String description = request.getParameter("description");
        double amount = Double.parseDouble(request.getParameter("amount"));
        String type = request.getParameter("type");
        Date date = Date.valueOf(request.getParameter("date"));

        try {
            addTransaction(description, amount, type, date);
        } catch (SQLException e) {
            throw new ServletException("Error adding transaction", e);
        }

        response.sendRedirect("transactionSuccess.html");
    }

    private void addTransaction(String description, double amount, String type, Date date) throws SQLException {
        String sql = "INSERT INTO transactions (description, amount, type, date) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, description);
            pstmt.setDouble(2, amount);
            pstmt.setString(3, type);
            pstmt.setDate(4, date);
            pstmt.executeUpdate();
        }
    }
}
