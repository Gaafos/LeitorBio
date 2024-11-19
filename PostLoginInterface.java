import javax.swing.*;

public class PostLoginInterface {
    public PostLoginInterface(int accessLevel) {
        JFrame postLoginFrame = new JFrame("Bem-vindo!");
        postLoginFrame.setSize(400, 200);
        postLoginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblMessage = new JLabel("Bem-vindo, Nível de Acesso: " + accessLevel);
        lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
        postLoginFrame.add(lblMessage);

        postLoginFrame.setVisible(true);
    }
}
