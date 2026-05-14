
package studentManagementSystem;

/**
 * Requirement: implements
 * This interface defines the contract for any user that can log in.
 */
public interface Authenticatable {
    // Methods in interfaces are public and abstract by default
    boolean login(String username, String password);
}
