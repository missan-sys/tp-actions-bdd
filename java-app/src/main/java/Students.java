package src.main.java;

import java.sql.*;

public class Students {

    private static final String URL = "jdbc:postgresql://localhost:5432/tp";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void addStudent(String name, double grade) throws SQLException {
        String sql = "INSERT INTO students (name, grade) VALUES (?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, name);
            stmt.setDouble(2, grade);
            stmt.executeUpdate();
        }
    }

    public static String getStudent(int id) throws SQLException {
        String sql = "SELECT name FROM students WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next() ? rs.getString("name") : null;
            }
        }
    }

    public static double averageGrade() throws SQLException {
        String sql = "SELECT AVG(grade) AS average FROM students";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            return rs.next() ? rs.getDouble("average") : 0;
        }
    }
}
