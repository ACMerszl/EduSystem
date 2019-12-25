package trial;

import java.sql.*;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.security.acl.NotOwnerException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.SwingConstants;

import javafx.scene.control.PasswordField;

import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainUI extends JFrame {
	
	
	private static DBManager dbManager;
	private static Connection connection;
	private static Statement stmt;
	private static ResultSet resultSet;
	private static String Pwd;
	
	
	public MainUI() {
		
		JPanel contentPane;
		JPasswordField passwordField;
		
		
		setSize(1000, 600);  //窗体大小
		setLocation(300,100); // 屏幕位置
		getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setBounds(365, 432, 224, 30);
		getContentPane().add(btnNewButton);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(414, 305, 137, 23);
		getContentPane().add(textArea);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(414, 352, 137, 23);
		getContentPane().add(passwordField);
		
		
		BufferedImage imgIDNum = null;
		try {
			imgIDNum = ImageIO.read(new File("./imgs/IDNum.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label = new JLabel(new ImageIcon(imgIDNum));
		label.setBounds(365, 305, 24, 24);
		getContentPane().add(label);
		
		
		BufferedImage imgPwd = null;
		try {
			imgPwd = ImageIO.read(new File("./imgs/Pwd.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label_1 = new JLabel(new ImageIcon(imgPwd));
		label_1.setBounds(365, 352, 24, 24);
		getContentPane().add(label_1);
		
		BufferedImage imgIDType = null;
		try {
			imgIDType = ImageIO.read(new File("./imgs/IDType.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		JLabel label_2 = new JLabel(new ImageIcon(imgIDType));
		label_2.setBounds(365, 258, 24, 24);
		getContentPane().add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u7BA1\u7406\u5458"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(414, 258, 137, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("NCWU\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(288, 157, 384, 44);
		getContentPane().add(lblNewLabel);
		JLabel lblNewLabel_1 = new JLabel("\u5FD8\u8BB0\u5BC6\u7801\uFF1F");
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 1)
					new ForgetPwdUI();
			}
		});
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(531, 397, 76, 25);
		getContentPane().add(lblNewLabel_1);
		
		setVisible(true);
		
		String initStr = "123456";
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check(textArea.getText(), String.valueOf(passwordField.getPassword() ) );
				//System.out.println(String.valueOf(passwordField.getPassword()));
				boolean flagOne = false, flagTwo =false;
				String op = (String)comboBox.getSelectedItem();
				if (textArea.getText().length() == 0) 
					JOptionPane.showMessageDialog(null, "学号/工号不能为空！", "消息",JOptionPane.ERROR_MESSAGE); 
				else {
					flagOne = true;
					if((String.valueOf(passwordField.getPassword()).length() == 0)) {
						
						JOptionPane.showMessageDialog(null, "密码不能为空！", "消息",JOptionPane.ERROR_MESSAGE); 
					} else 
						flagTwo = true;
				}
				if (flagOne && flagTwo) {
					if (check(textArea.getText(), String.valueOf(passwordField.getPassword()), op)) {
						if (initStr.equals(String.valueOf(passwordField.getPassword())) == true) {
							new FirstLogin(textArea.getText());
							return ;
						}
						
						if (op.equals("学生"))
							new StuUI(textArea.getText());
	//					else new AdminUI();
						
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "账号或密码错误！", "消息",JOptionPane.ERROR_MESSAGE);  
					}
				}
			}
		});
		
	}
	
	static boolean check(String id, String pwd, String op) {
		
		boolean flag = false;
		String strOp = null;
		if (op.equals("学生")) strOp = "InfoStu";
		else strOp = "InfoAdmin";
		String sql = "SELECT * FROM " + strOp + " WHERE Sno = " + id;
		dbManager = new DBManager();
		connection = dbManager.getConnection();
		try {
			stmt = connection.createStatement();
			resultSet = stmt.executeQuery(sql);

			if (resultSet.next()) {
				Pwd = resultSet.getString(2);
			} 
			
			if (pwd.equals(Pwd) == true) 
				flag = true;
			dbManager.releaseConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}
	
	public static void main(String[] args) {
			new MainUI();
	}
}