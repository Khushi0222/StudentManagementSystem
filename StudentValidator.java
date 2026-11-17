
class StudentValidator {

    // Validate student name
    public static boolean isValidName(String name) {
        return name != null && !name.trim().isEmpty();
    }

    // Validate student age
    public static boolean isValidAge(int age) {
        return age > 0 && age <= 100;
    }

    // Validate department
    public static boolean isValidDepartment(String department) {
        return department != null && !department.trim().isEmpty();
    }

    // Validate email
    public static boolean isValidEmail(String email) {
        return email != null &&
                email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    

}
