import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;

class StudentDAO {

    // ADD STUDENT
    public void addStudent(String name, int age,
            String department, String email)
            throws SQLException {

        String query = "INSERT INTO students(name, age, department, email) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);
            ps.setString(4, email);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student Added Successfully!");
            }
        }
    }

    // VIEW ALL STUDENTS
    public void viewStudents(int sortChoice) throws SQLException {

        String query;

        switch (sortChoice) {
            case 1:
                query = "SELECT * FROM students ORDER BY id ASC";
                break;

            case 2:
                query = "SELECT * FROM students ORDER BY name ASC";
                break;

            case 3:
                query = "SELECT * FROM students ORDER BY age ASC";
                break;

            case 4:
                query = "SELECT * FROM students ORDER BY age DESC";
                break;

            default:
                System.out.println("Invalid sorting choice!");
                return;
        }
        
        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            System.out.printf("%-5s %-15s %-6s %-15s %-25s%n",
                    "ID", "Name", "Age", "Department", "Email");

            System.out.println("--------------------------------------------------------------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;

                System.out.printf("%-5d %-15s %-6d %-15s %-25s%n",
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("department"),
                        rs.getString("email"));
            }

            if (!found) {
                System.out.println("No Students Found!");
            }
        }
    }

    // SEARCH STUDENT
    public void searchStudent(int id) throws SQLException {

        String query = "SELECT * FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    System.out.println("Student Found!");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Age: " + rs.getInt("age"));
                    System.out.println("Department: " + rs.getString("department"));
                    System.out.println("Email: " + rs.getString("email"));

                } else {
                    System.out.println("Student Not Found!");
                }
            }
        }
    }

    // UPDATE STUDENT
    public void updateStudent(int id, String name, int age,
            String department, String email)
            throws SQLException {

        String query = "UPDATE students SET name=?, age=?, department=?, email=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setInt(2, age);
            ps.setString(3, department);
            ps.setString(4, email);
            ps.setInt(5, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student Updated Successfully!");
            } else {
                System.out.println("Student ID Not Found!");
            }
        }
    }

    // DELETE STUDENT
    public void deleteStudent(int id) throws SQLException {

        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setInt(1, id);

            int result = ps.executeUpdate();

            if (result > 0) {
                System.out.println("Student Deleted Successfully!");
            } else {
                System.out.println("Student ID Not Found!");
            }
        }
    }

    // CHECK DUPLICATE EMAIL
    public boolean emailExists(String email) throws SQLException {

        String query = "SELECT id FROM students WHERE email=?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    // CHECK EMAIL FOR OTHER STUDENTS
    public boolean emailExistsForOtherStudent(String email, int id)
            throws SQLException {

        String query = "SELECT id FROM students WHERE email=? AND id<>?";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, email);
            ps.setInt(2, id);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }
    
    public void searchStudentByName(String name) throws SQLException {

        String query = "SELECT * FROM students WHERE LOWER(name) LIKE LOWER(?)";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, "%" + name + "%");

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\n===== SEARCH RESULTS =====");

                System.out.printf("%-5s %-15s %-6s %-15s %-25s%n",
                        "ID", "Name", "Age", "Department", "Email");

                System.out.println("--------------------------------------------------------------------------");

                while (rs.next()) {
                    found = true;

                    System.out.printf("%-5d %-15s %-6d %-15s %-25s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("department"),
                            rs.getString("email"));
                }

                if (!found) {
                    System.out.println("No Student Found with this Name!");
                }
            }
        }
    }
    
    public void searchStudentByDepartment(String department) throws SQLException {

        String query = "SELECT * FROM students WHERE LOWER(department) = LOWER(?)";

        try (Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(query)) {

            ps.setString(1, department);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println("\n===== SEARCH RESULTS =====");

                System.out.printf("%-5s %-15s %-6s %-15s %-25s%n",
                        "ID", "Name", "Age", "Department", "Email");

                System.out.println("--------------------------------------------------------------------------");

                while (rs.next()) {
                    found = true;

                    System.out.printf("%-5d %-15s %-6d %-15s %-25s%n",
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("age"),
                            rs.getString("department"),
                            rs.getString("email"));
                }

                if (!found) {
                    System.out.println("No Students Found in this Department!");
                }
            }
        }
    }
    
    public int getTotalStudents() throws SQLException {

        String query = "SELECT COUNT(*) FROM students";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                return rs.getInt(1);
            }
        }

        return 0;
    }
    
    public void getDepartmentStatistics() throws SQLException {

        String query = "SELECT department, COUNT(*) AS total " +
                "FROM students GROUP BY department";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            System.out.println("\nDepartment-wise Student Count");
            System.out.println("--------------------------------");

            while (rs.next()) {
                System.out.printf("%-15s %d%n",
                        rs.getString("department"),
                        rs.getInt("total"));
            }
        }
    }
    
    public double getAverageAge() throws SQLException {

        String query = "SELECT AVG(age) FROM students";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query)) {

            if (rs.next()) {
                return rs.getDouble(1);
            }
        }

        return 0;
    }
    
    public void exportStudentsToCSV() {

        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
                Statement st = con.createStatement();
                ResultSet rs = st.executeQuery(query);
                FileWriter writer = new FileWriter("students.csv")) {

            writer.write("ID,Name,Age,Department,Email\n");

            while (rs.next()) {

                writer.write(
                        rs.getInt("id") + "," +
                                escapeCSV(rs.getString("name")) + "," +
                                rs.getInt("age") + "," +
                                escapeCSV(rs.getString("department")) + "," +
                                escapeCSV(rs.getString("email")) + "\n"              );
            }

            System.out.println("Student records exported successfully!");

        } catch (SQLException | IOException e) {
            System.out.println("Error exporting student records: " + e.getMessage());
        }
    }
    public void importStudentsFromCSV() {

        String fileName = "students.csv";
        int successCount = 0;
        int skippedCount = 0;
    
        String query = "INSERT INTO students (name, age, department, email) VALUES (?, ?, ?, ?)";
    
        try (Connection con = DBConnection.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(fileName));
             PreparedStatement ps = con.prepareStatement(query)) {
    
            String line;
    
            // Skip CSV header
            reader.readLine();
    
            while ((line = reader.readLine()) != null) {
    
                String[] data = parseCSVLine(line);
    
                if (data.length != 5) {
                    skippedCount++;
                    continue;
                }
    
                String name = data[1].trim();
                String department = data[3].trim();
                String email = data[4].trim();
    
                int age;
    
                // Handle invalid age separately
                try {
                    age = Integer.parseInt(data[2].trim());
                } catch (NumberFormatException e) {
                    skippedCount++;
                    continue;
                }
    
                // Validate student details
                if (!StudentValidator.isValidName(name)
                        || age < 16 || age > 100
                        || !StudentValidator.isValidDepartment(department)
                        || !StudentValidator.isValidEmail(email)
                        || emailExists(email)) {
    
                    skippedCount++;
                    continue;
                }
    
                ps.setString(1, name);
                ps.setInt(2, age);
                ps.setString(3, department);
                ps.setString(4, email);
    
                ps.executeUpdate();
                successCount++;
            }
    
            System.out.println("\nCSV Import Completed!");
            System.out.println("Successfully Imported : " + successCount);
            System.out.println("Skipped Records       : " + skippedCount);
    
        } catch (IOException | SQLException e) {
    
            System.out.println("Import Error: " + e.getMessage());
    
        }
    }
    
    private String escapeCSV(String value) {
        return "\"" + value.replace("\"", "\"\"") + "\"";
    }
    
    private String[] parseCSVLine(String line) {

        java.util.List<String> values = new java.util.ArrayList<>();
        StringBuilder current = new StringBuilder();

        boolean insideQuotes = false;

        for (int i = 0; i < line.length(); i++) {

            char ch = line.charAt(i);

            if (ch == '"') {

                if (insideQuotes && i + 1 < line.length()
                        && line.charAt(i + 1) == '"') {

                    current.append('"');
                    i++;

                } else {
                    insideQuotes = !insideQuotes;
                }

            } else if (ch == ',' && !insideQuotes) {

                values.add(current.toString());
                current.setLength(0);

            } else {
                current.append(ch);
            }
        }

        values.add(current.toString());

        return values.toArray(new String[0]);
    }
}

