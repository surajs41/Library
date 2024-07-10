import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;

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
import com.toedter.calendar.JDateChooser;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

public class Author {

	JFrame frame;
	private JTextField t1;
	private JTextField t2;
	private JTextField t3;
	private JComboBox cb1;
	private JDateChooser dc1;
	Connection con;     
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Author window = new Author();
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
	public Author()throws Exception {
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
        pst = con.prepareStatement("SELECT * FROM author");
        rs = pst.executeQuery();
        table.setModel(DbUtils.resultSetToTableModel(rs));
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
public void load_Id() throws SQLException {
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
		frame.setBounds(100, 100, 741, 590);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Author Details");
		lblNewLabel.setBackground(new Color(255, 228, 196));
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 10, 631, 80);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Author Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1.setBounds(22, 267, 144, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Contact Info");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1_1.setBounds(22, 338, 144, 38);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t2 = new JTextField();
		t2.setBackground(SystemColor.scrollbar);
		t2.setBounds(176, 267, 167, 38);
		frame.getContentPane().add(t2);
		t2.setColumns(10);
		
		t3 = new JTextField();
		t3.setBackground(SystemColor.scrollbar);
		t3.setColumns(10);
		t3.setBounds(176, 338, 167, 38);
		frame.getContentPane().add(t3);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try 
				{ 
				String BookName,AuthorName,ContactInfo,PDate; 
				BookName=t1.getText();
				AuthorName=t2.getText(); 
				ContactInfo=t3.getText();  
				SimpleDateFormat sdf=new 
				SimpleDateFormat("yyyy-MM-dd"); 
				PDate=sdf.format(dc1.getDate()); 
				if(BookName.equals("")||AuthorName.equals("")||ContactInfo.equals("")) 
				{ 
				JOptionPane.showMessageDialog(null, "Please fill all the records"); 
				} 
				else 
				{ 
				String sql="insert into author(BookName,AuthorName,ContactInfo,PDate) values(?,?,?,?)"; 
				try { 
				pst=con.prepareStatement(sql); 
				pst.setString(1, BookName);
				pst.setString(2, AuthorName); 
				pst.setString(3, ContactInfo); 
				pst.setString(4, PDate);
				pst.executeUpdate(); 
				JOptionPane.showMessageDialog(null, "Record Added Successfully. .. "); 
				t1.setText("");
				t2.setText(null); 
				t3.setText(null); 
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
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 19));
		btnNewButton.setBounds(22, 488, 105, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String BookName=t1.getText();
				String AuthorName=t2.getText();
				String ContactInfo=t3.getText();
				String Id2 = cb1.getSelectedItem().toString();
				pst = con.prepareStatement("update author set BookName=?, Author=?, Edition=? where id=?");
				pst.setString(1, BookName);
				pst.setString(2, AuthorName);
				pst.setString(3, ContactInfo);
				pst.setString(4, Id2); 

				int k=pst.executeUpdate();
				if(k==1)
				{
				JOptionPane.showMessageDialog(null, "Record Updated. ");
				t1.setText(null);
				t2.setText(null);
				t3.setText(null);
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
		btnUpdate.setFont(new Font("Calibri", Font.BOLD, 19));
		btnUpdate.setBounds(146, 488, 105, 43);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String
				id=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("delete from author where id=?");
				pst.setString(1, id);
				int k=pst.executeUpdate();
				if(k==1)
				{
				JOptionPane.showMessageDialog(null,"Record deleted. ");
				t1.setText(null);
				t2.setText(null); 
				t3.setText(null);
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
		btnDelete.setFont(new Font("Calibri", Font.BOLD, 19));
		btnDelete.setBounds(272, 488, 105, 43);
		frame.getContentPane().add(btnDelete);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondPage c1 = new SecondPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnExit.setFont(new Font("Calibri", Font.BOLD, 19));
		btnExit.setBounds(509, 488, 105, 43);
		frame.getContentPane().add(btnExit);
		
		JLabel lblNewLabel_1_2 = new JLabel("id");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1_2.setBounds(22, 122, 144, 38);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		cb1 = new JComboBox();
		cb1.setBounds(176, 121, 167, 38);
		frame.getContentPane().add(cb1);
		
		JButton btnDelete_1 = new JButton("Show");
		btnDelete_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String id2=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("select * from book where bookid=?");
				pst.setString(1,id2);
				rs=pst.executeQuery();
				if(rs.next()==true)
				{
				t1.setText(rs.getString(2));
				t2.setText(rs.getString(3));			}
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
		btnDelete_1.setFont(new Font("Calibri", Font.BOLD, 19));
		btnDelete_1.setBounds(387, 488, 105, 43);
		frame.getContentPane().add(btnDelete_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Publication Date");
		lblNewLabel_1_1_1.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1_1_1.setBounds(22, 405, 144, 38);
		frame.getContentPane().add(lblNewLabel_1_1_1);
		
		dc1 = new JDateChooser();
		dc1.setBounds(176, 405, 167, 39);
		frame.getContentPane().add(dc1);
		
		JLabel lblNewLabel_1_1_1_1 = new JLabel("Book Name");
		lblNewLabel_1_1_1_1.setFont(new Font("Calibri", Font.BOLD, 19));
		lblNewLabel_1_1_1_1.setBounds(22, 190, 144, 38);
		frame.getContentPane().add(lblNewLabel_1_1_1_1);
		
		t1 = new JTextField();
		t1.setColumns(10);
		t1.setBackground(SystemColor.scrollbar);
		t1.setBounds(176, 190, 167, 38);
		frame.getContentPane().add(t1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(365, 114, 340, 329);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Id", "BookName", "AuthorName", "Contact Info", "PDate"
			}
		));
	}
}
