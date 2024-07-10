import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class SecondPage {

	JFrame frame;
	Student c1;
	Book c3;
	Author c4;
	IssueDate c5;
	ReturnDate c6;
;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SecondPage window = new SecondPage();
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
	public SecondPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 450, 652);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Student");
		btnNewButton.setBackground(SystemColor.scrollbar);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c1 = new Student();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton.setBounds(35, 38, 363, 50);
		frame.getContentPane().add(btnNewButton);
		
	
		
		JButton btnNewButton_2 = new JButton("Books");
		btnNewButton_2.setBackground(SystemColor.scrollbar);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					c3 = new Book();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c3.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton_2.setBounds(35, 129, 363, 50);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Author");
		btnNewButton_3.setBackground(SystemColor.scrollbar);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					c4 = new Author();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c4.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton_3.setBounds(35, 223, 363, 50);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Issue Date");
		btnNewButton_4.setBackground(SystemColor.scrollbar);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					c5 = new IssueDate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c5.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_4.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton_4.setBounds(35, 320, 363, 50);
		frame.getContentPane().add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Return Date");
		btnNewButton_5.setBackground(SystemColor.scrollbar);
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					c6 = new ReturnDate();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				c6.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_5.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton_5.setBounds(35, 424, 363, 50);
		frame.getContentPane().add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Logout");
		btnNewButton_6.setBackground(SystemColor.scrollbar);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginPage c1 = new LoginPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_6.setFont(new Font("Calibri", Font.BOLD, 25));
		btnNewButton_6.setBounds(35, 526, 363, 50);
		frame.getContentPane().add(btnNewButton_6);
	}
}
