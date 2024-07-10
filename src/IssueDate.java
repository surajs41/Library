import java.awt.EventQueue;
import java.sql.SQLException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import com.toedter.calendar.JDateChooser;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class IssueDate {

	JFrame frame;
	private JTextField t3;
	private JTextField t1;
	private JTextField t2;
	private JTable table;
	Connection con;     
	PreparedStatement pst;
	ResultSet rs;
	private JDateChooser dc1;
	private JComboBox cb1;
	private JButton btnUpdate;
	private JButton btnNewButton_2;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					IssueDate window = new IssueDate();
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
	public IssueDate()throws Exception {
		initialize();
		connect(); 
		load_Id(); 
		table_load(); 
	}

public void connect()throws SQLException 
{ 
con=DriverManager.getConnection("jdbc:mysql://localhost:3306/library","root","root"); 
} 
public void table_load() {
    try {
        pst = con.prepareStatement("select * from issuedate");
        rs = pst.executeQuery();
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear existing table data
        while (rs.next()) {
            model.addRow(new Object[]{rs.getString("StudentId"), rs.getString("BookId"), rs.getString("StudentName"), rs.getString("BookName"), rs.getString("IssueDate")});
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void load_Id() throws SQLException {
    pst = con.prepareStatement("select `StudentId` from student");
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
		frame.setBounds(100, 100, 798, 559);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Issue Date");
		lblNewLabel.setFont(new Font("Times New Roman", Font.BOLD, 34));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 725, 83);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Student Id");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1.setBounds(10, 125, 133, 42);
		frame.getContentPane().add(lblNewLabel_1);
		
		cb1 = new JComboBox();
		cb1.setBounds(166, 125, 158, 42);
		frame.getContentPane().add(cb1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Id");
		lblNewLabel_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1.setBounds(10, 188, 133, 42);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Student Name");
		lblNewLabel_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1.setBounds(10, 256, 133, 42);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		t1 = new JTextField();
		t1.setBounds(166, 251, 158, 42);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1_1.setBounds(10, 317, 133, 42);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		t2 = new JTextField();
		t2.setColumns(10);
		t2.setBounds(166, 318, 158, 42);
		frame.getContentPane().add(t2);
		
		JLabel lblNewLabel_1_1_1_1_1 = new JLabel("Issue Date");
		lblNewLabel_1_1_1_1_1.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblNewLabel_1_1_1_1_1.setBounds(10, 381, 133, 42);
		frame.getContentPane().add(lblNewLabel_1_1_1_1_1);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String BookId, StudentName, BookName, IssueDate;
		            BookId = t3.getText();
		            StudentName = t1.getText();
		            BookName = t2.getText();
		            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		            IssueDate = sdf.format(dc1.getDate());
		            if (StudentName.equals("") || BookName.equals("")) {
		                JOptionPane.showMessageDialog(null, "Please fill all the records");
		            } else {
		                String sql = "INSERT INTO issuedate(BookId, StudentName, BookName, IssueDate) VALUES (?, ?, ?, ?)";
		                pst = con.prepareStatement(sql);
		                pst.setString(1, BookId);
		                pst.setString(2, StudentName);
		                pst.setString(3, BookName);
		                pst.setString(4, IssueDate);
		                pst.executeUpdate();
		                JOptionPane.showMessageDialog(null, "Record Added Successfully.");
		                t1.setText("");
		                t2.setText(null);
		                t3.setText(null);
		                table_load();
		                load_Id();
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnNewButton.setBounds(26, 451, 122, 42);
		frame.getContentPane().add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(354, 97, 391, 326);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Student Id", "Book Id", "Student Name", "BookName", "Issue Date"
			}
		));
		
		dc1 = new JDateChooser();
		dc1.setBounds(166, 381, 158, 35);
		frame.getContentPane().add(dc1);
		
		btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String StudentName=t1.getText();
				String BookName=t2.getText();
				String Id2 = cb1.getSelectedItem().toString();
				pst = con.prepareStatement("update issuedate set StudName=?, BookName=? where BookId=?");
				pst.setString(1, StudentName);
				pst.setString(2, BookName);
				pst.setString(3, Id2); 

				int k=pst.executeUpdate();
				if(k==1)
				{
				JOptionPane.showMessageDialog(null, "Record Updated. ");
				t1.setText(null);
				t2.setText(null);
				t1.requestFocus();
				load_Id();
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
			}
			}
		});
		btnUpdate.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnUpdate.setBounds(174, 451, 122, 42);
		frame.getContentPane().add(btnUpdate);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String
				id=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("delete from issuedate where BookId=?");
				pst.setString(1, id);
				int k=pst.executeUpdate();
				if(k==1)
				{
				JOptionPane.showMessageDialog(null,"Record deleted. ");
				t1.setText(null);
				t2.setText(null); 
				t1.requestFocus();
				load_Id();
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
		btnNewButton_2.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_2.setBounds(331, 451, 122, 42);
		frame.getContentPane().add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Show");
		btnNewButton_3.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String studentId = cb1.getSelectedItem().toString();
		            pst = con.prepareStatement("SELECT Name, BookId, BookName FROM student WHERE StudentId=?");
		            pst.setString(1, studentId);
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                t1.setText(rs.getString(1)); // Set Student Name
		                t3.setText(rs.getString(2)); // Set Book ID
		                t2.setText(rs.getString(3)); // Set Book Name
		            } else {
		                JOptionPane.showMessageDialog(null, "Data not found for the selected Student ID.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});




		btnNewButton_3.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_3.setBounds(479, 451, 122, 42);
		frame.getContentPane().add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Exit");
		btnNewButton_4.setFont(new Font("Times New Roman", Font.BOLD, 20));
		btnNewButton_4.setBounds(627, 451, 122, 42);
		frame.getContentPane().add(btnNewButton_4);
		
		t3 = new JTextField();
		t3.setColumns(10);
		t3.setBounds(166, 188, 158, 42);
		frame.getContentPane().add(t3);
	}
}
