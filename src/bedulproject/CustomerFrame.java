package bedulproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import com.toedter.components.JSpinField;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;

public class CustomerFrame extends JFrame {

	private JPanel contentPane;
	private JTextField txtNama;
	private JTextField txtAlamat;
	private JTextField txtNoTelp;
	private JTable tblData;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame() {
		setTitle("Customer Add");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 524, 451);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtNama = new JTextField();
		txtNama.setBounds(108, 59, 195, 26);
		contentPane.add(txtNama);
		txtNama.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Nama");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(6, 64, 61, 16);
		contentPane.add(lblNewLabel);
		
		txtAlamat = new JTextField();
		txtAlamat.setColumns(10);
		txtAlamat.setBounds(108, 97, 195, 26);
		contentPane.add(txtAlamat);
		
		JLabel lblAlamat = new JLabel("Alamat");
		lblAlamat.setForeground(Color.WHITE);
		lblAlamat.setBounds(6, 102, 61, 16);
		contentPane.add(lblAlamat);
		
		JLabel lblNewLabel_1 = new JLabel("Tanggal");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(6, 141, 61, 16);
		contentPane.add(lblNewLabel_1);
		
		JDateChooser txtDate = new JDateChooser();
		txtDate.setBounds(108, 135, 195, 26);
		contentPane.add(txtDate);
		
		JLabel lblNewLabel_2 = new JLabel("No Telp");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(6, 178, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		txtNoTelp = new JTextField();
		txtNoTelp.setBounds(108, 173, 195, 26);
		contentPane.add(txtNoTelp);
		txtNoTelp.setColumns(10);
		
		JButton btnSave = new JButton("Save");
		btnSave.setBackground(Color.DARK_GRAY);
		btnSave.setForeground(Color.DARK_GRAY);
		btnSave.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
			
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = String.format("INSERT INTO customer (nama,alamat,tanggal,no_hp) VALUES ('%s','%s','%s','%d')",txtNama.getText(),txtAlamat.getText(),txtDate.getDate(), Integer.parseInt(txtNoTelp.getText()));
					System.out.println(sql);
										stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Sucessfully Add");
					tblData.setModel(new DefaultTableModel());
					txtNama.setText("");
					txtAlamat.setText("");
					txtDate.setCalendar(null);;
					txtNoTelp.setText("");
					
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
			
			}
		});
		btnSave.setBounds(325, 173, 117, 29);
		contentPane.add(btnSave);
		
		JButton btnEdit = new JButton("Update");
		btnEdit.setBackground(Color.DARK_GRAY);
		btnEdit.setForeground(Color.DARK_GRAY);
		btnEdit.setBounds(325, 38, 117, 29);
		contentPane.add(btnEdit);
		
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = String.format("UPDATE customer SET nama = '%s', alamat = '%s', no_hp = '%d' WHERE id = '%s'",txtNama.getText(),txtAlamat.getText(),Integer.parseInt(txtNoTelp.getText()),tblData.getValueAt(tblData.getSelectedRow(), 0));
					System.out.println(sql);
				stmt.executeUpdate(sql);
					JOptionPane.showMessageDialog(null,"Sucessfully Update");
					tblData.setModel(new DefaultTableModel());
					txtNama.setText("");
					txtAlamat.setText("");
					txtDate.setCalendar(null);;
					txtNoTelp.setText("");
					
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
			
		});
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBackground(Color.DARK_GRAY);
		btnClear.setForeground(Color.DARK_GRAY);
		btnClear.setBounds(325, 79, 117, 29);
		contentPane.add(btnClear);
		
		JButton btnNewButton = new JButton("Show Data");
		btnNewButton.setBackground(Color.DARK_GRAY);
		btnNewButton.setForeground(Color.DARK_GRAY);
		btnNewButton.setBounds(202, 394, 117, 29);
		
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "SELECT * FROM customer";
					ResultSet rs = stmt.executeQuery(sql);
					java.sql.ResultSetMetaData rsmd = rs.getMetaData();
					DefaultTableModel model = (DefaultTableModel) tblData.getModel();
					
					int cols = rsmd.getColumnCount();
					String[] colName =  new String[cols];
					
					for (int i = 0; i <cols;i++) {
						colName[i] = rsmd.getColumnName(i+1);
						model.setColumnIdentifiers(colName);
						String id,nama,alamat,tanggal,nohp;
						
						while(rs.next()) {
							id = rs.getString(1);
							nama = rs.getString(2);
							alamat = rs.getString(3);
							tanggal = rs.getString(4);
							nohp = rs.getString(5);
							String[] row = {id,nama,alamat,tanggal,nohp};
							model.addRow(row);
						}
					}			    
					stmt.close();
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}
				
			}
		});
		
		
		contentPane.add(btnNewButton);
		
		tblData = new JTable();
		tblData.setBounds(52, 274, 447, 137);
		tblData.setCellSelectionEnabled(true);
		tblData.setColumnSelectionAllowed(true);
		
		JScrollPane scrollPane = new JScrollPane();
		tblData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				txtNama.setText((String) tblData.getValueAt(tblData.getSelectedRow(),1));
				txtAlamat.setText((String) tblData.getValueAt(tblData.getSelectedRow(), 2));
//				txtDate.setCalendar((Calendar) tblData.getValueAt(tblData.getSelectedRow(), 3));
				txtNoTelp.setText((String) tblData.getValueAt(tblData.getSelectedRow(), 4));
				btnSave.setEnabled(false);
			}
		});
		scrollPane.setBounds(17, 231, 488, 153);
		contentPane.add(scrollPane);
		scrollPane.setViewportView(tblData);
		
		JLabel lblNewLabel_3 = new JLabel("New label");
		lblNewLabel_3.setIcon(new ImageIcon("/Users/macbook/Downloads/acoustic-guitar-pngrepo-com.png"));
		lblNewLabel_3.setBounds(402, -74, 537, 333);
		contentPane.add(lblNewLabel_3);
		
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtNama.setText("");
				txtAlamat.setText("");
				txtDate.setCalendar(null);;
				txtNoTelp.setText("");
				
				
				
			}
		});
	}
}
