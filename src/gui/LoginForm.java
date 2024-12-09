package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import dao.UsersDAO;
import model.User;
import util.DatabaseConnection;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textUsername;
	private JPasswordField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm frame = new LoginForm();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 328, 211);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(27, 48, 59, 13);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(27, 88, 45, 13);
		contentPane.add(lblNewLabel_1);
		
		textUsername = new JTextField();
		textUsername.setBounds(114, 45, 144, 19);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		textPassword = new JPasswordField();
		textPassword.setBounds(114, 85, 144, 19);
		contentPane.add(textPassword);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   UsersDAO usersDAO = new UsersDAO();

			        // Kullanıcıdan giriş bilgilerini al
			        String username = textUsername.getText();
			        String password =textPassword.getText();
			        // Giriş yapmayı dene
			        User user = usersDAO.login(username, password);
			        if (user != null) {
			        	JOptionPane.showMessageDialog(null, "Giriş başarılı! Hoş geldiniz, " + user.getUsername(), 
                                "Başarılı Giriş", JOptionPane.INFORMATION_MESSAGE);
			        	PersonelOperations form=new PersonelOperations();
			        	form.show();
			        	
			        	} else {
			        		 JOptionPane.showMessageDialog(null, "Giriş başarısız! Kullanıcı adı veya şifre yanlış.", 
                                     "Başarısız Giriş", JOptionPane.ERROR_MESSAGE);

			    }
			}
		});
		btnSignIn.setBounds(143, 127, 115, 21);
		contentPane.add(btnSignIn);
		
		JButton btnReset = new JButton("RESET");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textUsername.setText("");
				textPassword.setText("");
			}
		});
		btnReset.setBounds(27, 127, 106, 21);
		contentPane.add(btnReset);
	}
}
