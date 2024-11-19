import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class AuthenticationSystem {
    private final List<User> users;

    public AuthenticationSystem() {
        this.users = new ArrayList<>();
    }

    public boolean register(String name, int accessLevel, BufferedImage photo) {
        for (User user : users) {
            if (user.getName().equals(name)) {
                return false; // Nome de usuário já existe
            }
        }
        users.add(new User(name, accessLevel, photo));
        return true;
    }

    public int validateLogin(String name, BufferedImage photo) {
        for (User user : users) {
            if (user.getName().equals(name) && arePhotosSimilar(user.getPhoto(), photo)) {
                return user.getAccessLevel();
            }
        }
        return -1; // Login inválido
    }

    private boolean arePhotosSimilar(BufferedImage img1, BufferedImage img2) {
        if (img1.getWidth() != img2.getWidth() || img1.getHeight() != img2.getHeight()) {
            return false;
        }

        for (int x = 0; x < img1.getWidth(); x++) {
            for (int y = 0; y < img1.getHeight(); y++) {
                if (img1.getRGB(x, y) != img2.getRGB(x, y)) {
                    return false;
                }
            }
        }
        return true;
    }
}
