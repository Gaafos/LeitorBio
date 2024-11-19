import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class UserInterface {
    private final AuthenticationSystem authenticationSystem;

    public UserInterface(AuthenticationSystem authenticationSystem) {
        this.authenticationSystem = authenticationSystem;
        showMainMenu();
    }

    private void showMainMenu() {
        JFrame mainFrame = new JFrame("Menu Principal");
        mainFrame.setSize(300, 150);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());

        JButton btnRegister = new JButton("Cadastro");
        JButton btnLogin = new JButton("Login");

        btnRegister.addActionListener(e -> {
            mainFrame.dispose();
            showRegisterScreen();
        });

        btnLogin.addActionListener(e -> {
            mainFrame.dispose();
            showLoginScreen();
        });

        mainFrame.add(btnRegister);
        mainFrame.add(btnLogin);
        mainFrame.setVisible(true);
    }

    private void showRegisterScreen() {
        JFrame registerFrame = new JFrame("Cadastro");
        registerFrame.setSize(400, 300);
        registerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registerFrame.setLayout(null);

        JLabel lblName = new JLabel("Nome:");
        lblName.setBounds(20, 20, 100, 25);
        JTextField txtName = new JTextField();
        txtName.setBounds(120, 20, 200, 25);

        JLabel lblAccessLevel = new JLabel("Nível de Acesso:");
        lblAccessLevel.setBounds(20, 60, 150, 25);
        JComboBox<Integer> cmbAccessLevel = new JComboBox<>(new Integer[]{1, 2, 3});
        cmbAccessLevel.setBounds(120, 60, 200, 25);

        JLabel lblPhoto = new JLabel("Foto:");
        lblPhoto.setBounds(20, 100, 100, 25);
        JButton btnChoosePhoto = new JButton("Escolher Foto");
        btnChoosePhoto.setBounds(120, 100, 200, 25);

        JLabel lblPhotoPath = new JLabel("");
        lblPhotoPath.setBounds(120, 140, 200, 25);

        btnChoosePhoto.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(registerFrame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                lblPhotoPath.setText(file.getAbsolutePath());
            }
        });

        JButton btnSubmit = new JButton("Cadastrar");
        btnSubmit.setBounds(120, 180, 200, 25);

        btnSubmit.addActionListener(e -> {
            try {
                String name = txtName.getText();
                int accessLevel = (int) cmbAccessLevel.getSelectedItem();
                BufferedImage photo = ImageIO.read(new File(lblPhotoPath.getText()));

                if (authenticationSystem.register(name, accessLevel, photo)) {
                    JOptionPane.showMessageDialog(registerFrame, "Cadastro realizado com sucesso!");
                    registerFrame.dispose();
                    showMainMenu();
                } else {
                    JOptionPane.showMessageDialog(registerFrame, "Usuário já existe.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(registerFrame, "Erro: " + ex.getMessage());
            }
        });

        registerFrame.add(lblName);
        registerFrame.add(txtName);
        registerFrame.add(lblAccessLevel);
        registerFrame.add(cmbAccessLevel);
        registerFrame.add(lblPhoto);
        registerFrame.add(btnChoosePhoto);
        registerFrame.add(lblPhotoPath);
        registerFrame.add(btnSubmit);
        registerFrame.setVisible(true);
    }

    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setSize(400, 250);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(null);

        JLabel lblName = new JLabel("Nome:");
        lblName.setBounds(20, 20, 100, 25);
        JTextField txtName = new JTextField();
        txtName.setBounds(120, 20, 200, 25);

        JLabel lblPhoto = new JLabel("Foto:");
        lblPhoto.setBounds(20, 60, 100, 25);
        JButton btnChoosePhoto = new JButton("Escolher Foto");
        btnChoosePhoto.setBounds(120, 60, 200, 25);

        JLabel lblPhotoPath = new JLabel("");
        lblPhotoPath.setBounds(120, 100, 200, 25);

        btnChoosePhoto.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(loginFrame) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                lblPhotoPath.setText(file.getAbsolutePath());
            }
        });

        JButton btnLogin = new JButton("Entrar");
        btnLogin.setBounds(120, 140, 200, 25);

        btnLogin.addActionListener(e -> {
            try {
                String name = txtName.getText();
                BufferedImage photo = ImageIO.read(new File(lblPhotoPath.getText()));

                int accessLevel = authenticationSystem.validateLogin(name, photo);
                if (accessLevel != -1) {
                    JOptionPane.showMessageDialog(loginFrame, "Login realizado com sucesso!");
                    loginFrame.dispose();
                    new PostLoginInterface(accessLevel);
                } else {
                    JOptionPane.showMessageDialog(loginFrame, "Login falhou.");
                }
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(loginFrame, "Erro: " + ex.getMessage());
            }
        });

        loginFrame.add(lblName);
        loginFrame.add(txtName);
        loginFrame.add(lblPhoto);
        loginFrame.add(btnChoosePhoto);
        loginFrame.add(lblPhotoPath);
        loginFrame.add(btnLogin);
        loginFrame.setVisible(true);
    }
}
