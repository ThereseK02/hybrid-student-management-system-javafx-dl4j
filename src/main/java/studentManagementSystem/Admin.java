

package studentManagementSystem;
/**
 * SUBCLASS: Admin
 * Extends User. Acts as an Academic Analyst.
 * Combines statistical regression with human-readable performance reporting.
 */

public class Admin extends User {
    
    private String departmentAccess;

    public Admin(String accountUsername, String accountPassword, String departmentAccess) {
        super(accountUsername, accountPassword);
        this.departmentAccess = departmentAccess;
    }

    /**
     * BUSINESS LOGIC: Performance Predictions
     * Uses Linear Regression: y = (slope * x) + intercept
     * This calculates the raw mathematical prediction.
     */
    public double calculateForecast(double currentGpa, double slope, double intercept) {
        double predicted = (slope * currentGpa) + intercept;
        return Math.min(4.0, Math.max(0.0, predicted)); // Clamps GPA between 0.0 and 4.0
    }

    /**
     * CATEGORICAL ANALYSIS: Performance Report
     * Converts raw data and regression results into a professional report.
     * Demonstrates: Interaction between Admin logic and Student data.
     */
    public String generateSuccessReport(Student student, double slope, double intercept) {
        // Using the simple linear regression formula: y = mx + b
        double forecastedGpa = (slope * student.getGpa()) + intercept;
        
        // Cap the GPA so it doesn't exceed 4.0
        if (forecastedGpa > 4.0) forecastedGpa = 4.0;

        // Humanized verdict logic
        String verdict;
        if (forecastedGpa >= 3.5) verdict = "High Distinction Potential";
        else if (forecastedGpa >= 2.0) verdict = "Satisfactory Progress";
        else verdict = "MAcademic Intervention Advised";

        StringBuilder report = new StringBuilder();
        report.append("\n=========================================\n");
        report.append("       OFFICIAL PERFORMANCE REPORT       \n");
        report.append("=========================================\n");
        report.append("Analyst: ").append(getAccountUsername()).append(" (").append(departmentAccess).append(")\n");
        report.append("-----------------------------------------\n");
        report.append("Student Name:    ").append(student.getName()).append("\n");
        report.append("Current GPA:     ").append(student.getGpa()).append("\n");
        report.append("Forecasted GPA:  ").append(String.format("%.2f", forecastedGpa)).append("\n");
        report.append("-----------------------------------------\n");
        report.append("DEPARTMENT VERDICT: ").append(verdict).append("\n");
        report.append("=========================================\n");
        
        return report.toString();
    }
    @Override
    public void displayRole() {
        System.out.println("Access Level: Admin | Dept: " + departmentAccess + " | Analyst: " + getAccountUsername());
    }

    // Getter and Setter
    public String getDepartmentAccess() { return departmentAccess; }
    public void setDepartmentAccess(String departmentAccess) { this.departmentAccess = departmentAccess; }
}