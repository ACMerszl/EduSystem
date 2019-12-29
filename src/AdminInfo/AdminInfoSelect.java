package AdminInfo;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;


import javax.swing.JButton;

public class AdminInfoSelect extends JDialog implements ActionListener {
	
	private static JComboBox comboBox;
	private JPanel contentPane;
	private JTextField textField;
	private String sql = null, key = null;
	public AdminInfoSelect(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		setSize(400, 200); // 窗体大小
		setLocation(600, 25);
		getContentPane().setLayout(null);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"\u6309\u5B66\u53F7\u67E5\u8BE2", "\u6309\u73ED\u7EA7\u67E5\u8BE2", "\u6309\u4E13\u4E1A\u67E5\u8BE2", "\u6309\u9662\u7CFB\u67E5\u8BE2"}));
		comboBox.setBounds(171, 32, 107, 23);
		getContentPane().add(comboBox);
		
		JLabel lblNewLabel = new JLabel("\u67E5\u8BE2\u65B9\u5F0F\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(70, 36, 95, 15);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BF7\u8F93\u5165\u5173\u952E\u5B57\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(70, 80, 107, 15);
		getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setBounds(169, 77, 175, 21);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("\u67E5\u8BE2");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 20));
		btnNewButton.setBounds(70, 117, 138, 36);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("query");
		getContentPane().add(btnNewButton);
		setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("query")) {
			String op = (String)comboBox.getSelectedItem();
			key = textField.getText();
			if (op.length() != 0 && key.length() != 0) {
				sql = null;
	
				if (op.equals("按学号查询")) {
					sql = "select * from Student where Sno = ?";
				} else if (op.equals("按班级查询")) {
					sql = "select * from Student where Sclass = ?";
				} else if (op.equals("按专业查询")) {
					sql = "select * from Student where Smajor = ?";
				} else {
					sql = "select * from Student where Sdeparment = ?";
				}
				
				setVisible(false);
			} else JOptionPane.showMessageDialog(null, "不能为空！", "消息",JOptionPane.WARNING_MESSAGE); 
		}
	}
	
	public String getSql() {
		return sql;
	}
	
	public String getKey() {
		return key;
	}
}
