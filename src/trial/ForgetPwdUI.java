package trial;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class ForgetPwdUI extends JFrame {
	
	private static DBManager dbManager;
	private static Connection connection;
	private static Statement stmt;
	private static ResultSet resultSet;
	private static String ID;
	private static String pro, proText, proAnswer;
	private static String pwd, rePwd;
	public ForgetPwdUI() {
		JPanel contentPane;
		JTextField textField;
		JPasswordField passwordField;
		JPasswordField passwordField_1;
		JTextField textField_1;
		
		
		
		setSize(660,520);  //窗体大小
		setLocation(480,150); // 屏幕位置
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u5B66\u53F7\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(144, 125, 52, 15);
		getContentPane().add(label);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setBounds(188, 122, 142, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		
		
		JLabel lblNewLabel = new JLabel("\u91CD\u7F6E\u5BC6\u7801");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 20));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(248, 48, 134, 21);
		getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBounds(83, 244, 461, 207);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel label_2 = new JLabel("\u65B0\u7684\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(62, 55, 72, 15);
		panel.add(label_2);
		
		JLabel label_3 = new JLabel("\u91CD\u590D\u5BC6\u7801\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(62, 95, 72, 15);
		panel.add(label_3);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField.setBounds(131, 52, 150, 21);
		panel.add(passwordField);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField_1.setBounds(131, 92, 150, 21);
		panel.add(passwordField_1);
		
		
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(83, 153, 461, 74);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel label_1 = new JLabel("\u5BC6\u4FDD\u95EE\u9898\uFF1A");
		label_1.setBounds(61, 10, 72, 21);
		panel_1.add(label_1);
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setBounds(61, 41, 281, 21);
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean flag = false;
				dbManager = new DBManager(); 
				connection = dbManager.getConnection();
				ID = textField.getText();
				if (ID.length() == 0) 
					JOptionPane.showMessageDialog(null, "学号不能为空！", "消息",JOptionPane.ERROR_MESSAGE);  
				else {
					String sql = "SELECT * FROM InfoStu";
					//System.out.println(ID + "*");
		
					try {
						stmt = connection.createStatement();
						
						resultSet = stmt.executeQuery(sql);
						
						while (resultSet.next()) {
							if (ID.equals(resultSet.getString(1)) == true) {
								
								flag = true;
								System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4));
								System.out.println(resultSet.getString(4) + "**");
								pro = resultSet.getString(3);
								//System.out.println(Pro + "***");
								proAnswer = resultSet.getString(4);
							}
							
						}
						
						if (flag == false) 
							JOptionPane.showMessageDialog(null, "学号不存在！", "消息",JOptionPane.ERROR_MESSAGE); 
						
						JLabel lblNewLabel_1 = new JLabel(pro);
						lblNewLabel_1.setBounds(129, 13, 213, 15);
						panel_1.add(lblNewLabel_1);
						lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
						
						if (flag) {
							panel_1.setVisible(true);
							panel.setVisible(true);
						}
						
					} catch (Exception ex) {
						ex.printStackTrace();
					} finally {
						dbManager.releaseConnection();
					}
				}
			}
		});
		
		
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(375, 121, 97, 23);
		getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("\u63D0\u4EA4");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pwd = String.valueOf(passwordField.getPassword());
				rePwd = String.valueOf(passwordField_1.getPassword());
				proText = textField_1.getText(); 
//				System.out.println(proText + "**^");
//				System.out.println(proAnswer + "**&");
				boolean flagOne = false, flagTwo = false;
				if (proAnswer.equals(proText) == false) 
					JOptionPane.showMessageDialog(null, "回答错误", "消息",JOptionPane.ERROR_MESSAGE); 
				else if (pwd.length() == 0)
					JOptionPane.showMessageDialog(null, "密码不能为空！", "消息",JOptionPane.ERROR_MESSAGE);  
				else {
					flagOne = true;
					if (pwd.equals(rePwd) == false) 
						JOptionPane.showMessageDialog(null, "两次输入不一致！", "消息",JOptionPane.ERROR_MESSAGE); 
					else {
						if (pwd.equals("123456") == true) 
							JOptionPane.showMessageDialog(null, "不能使用初始密码123456", "消息",JOptionPane.ERROR_MESSAGE);
						else
							flagTwo = true;
					}
				}
				
				String sql = "SELECT * FROM InfoStu";
				dbManager = new DBManager();
				connection = dbManager.getConnection();
				
				try {
					stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_UPDATABLE);
					
					resultSet = stmt.executeQuery(sql);
					
					while (resultSet.next()) {

						//System.out.println(resultSet.getString(1) + resultSet.getString(2) + resultSet.getString(3) + resultSet.getString(4));
					
						if (resultSet.getString(1).equals(ID) == true) {
							
							//System.out.println("!!!!!");
							//resultSet.updateString(1, ID);
							resultSet.updateString(2, pwd);
							resultSet.updateRow();
						}
					}
					
					
					
					if ((flagOne && flagTwo) == true)
						setVisible(false);
					//System.out.println("***");
				} catch (Exception ex) {
					ex.printStackTrace();
				} finally {
					dbManager.releaseConnection();
				}
				
			}
		});
		
		btnNewButton_1.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton_1.setBounds(62, 156, 219, 23);
		panel.add(btnNewButton_1);
		
		setVisible(true);
		panel_1.setVisible(false);
		panel.setVisible(false);
	}
	

//	public static void main(String[] args) {
//		new ForgetPwdUI();
//	}
}