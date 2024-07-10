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
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.JComboBox;

public class Book {

	JFrame frame;
	private JTextField t2;
	private JTextField t3;
	private JComboBox cb1;
	Connection con;     
	PreparedStatement pst;
	ResultSet rs;
	private JTable table;
	private JTextField t1;
	


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Book window = new Book();
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
	public Book()throws Exception {
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
        pst = con.prepareStatement("SELECT * FROM book");
        rs = pst.executeQuery();
        // Update the table model with new data
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
		frame.setBounds(100, 100, 790, 538);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Book Database");
		lblNewLabel.setFont(new Font("Calibri", Font.BOLD, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(134, 22, 476, 37);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Book Name");
		lblNewLabel_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1.setBounds(70, 178, 106, 37);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_2 = new JLabel("Author");
		lblNewLabel_1_2.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2.setBounds(70, 248, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_2);
		
		t2 = new JTextField();
		t2.setBackground(SystemColor.scrollbar);
		t2.setColumns(10);
		t2.setBounds(228, 247, 202, 37);
		frame.getContentPane().add(t2);
		
		JButton btnNewButton = new JButton("Insert");
		btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e){
					try 
					{ 
					String BookName, Author, Edition;
					BookName=t1.getText(); 
					Author=t2.getText();
					Edition=t3.getText(); 
					if(BookName.equals("")||Author.equals("")||Edition.equals( "")) 
					{ 
					JOptionPane.showMessageDialog(null, "Please fill all the records"); 
					} 
					else 
					{ 
					String sql="insert into book(BookName, Author, Edition) values(?,?,?)";
	 
					try { 
					pst=con.prepareStatement(sql); 
					pst.setString(1, BookName); 
					pst.setString(2, Author); 
					pst.setString(3, Edition);
					pst.executeUpdate(); 
					JOptionPane.showMessageDialog(null, "Record Added Successfully... "); 
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
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton.setBounds(42, 423, 120, 43);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String BookName=t1.getText();
				String Author=t2.getText();
				String Edition=t3.getText();
				String Id2 = cb1.getSelectedItem().toString();
				pst = con.prepareStatement("update book set BookName=?, Author=?, Edition=? where BookId=?");
				pst.setString(1, BookName);
				pst.setString(2, Author);
				pst.setString(3, Edition);
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
		btnNewButton_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_1.setBounds(198, 423, 120, 43);
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
				String
				id=cb1.getSelectedItem().toString();
				pst=con.prepareStatement("delete from book where BookId=?");
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
		btnNewButton_2.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_2.setBounds(356, 423, 120, 43);
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Show");
		btnNewButton_3.addActionListener(new ActionListener() {
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
				t2.setText(rs.getString(3));
				t3.setText(rs.getString(4));			}
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
		btnNewButton_3.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_3.setBounds(500, 423, 120, 43);
		frame.getContentPane().add(btnNewButton_3);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("Edition");
		lblNewLabel_1_2_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_2_1.setBounds(70, 323, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_2_1);
		
		t3 = new JTextField();
		t3.setBackground(SystemColor.scrollbar);
		t3.setColumns(10);
		t3.setBounds(228, 322, 202, 37);
		frame.getContentPane().add(t3);
		
		JButton btnNewButton_3_1 = new JButton("Exit");
		btnNewButton_3_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SecondPage c1 = new SecondPage();
				c1.frame.setVisible(true);
				frame.setVisible(false);
			}
		});
		btnNewButton_3_1.setFont(new Font("Calibri", Font.BOLD, 20));
		btnNewButton_3_1.setBounds(646, 423, 120, 43);
		frame.getContentPane().add(btnNewButton_3_1);
		
		cb1 = new JComboBox();
		cb1.setBackground(Color.LIGHT_GRAY);
		cb1.setBounds(228, 108, 202, 37);
		frame.getContentPane().add(cb1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(475, 108, 277, 266);
		frame.getContentPane().add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Book Id", "Book Name", "Author", "Edition"
			}
		));
		
		JLabel lblNewLabel_1_1 = new JLabel("Book Id");
		lblNewLabel_1_1.setFont(new Font("Calibri", Font.BOLD, 20));
		lblNewLabel_1_1.setBounds(70, 108, 106, 37);
		frame.getContentPane().add(lblNewLabel_1_1);
		
		t1 = new JTextField();
		t1.setColumns(10);
		t1.setBackground(SystemColor.scrollbar);
		t1.setBounds(228, 178, 202, 37);
		frame.getContentPane().add(t1);
	}
}
