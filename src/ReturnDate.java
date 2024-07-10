import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import com.toedter.calendar.JDateChooser;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class ReturnDate {

	JFrame frame;
	private JTextField t1;
	private JTextField t2;
	Connection con;     
	PreparedStatement pst;
	ResultSet rs;
	private JDateChooser dc1;
	private JComboBox cb1;
	private JComboBox cb2;
	private JTextField t4;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReturnDate window = new ReturnDate();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @wbp.parser.entryPoint
	 */
	public ReturnDate()throws Exception {
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
        pst = con.prepareStatement("SELECT * FROM returndate");
        rs = pst.executeQuery();
        
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0); // Clear the existing table data
        
        while (rs.next()) {
            // Check for null values and replace them with an empty string
            String bookId = rs.getString("BookId") != null ? rs.getString("BookId") : "";
            
            Object[] row = { 
                rs.getString("StudentId"), 
                bookId, // Use the modified bookId value
                rs.getString("StudentName"), 
                rs.getString("BookName"), 
                rs.getString("IssueDate"), 
                rs.getString("ReturnDate")
            };
            model.addRow(row); // Add each row to the table model
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


public void load_Id() throws SQLException {
    pst = con.prepareStatement("SELECT `StudentId` FROM issuedate");
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
		frame.setBounds(100, 100, 834, 610);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Return Date");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setBounds(0, 0, 820, 86);
		frame.getContentPane().add(lblNewLabel);
		
		dc1 = new JDateChooser();
		dc1.setBackground(SystemColor.scrollbar);
		dc1.setBounds(153, 428, 154, 33);
		frame.getContentPane().add(dc1);
		
		JLabel lblNewLabel_1 = new JLabel("Stud Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(25, 249, 113, 33);
		frame.getContentPane().add(lblNewLabel_1);
		
		t1 = new JTextField();
		t1.setBackground(SystemColor.scrollbar);
		t1.setBounds(153, 248, 154, 33);
		frame.getContentPane().add(t1);
		t1.setColumns(10);
		
		JLabel lblNewLabel_1_1 = new JLabel("Book name");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(25, 311, 113, 33);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t2 = new JTextField();
		t2.setBackground(SystemColor.scrollbar);
		t2.setColumns(10);
		t2.setBounds(153, 311, 154, 33);
		frame.getContentPane().add(t2);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Return Date");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1_1.setBounds(25, 428, 113, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{ 
				String StudName,BookName,IssueDate,ReturnDate; 
				StudName=t1.getText();
				BookName=t2.getText();  
				IssueDate=t4.getText();  
				SimpleDateFormat sdf=new 
				SimpleDateFormat("yyyy-MM-dd"); 
				ReturnDate=sdf.format(dc1.getDate()); 
				if(StudName.equals("")||BookName.equals("")) 
				{ 
				JOptionPane.showMessageDialog(null, "Please fill all the records"); 
				} 
				else 
				{ 
					String sql = "INSERT INTO returndate (StudentName, BookName, IssueDate, ReturnDate) VALUES (?, ?, ?, ?)";
				try { 
				pst=con.prepareStatement(sql); 
				pst.setString(1, StudName);
				pst.setString(2, BookName); 
				pst.setString(3, IssueDate); 
				pst.setString(4, ReturnDate); 
				pst.executeUpdate(); 
				JOptionPane.showMessageDialog(null, "Record Added Successfully. .. "); 
				t1.setText(""); 
				t2.setText(null); 
				table_load(); 
				load_Id(); 
				} catch (SQLException e1) { 
				// TODO Auto-generated catch block 
				e1.printStackTrace(); 
				} 
				} 
				} 
				catch(Exception ex) 
				{} 
			}			
		});
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(25, 506, 95, 39);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String StudName=t1.getText();
				String BookName=t2.getText();
				String Id2 = cb1.getSelectedItem().toString();
				pst = con.prepareStatement("update returndate set StudName=?, BookName=? where BookId=?");
				pst.setString(1, StudName);
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
			}}
		});
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 20));
		btnUpdate.setBounds(153, 506, 95, 39);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String
				id=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("delete from returndate where BookId=?");
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
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 20));
		btnDelete.setBounds(282, 506, 95, 39);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondPage c1 = new SecondPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 20));
		btnExit.setBounds(538, 506, 95, 39);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1_2 = new JLabel("Book Id");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(25, 180, 113, 33);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		cb1 = new JComboBox();
		cb1.setBounds(153, 173, 154, 39);
		frame.getContentPane().add(cb1);
		
		JButton btnShow = new JButton("Show");
		btnShow.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        try {
		            String id2 = cb1.getSelectedItem().toString();
		            pst = con.prepareStatement("SELECT StudentName, BookName, IssueDate FROM issuedate WHERE StudentId=?");
		            pst.setString(1, id2);
		            rs = pst.executeQuery();
		            if (rs.next()) {
		                t1.setText(rs.getString(1)); // Set StudentName to t1
		                t2.setText(rs.getString(2)); // Set BookName to t2
		                t4.setText(rs.getString(3)); // Set IssueDate to t4
		            } else {
		                JOptionPane.showMessageDialog(null, "Record not found.");
		            }
		        } catch (SQLException ex) {
		            ex.printStackTrace();
		        }
		    }
		});

		btnShow.setFont(new Font("Calibri", Font.BOLD, 20));
		btnShow.setBounds(409, 506, 95, 39);
		frame.getContentPane().add(btnShow);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Issue Date");
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1_1_1.setBounds(25, 369, 113, 33);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		t4 = new JTextField();
		t4.setColumns(10);
		t4.setBackground(SystemColor.scrollbar);
		t4.setBounds(153, 369, 154, 33);
		frame.getContentPane().add(t4);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Student Id");
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(25, 111, 113, 33);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		cb2 = new JComboBox();
		cb2.setBounds(153, 107, 154, 39);
		frame.getContentPane().add(cb2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(345, 96, 450, 364);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			    new Object[][] {},
			    new String[] { "Student Id", "Book Id", "Stud Name", "Book Name", "Issue Date", "Return Date" }
			));

	}
}
