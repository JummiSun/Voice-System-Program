import java.util.HashMap;
import java.util.Map;

public class UserManager {
    private final Map<String, User> users;
    private String currentUsername = null;
    private final User systemAdmin = new User("admin", "admin", true);
    private boolean adminLoggedIn = false;

    public boolean isAdminLoggedIn() {
        return adminLoggedIn;
    }

    public UserManager() {
        this.users = new HashMap<>();
    }

    public void register(String username, String password, boolean admin) {
        if (username.equals(systemAdmin.getUsername())) {
            throw new IllegalArgumentException("You can't register user with admin");
        }
        if (users.containsKey(username)) {
            throw new IllegalArgumentException("User with this name already exists");
        }
        User user = new User(username, password, admin);
        users.put(username, user);
    }

    public void login(String username, String password) {
        User foundUser = users.get(username);
        if (foundUser == null) {
            throw new IllegalArgumentException("User with this name does not exist");
        }
        if (!foundUser.getPassword().equals(password)) {
            throw new IllegalArgumentException("User with this password does not match");
        }
        currentUsername = username;
    }

    public void logout() {
        currentUsername = null;
    }

    public String getCurrentUsername() {
        return currentUsername;
    }

    public boolean isUserLoggedIn() {
        return currentUsername != null;
    }

    public void adminLogin(String username, String password) {
        if (!systemAdmin.getUsername().equals(username) || !systemAdmin.getPassword().equals(password)) {
            throw new IllegalArgumentException("You entered wrong admin name or password");
        }
        adminLoggedIn = true;
    }
}
