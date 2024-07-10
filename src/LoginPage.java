import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JPasswordField;
import java.awt.SystemColor;

public class LoginPage {

	JFrame frame;
	private JTextField t1;
	private JTextField textField_1;
	private JPasswordField t2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 508, 253);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 25));
		lblNewLabel.setBounds(26, 38, 159, 43);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPassword.setBounds(26, 91, 159, 55);
		frame.getContentPane().add(lblPassword);
		
		t1 = new JTextField();
		t1.setBackground(Color.WHITE);
		t1.setHorizontalAlignment(SwingConstants.CENTER);
		t1.setFont(new Font("Calibri", Font.BOLD, 14));
		t1.setBounds(220, 38, 214, 31);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setHorizontalAlignment(SwingConstants.CENTER);
		textField_1.setFont(new Font("Calibri", Font.BOLD, 14));
		textField_1.setColumns(10);
		textField_1.setBounds(220, 113, 123, 0);
		frame.getContentPane().add(textField_1);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBackground(UIManager.getColor("Button.highlight"));
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 14));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			String user,password;
			user=t1.getText();
			password=t2.getText();
			if(user.equals("Suraj")&&password.equals("123"))
			{
				JOptionPane.showMessageDialog(null, "Login Successfully..");
				SecondPage c1 = new SecondPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}else {
				JOptionPane.showMessageDialog(null, "Login Failed");
			}
			}
		});
		btnNewButton.setBounds(126, 160, 139, 34);
		frame.getContentPane().add(btnNewButton);
		
		t2 = new JPasswordField();
		t2.setBackground(Color.WHITE);
		t2.setBounds(220, 96, 214, 31);
		frame.getContentPane().add(t2);
	}
}
