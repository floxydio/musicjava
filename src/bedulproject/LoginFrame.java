package bedulproject;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.GridLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JFormattedTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;

public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JFormattedTextField frmtdtxtfldInputYourUsername = new JFormattedTextField();
		passwordField = new JPasswordField();
		frmtdtxtfldInputYourUsername.setBounds(85, 55, 249, 29);
		contentPane.add(frmtdtxtfldInputYourUsername);
		
		passwordField.setBounds(85, 117, 249, 29);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(85, 38, 99, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(85, 96, 99, 16);
		contentPane.add(lblNewLabel_1);
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String abc = frmtdtxtfldInputYourUsername.getText();
				String bcd = passwordField.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection Con = DriverManager.getConnection("jdbc:mysql://localhost:3306/music","root", "");
					Statement stmt= Con.createStatement();
					String sql = "Select * from users where username ='"+abc+"' and password= '"+bcd.toString()+"'";
					ResultSet rs =stmt.executeQuery(sql);
					if(rs.next()) {

						JOptionPane.showMessageDialog(null,"Login Sucessfully");
					    MenuFrame field  = new MenuFrame();
					    field.setVisible(true);
					    setVisible(false);
					    }
					else {
						JOptionPane.showMessageDialog(null,"incorrect username or password" + abc + bcd);}
					Con.close();
				}catch(Exception e1) {System.out.println(e1);}


				
				
			}
		});
		btnNewButton.setBounds(148, 158, 117, 29);
		contentPane.add(btnNewButton);
//		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
