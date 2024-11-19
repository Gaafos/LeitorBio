import java.awt.image.BufferedImage;

public class User {
    private String name;
    private int accessLevel;
    private BufferedImage photo;

    public User(String name, int accessLevel, BufferedImage photo) {
        this.name = name;
        this.accessLevel = accessLevel;
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public BufferedImage getPhoto() {
        return photo;
    }
}
