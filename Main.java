
import java.util.Scanner;
import java.sql.SQLException;

class Main {

    static void addStudent(Scanner sc, StudentDAO dao)
            throws SQLException {

        String name = readName(sc);
        int age = readAge(sc);
        String department = readDepartment(sc);
        String email = readEmail(sc);

        if (dao.emailExists(email)) {
            System.out.println("This email is already registered!");
            return;
        }

        dao.addStudent(name, age, department, email);
    }
    
    public static void searchStudent(Scanner sc, StudentDAO dao) throws SQLException {

        System.out.println("\n===== SEARCH STUDENT =====");
        System.out.println("1. Search by ID");
        System.out.println("2. Search by Name");
        System.out.println("3. Search by Department");
        System.out.print("Enter your choice: ");

        int searchChoice;

        if (sc.hasNextInt()) {
            searchChoice = sc.nextInt();
            sc.nextLine();
        } else {
            System.out.println("Invalid input! Please enter a number.");
            sc.nextLine();
            return;
        }

        if (searchChoice == 1) {

            System.out.println("Enter Student ID:");
            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a numeric Student ID.");
                sc.nextLine();
                return;
            }

            int id = sc.nextInt();
            sc.nextLine();
            if (id <= 0) {
                System.out.println("Invalid Student ID!");
                return;
            }

            dao.searchStudent(id);

        } else if (searchChoice == 2) {

            System.out.println("Enter Student Name:");
            String name = sc.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Student name cannot be empty!");
                return;
            }

            dao.searchStudentByName(name);

        } else if (searchChoice == 3) {

            System.out.println("Enter Department:");
            String department = sc.nextLine().trim();

            if (department.isEmpty()) {
                System.out.println("Department cannot be empty!");
                return;
            }

            dao.searchStudentByDepartment(department);
        }
        else {
            System.out.println("Invalid search choice!");
        } 
        
    }

    static void updateStudent(Scanner sc, StudentDAO dao) throws SQLException {
        System.out.println("Enter Student ID to Update:");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a numeric Student ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        if (id <= 0) {
            System.out.println("Invalid Student ID!");
            return;
        }

        String name = readName(sc);

        int age = readAge(sc);

        String department = readDepartment(sc);

        String email = readEmail(sc);

        if (dao.emailExistsForOtherStudent(email, id)) {
            System.out.println("Email already exists!");
            return;
        }

        dao.updateStudent(id, name, age, department, email);
    }
    
    static void deleteStudent(Scanner sc, StudentDAO dao)
            throws SQLException {

        System.out.println("Enter Student ID to Delete:");
        if (!sc.hasNextInt()) {
            System.out.println("Invalid input! Please enter a numeric Student ID.");
            sc.nextLine();
            return;
        }

        int id = sc.nextInt();
        sc.nextLine();

        if (id <= 0) {
            System.out.println("Invalid Student ID!");
            return;
        }

        System.out.println("Are you sure you want to delete this student? (Y/N):");
        String confirm = sc.nextLine();

        if (confirm.equalsIgnoreCase("Y")) {
            dao.deleteStudent(id);
        } else if (confirm.equalsIgnoreCase("N")) {
            System.out.println("Deletion Cancelled!");
        } else {
            System.out.println("Invalid Choice! Deletion Cancelled.");
        }
    }
    
    static int readAge(Scanner sc) {
        int age;

        while (true) {
            System.out.println("Enter age:");

            if (sc.hasNextInt()) {
                age = sc.nextInt();
                sc.nextLine();

                if (StudentValidator.isValidAge(age)
                        && age >= 16) {
                    return age;
                } else {
                    System.out.println(
                            "Age must be between 16 and 100.");
                }

            } else {
                System.out.println(
                        "Invalid input. Please enter a number.");
                sc.nextLine();
            }
        }
    }
    
    static String readEmail(Scanner sc) {

        String email;

        while (true) {
            System.out.println("Enter Email:");
            email = sc.nextLine();

            if (StudentValidator.isValidEmail(email)) {
                return email;
            } else {
                System.out.println(
                        "Invalid Email! Please enter a valid email.");
            }
        }
    }
    
    static String readName(Scanner sc) {

        String name;

        while (true) {
            System.out.println("Enter Name:");
            name = sc.nextLine();

            if (StudentValidator.isValidName(name)) {
                return name.trim();
            } else {
                System.out.println("Name cannot be empty!");
            }
        }
    }
    
    static String readDepartment(Scanner sc) {

        String department;

        while (true) {
            System.out.println("Enter Department:");
            department = sc.nextLine();

            if (StudentValidator.isValidDepartment(department)) {
                return department.trim();
            } else {
                System.out.println("Department cannot be empty!");
            }
        }
    }
    
    
    public static void main(String args[]) {

        Scanner sc = new Scanner(System.in);
        StudentDAO dao = new StudentDAO();

        while (true) {

            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student");
            System.out.println("4. Update Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Student Dashboard");
            System.out.println("7. Export Students to CSV");
            System.out.println("8. Import Students from CSV");
            System.out.println("9. Exit");

            System.out.println("Enter Your Choice:");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input! Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();
            sc.nextLine();

            try {

                switch (choice) {

                    case 1:
                        addStudent(sc, dao);
                        break;

                    case 2:
                        System.out.println("\nSort Students By:");
                        System.out.println("1. ID");
                        System.out.println("2. Name");
                        System.out.println("3. Age");
                        System.out.println("Enter Your Choice:");

                        if (!sc.hasNextInt()) {
                            System.out.println("Invalid input!");
                            sc.nextLine();
                            break;
                        }

                        int sortChoice = sc.nextInt();
                        sc.nextLine();

                        if (sortChoice < 1 || sortChoice > 3) {
                            System.out.println("Invalid sorting choice! Please select 1, 2, or 3.");
                            break;
                        }

                        dao.viewStudents(sortChoice);
                        break;

                    case 3:
                        searchStudent(sc, dao);
                        break;

                    case 4:
                        updateStudent(sc, dao);
                        break;

                    case 5:
                        deleteStudent(sc, dao);
                        break;

                    case 6:
                        showDashboard(dao);
                        break;

                    case 7:
                        dao.exportStudentsToCSV();
                        break;

                    case 8:
                        dao.importStudentsFromCSV();
                        break;

                    case 9:
                        System.out.println("Thank You!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid Choice! Please select 1-9.");
                }

            } catch (SQLException e) {

                System.out.println("\nDatabase Error!");
                System.out.println("Unable to complete the operation.");
                System.out.println("Reason: " + e.getMessage());

            }
        }
    }
    
    public static void showDashboard(StudentDAO dao) throws SQLException {

        int total = dao.getTotalStudents();

        System.out.println("\n=================================");
        System.out.println("       STUDENT DASHBOARD");
        System.out.println("=================================");

        System.out.println("Total Students : " + total);

        double averageAge = dao.getAverageAge();
        System.out.printf("Average Age    : %.2f%n", averageAge);

        dao.getDepartmentStatistics();

        System.out.println("=================================");
    }
}   