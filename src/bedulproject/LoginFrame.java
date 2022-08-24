
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
import java.awt.Color;
import javax.swing.ImageIcon;

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
		setResizable(false);
		setTitle("Login Account");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JFormattedTextField frmtdtxtfldInputYourUsername = new JFormattedTextField();
		passwordField = new JPasswordField();
		frmtdtxtfldInputYourUsername.setBounds(85, 79, 249, 29);
		contentPane.add(frmtdtxtfldInputYourUsername);
		
		passwordField.setBounds(85, 148, 249, 29);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(85, 51, 99, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(85, 120, 99, 16);
		contentPane.add(lblNewLabel_1);
		JButton btnNewButton = new JButton("Sign In");
		btnNewButton.setForeground(Color.DARK_GRAY);
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
		btnNewButton.setBounds(85, 202, 249, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon("/Users/macbook/Downloads/acoustic-guitar-pngrepo-com.png"));
		lblNewLabel_2.setBounds(213, 98, 414, 235);
		contentPane.add(lblNewLabel_2);
//		setUndecorated(true);
		setLocationRelativeTo(null);
	}
}
