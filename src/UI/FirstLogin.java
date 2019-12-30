package UI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;

import java.sql.ResultSet;


import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import DBManager.SqlTool;
public class FirstLogin extends JDialog implements ActionListener {
	
	private static String Pwd, rePwd, textPro, answer;
	private static ResultSet resultSet;
	
	public FirstLogin(Frame owner, String title, boolean modal, String ID) {
		super(owner, title, modal);
		JPanel contentPane;
		JPasswordField passwordField;
		JPasswordField passwordField_1;
		JTextField textField;
		
		setSize(660,520);  //窗体大小
		setLocation(480,150); // 屏幕位置
		getContentPane().setLayout(null);
		
		JLabel label = new JLabel("\u9996\u6B21\u767B\u5F55\u8BF7\u91CD\u7F6E\u5BC6\u7801");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("宋体", Font.PLAIN, 20));
		label.setBounds(252, 57, 204, 24);
		getContentPane().add(label);
		
		JLabel label_1 = new JLabel("\u65B0\u7684\u5BC6\u7801\uFF1A");
		label_1.setFont(new Font("宋体", Font.PLAIN, 14));
		label_1.setBounds(210, 127, 72, 15);
		getContentPane().add(label_1);
		
		passwordField = new JPasswordField();
		//passwordField.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField.setBounds(285, 124, 213, 21);
		getContentPane().add(passwordField);
		
		JLabel label_2 = new JLabel("\u91CD\u590D\u5BC6\u7801\uFF1A");
		label_2.setFont(new Font("宋体", Font.PLAIN, 14));
		label_2.setBounds(210, 175, 72, 15);
		getContentPane().add(label_2);
		
		passwordField_1 = new JPasswordField();
		//passwordField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		passwordField_1.setBounds(285, 172, 212, 21);
		getContentPane().add(passwordField_1);
		
		JLabel label_3 = new JLabel("\u8BBE\u7F6E\u5BC6\u4FDD\u95EE\u9898\uFF1A");
		label_3.setFont(new Font("宋体", Font.PLAIN, 14));
		label_3.setBounds(210, 222, 101, 15);
		getContentPane().add(label_3);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u4F60\u7684\u5B66\u53F7\u6216\u5DE5\u53F7?", "\u4F60\u6700\u559C\u6B22\u7684\u989C\u8272?", "\u4F60\u6700\u559C\u6B22\u7684\u8FD0\u52A8?", "\u4F60\u7236\u4EB2\u7684\u540D\u5B57?", "\u4F60\u6BCD\u4EB2\u7684\u540D\u5B57?", "\u4F60\u7684\u5C0F\u5B66?", "\u4F60\u7684\u9AD8\u4E2D?", "\u4F60\u7684\u5927\u5B66?"}));
		comboBox.setBounds(307, 218, 191, 23);
		getContentPane().add(comboBox);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setBounds(306, 271, 192, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("\u5BC6\u4FDD\u95EE\u9898\u7B54\u6848\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(210, 275, 108, 15);
		getContentPane().add(lblNewLabel);
		
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				boolean flagOne = false, flagTwo = false;
				Pwd = String.valueOf(passwordField.getPassword());
				rePwd = String.valueOf(passwordField_1.getPassword());
				textPro = (String)comboBox.getSelectedItem();
				answer = textField.getText();	
				if (Pwd.length() == 0) 
					JOptionPane.showMessageDialog(null, "密码不能为空", "消息",JOptionPane.ERROR_MESSAGE);
				else {
					if (check(Pwd, rePwd) == false) {
						JOptionPane.showMessageDialog(null, "两次输入不一致", "消息",JOptionPane.ERROR_MESSAGE);
					} else {
						if (answer.length() != 0)
							flagTwo = true;
						else 
							JOptionPane.showMessageDialog(null, "密保答案不能为空", "消息",JOptionPane.ERROR_MESSAGE);
						if (flagTwo) {
							if (Pwd.equals("123456") == true) 
								JOptionPane.showMessageDialog(null, "不能使用初始密码123456", "消息",JOptionPane.ERROR_MESSAGE);
							else 
								flagOne = true;
						}
						if(flagOne && flagTwo) {
							String sql = "update Student set Pwd = ?, ProText = ?, answer = ? where Sno = ?"; 
							String paras[] = {Pwd, textPro, answer, ID};
							SqlTool sqlTool = new SqlTool();
							boolean flag = sqlTool.cudExecute(sql, paras);
							if (flag == false) 
								JOptionPane.showMessageDialog(null, "修改失败", "消息",JOptionPane.ERROR_MESSAGE);
							else {
								setVisible(false);
								sqlTool.close();
							}
						}
							
					}
				}
			}
			
		});
		
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 14));
		btnNewButton.setBounds(248, 331, 208, 41);
		getContentPane().add(btnNewButton);
		
		setVisible(true);
	}
	

	public static boolean check(String pwd, String rePwd) {
		if (pwd.equals(rePwd) == true) 
			return true;
		else
			return false;
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
//	public static void main(String args[]) {
//		new FirstLogin("201720207");
//	}
}