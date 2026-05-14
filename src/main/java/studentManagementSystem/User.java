/**
 * 
 */
package studentManagementSystem;
/**
 * Requirements: abstract, implements, encapsulation
 */
public abstract class User implements Authenticatable {
    private String accountUsername;
    private String accountPassword;

    public User(String username, String password) {
        this.accountUsername = username;
        this.accountPassword = password;
    }

    // Encapsulation: Public getters and setters for private fields
    public String getAccountUsername() { return accountUsername; }
    public String getAccountPassword() { return accountPassword; }
    public void setAccountUsername(String newName) {
        this.accountUsername = newName;
    }
    
    // Requirement: Abstract method (must be overridden by subclasses)
    public abstract void displayRole();

    // Requirement: Implementation of the interface method
    @Override
    public boolean login(String username, String password) {
        return this.accountUsername.equals(username) && this.accountPassword.equals(password);
    }
}
