import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class Student{

    public JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	Connection con;     
	PreparedStatement pst;
	ResultSet rs;
	private JComboBox cb2;
	private JComboBox cb1;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Student window = new Student();
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
	public Student()throws Exception {
		initialize();
		connect(); 
		loadId(); 
		table_load(); 
	}

public void connect()throws SQLException 
{ 
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root"); 
} 
public void table_load() {
    try {
        pst = con.prepareStatement("SELECT * FROM student");
        rs = pst.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void loadId() throws SQLException {
    pst = con.prepareStatement("SELECT `BookId` FROM book");
    rs = pst.executeQuery();
    cb1.removeAllItems();
    while (rs.next()) {
        cb1.addItem(rs.getString(1));
    }
}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.ORANGE);
		frame.setBounds(100, 100, 857, 599);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Student Database");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 22, 476, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(75, 245, 106, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		t1 = new JTextField();
		t1.setBackground(new Color(255, 255, 255));
		t1.setForeground(Color.BLACK);
		t1.setFont(new Font("Calibri", Font.BOLD, 17));
		t1.setBounds(233, 245, 202, 37);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Id");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(75, 180, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Department");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(75, 316, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		t2 = new JTextField();
		t2.setBackground(new Color(255, 255, 255));
		t2.setForeground(Color.BLACK);
		t2.setFont(new Font("Calibri", Font.BOLD, 17));
		t2.setColumns(10);
		t2.setBounds(233, 316, 202, 37);
		frame.getContentPane().add(t2);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try { 
		            String Name, Department, BookName;
		            String BookId = cb1.getSelectedItem().toString(); // Retrieve the selected book ID
		            Name = t1.getText(); 
		            Department = t2.getText();
		            BookName = t3.getText();
		            
		            if (Name.equals("") || Department.equals("") || BookName.equals("")) { 
		                JOptionPane.showMessageDialog(null, "Please fill all the fields"); 
		            } else { 
		                String sql = "INSERT INTO student (StudentId, Name, Department, BookName) VALUES (?, ?, ?, ?)";
		                try { 
		                    pst = con.prepareStatement(sql);
		                    pst.setString(1, BookId); // Set the book ID as the student ID
		                    pst.setString(2, Name); 
		                    pst.setString(3, Department); 
		                    pst.setString(4, BookName);
		                    pst.executeUpdate(); 
		                    JOptionPane.showMessageDialog(null, "Record Added Successfully."); 
		                    t1.setText(""); 
		                    t2.setText(""); 
		                    t3.setText(""); 
		                    table_load(); 
		                    loadId(); 
		                } catch (SQLException e1) { 
		                    e1.printStackTrace(); 
		                } 
		            } 
		        } catch (Exception ex) { 
		            ex.printStackTrace(); 
		        } 
		    } 
		});

		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(37, 489, 120, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					String Name=t1.getText();
					String Department=t2.getText();
					String BookName=t3.getText();
					String Id2 = cb1.getSelectedItem().toString();
					pst = con.prepareStatement("update student set Name=?, Department=?, BookName=? where StudentId=?");
					pst.setString(1, Name);
					pst.setString(2,BookName);
					pst.setString(3,Department);
					pst.setString(4, Id2); 

					int k=pst.executeUpdate();
					if(k==1)
					{
					JOptionPane.showMessageDialog(null, "Record Updated. ");
					t1.setText(null);
					t2.setText(null);
					t3.setText(null);
					t1.requestFocus();
					loadId();
					table_load();
					}
					else
					{
					JOptionPane.showMessageDialog(null, "Record Failed to update");
					}
					}
					catch(SQLException ex)
					{
					System.out.println(ex);
				}}
		});
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_1.setBounds(193, 489, 120, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String
				StudentId=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("delete from student where StudentId=?");
				pst.setString(1, StudentId);
				int k=pst.executeUpdate();
				if(k==1)
				{
				JOptionPane.showMessageDialog(null,"Record deleted. ");
				t1.setText(null);
				t2.setText(null); t3.setText(null);
				t1.requestFocus();
				loadId();
				table_load();
				}	
				else
				{
				JOptionPane.showMessageDialog(null, "Record Failed to delete");
				}
				}
				catch(SQLException ex)
				{
				System.out.println(ex);
				}
				
			}
		});
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_2.setBounds(351, 489, 120, 43);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Exit");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondPage c1 = new SecondPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_3.setBounds(629, 489, 120, 43);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Book name");
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(75, 392, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		t3 = new JTextField();
		t3.setBackground(new Color(255, 255, 255));
		t3.setForeground(Color.BLACK);
		t3.setFont(new Font("Calibri", Font.BOLD, 17));
		t3.setColumns(10);
		t3.setBounds(233, 391, 202, 37);
		frame.getContentPane().add(t3);
		
		cb1 = new JComboBox();
		cb1.setFont(new Font("Calibri", Font.BOLD, 17));
		cb1.setBackground(SystemColor.scrollbar);
		cb1.setBounds(233, 176, 202, 37);
		frame.getContentPane().add(cb1);
		
		JButton btnNewButton_3_1 = new JButton("Show");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String id2=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("select * from book where BookId=?");
				pst.setString(1,id2);
				rs=pst.executeQuery();
				if(rs.next()==true)
				{
					
				t3.setText(rs.getString(2));
				}
				else
				{
				JOptionPane.showMessageDialog(null, "Record not found. .... ");
				}
				}
				catch(Exception ce)
				{
				}
			}
		});
		btnNewButton_3_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_3_1.setBounds(491, 489, 120, 43);
		frame.getContentPane().add(btnNewButton_3_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Student Id");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(75, 112, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		cb2 = new JComboBox();
		cb2.setFont(new Font("Calibri", Font.BOLD, 17));
		cb2.setBackground(SystemColor.scrollbar);
		cb2.setBounds(233, 108, 202, 37);
		frame.getContentPane().add(cb2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(457, 90, 376, 339);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"StudentId", "BookId", "Name", "Department", "BookName"
			}
		));
	}
}
