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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Color;

public class TransactionFrame extends JFrame {

	private JPanel contentPane;
	private JTable tblTransaction;
	private JButton btnLoadTransaction;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TransactionFrame frame = new TransactionFrame();
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
	public TransactionFrame() {
		setTitle("Data Transaksi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(6, 6, 438, 219);
		contentPane.add(scrollPane);
		
		tblTransaction = new JTable();
		tblTransaction.setColumnSelectionAllowed(true);
		scrollPane.setViewportView(tblTransaction);
		
		btnLoadTransaction = new JButton("Load Transaction");
		btnLoadTransaction.setForeground(Color.DARK_GRAY);
		btnLoadTransaction.setBounds(153, 237, 145, 29);
		contentPane.add(btnLoadTransaction);
		
		
		btnLoadTransaction.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM transaction";
					ResultSet rs = stmt.executeQuery(sql);
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblTransaction.getModel();
					
					int cols = rsmd.getColumnCount(); 
					String[] colName =  new String[cols];
					
					for (int i = 0; i <cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id,namaCustomer,namaProduct,quantity,tanggal;
						
						while(rs.next()) {
							id = rs.getString(1);
							namaCustomer = rs.getString(2);
							namaProduct = rs.getString(3);
							quantity = rs.getString(4);
							tanggal = rs.getString(5);
							String[] row = {id,namaCustomer,namaProduct,quantity,tanggal};
							model.addRow(row);
						}
					}			    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
		});

	}

}
