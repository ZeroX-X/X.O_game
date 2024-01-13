package TicTacToe;
import java.nio.file.*;
import java.util.HashMap;
import java.util.Map;
import java.io.IOException;

public class AuthManager {
    private static final String USER_INFO_FILE = "user_info.txt";

    public static void register(String username, String password) {
        Map<String, String> userInfo = new HashMap<>();
        userInfo.put("username", username);
        userInfo.put("password", password);
        userInfo.put("wins", "0");
        userInfo.put("losses", "0");

        try {
            Files.write(Paths.get(USER_INFO_FILE), userInfo.entrySet().toString().getBytes());
        } catch (IOException e) {
            System.out.println("Error saving user information to file.");
        }
    }

    public static boolean login(String username, String password) {
        try {
            Path path = Paths.get(USER_INFO_FILE);
            String content = Files.readString(path);

            // Parse the content and check if the provided credentials match
            // Implement this based on your file format

            // For simplicity, we are not implementing the full parsing logic here
            // You may want to use a more robust approach (e.g., JSON or CSV format)

            return content.contains("username=" + username) && content.contains("password=" + password);
        } catch (IOException e) {
            System.out.println("Error loading user information from file.");
            return false;
        }
    }
}
