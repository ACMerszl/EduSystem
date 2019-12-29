package UI;

import java.sql.*;
import javax.swing.JFrame;

import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JPasswordField;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JComboBox;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import java.awt.Font;
import javax.swing.SwingConstants;




import java.awt.Color;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import DBManager.SqlTool;

public class MainUI extends JFrame implements ActionListener, MouseListener {
	
	private static ResultSet resultSet;
	private static String Pwd = null;
	

	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JTextArea textArea;
	private JLabel label;
	private JLabel label_1;
	private JLabel label_2 ;
	private JComboBox comboBox;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private static final String initStr = "123456";
	
	public MainUI() {
		
		setTitle("By-szl");
		setSize(1000, 600);  //窗体大小
		setLocation(300,100); // 屏幕位置
		getContentPane().setLayout(null);
		
		btnNewButton = new JButton("\u767B\u5F55");
		btnNewButton.setBounds(365, 432, 224, 30);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("login");
		getContentPane().add(btnNewButton);
		
		textArea = new JTextArea();
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
		label = new JLabel(new ImageIcon(imgIDNum));
		label.setBounds(365, 305, 24, 24);
		getContentPane().add(label);
		
		
		BufferedImage imgPwd = null;
		try {
			imgPwd = ImageIO.read(new File("./imgs/Pwd.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label_1 = new JLabel(new ImageIcon(imgPwd));
		label_1.setBounds(365, 352, 24, 24);
		getContentPane().add(label_1);
		
		BufferedImage imgIDType = null;
		try {
			imgIDType = ImageIO.read(new File("./imgs/IDType.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		label_2 = new JLabel(new ImageIcon(imgIDType));
		label_2.setBounds(365, 258, 24, 24);
		getContentPane().add(label_2);
		
		comboBox = new JComboBox();
		comboBox.setFont(new Font("宋体", Font.PLAIN, 14));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u5B66\u751F", "\u7BA1\u7406\u5458"}));
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(414, 258, 137, 23);
		getContentPane().add(comboBox);
		
		lblNewLabel = new JLabel("NCWU\u6559\u52A1\u7BA1\u7406\u7CFB\u7EDF");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 30));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(288, 157, 384, 44);
		getContentPane().add(lblNewLabel);
		lblNewLabel_1 = new JLabel("\u5FD8\u8BB0\u5BC6\u7801\uFF1F");
		lblNewLabel_1.addMouseListener((MouseListener) this);

		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setBounds(531, 397, 76, 25);
		getContentPane().add(lblNewLabel_1);
		
		setVisible(true);
		
		
	}
	
	static boolean check(String id, String pwd, String op) {
		
		boolean flagOne = false, flagTwo = false;
		String strOp = null, sql;
		
		if (op.equals("学生")) 
			sql = "SELECT * FROM  Student WHERE Sno = ?";
		else 
			sql = "SELECT * FROM  InfoAdmin WHERE Id = ?";
		
		String []paras = {id};
		SqlTool sqlTool = new SqlTool();
		resultSet = sqlTool.queryExecute(sql, paras);
		
		try {
			if (resultSet.next()) {
				flagOne = true;
				if (op.equals("学生")) 
					Pwd = resultSet.getString(8);
				else 
					Pwd = resultSet.getString(2);
			} 
			
			if (pwd.equals(Pwd) == true) 
				flagTwo = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			sqlTool.close();
		}
		return flagOne && flagTwo;
	}
	
	public static void main(String[] args) {
			new MainUI();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("login")) {
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
					if (op.equals("学生")) {
						if (initStr.equals(String.valueOf(passwordField.getPassword())) == true) {
							new FirstLogin(this, "By-szl", true, textArea.getText());
							return ;
						}
					}
					if (op.equals("学生"))
						new StuUI(textArea.getText());
					else new AdminUI();
					
					setVisible(false);
				} else {
					JOptionPane.showMessageDialog(null, "账号或密码错误！", "消息",JOptionPane.ERROR_MESSAGE);  
				}
			}
		}
	}
	
	public void mouseClicked(MouseEvent e) {
		if (e.getClickCount() == 1)
			new ForgetPwdUI(this, "By-szl", true);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}