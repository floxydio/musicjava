package bedulproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame() {
		setResizable(false);
		setTitle("Product ");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Add Product");
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(296, 20, 148, 35);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(17, 68, 416, 188);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		table.setColumnSelectionAllowed(true);
		scrollPane.setColumnHeaderView(table);
		scrollPane.setViewportView(table);
		
		JButton btnShowData = new JButton("Show Data");
		btnShowData.setBounds(17, 20, 148, 35);
		contentPane.add(btnShowData);
		
		btnShowData.addActionListener(new ActionListener() {
				
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM transaction";
					ResultSet rs = stmt.executeQuery(sql);
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) table.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName =  new String[cols];
					
					for (int i = 0; i <cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id,namaCustomer,namaProduct,quantity;
						
						while(rs.next()) {
							id = rs.getString(1);
							namaCustomer = rs.getString(2);
							namaProduct = rs.getString(3);
							quantity = rs.getString(4);
							String[] row = {id,namaCustomer,namaProduct,quantity};
							model.addRow(row);
						}
					}			    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
			
		});

		
		btnNewButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				AddProduct frame = new AddProduct();
				frame.setVisible(true);
				setVisible(false);
				
			}
			
			
		});
		
	}
}
