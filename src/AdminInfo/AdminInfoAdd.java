package AdminInfo;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.ResultSet;
import DBManager.SqlTool;

import javax.swing.JButton;

public class AdminInfoAdd extends JDialog implements ActionListener {
	
	private String sno, Sname, Ssex, Sdata, Sclass, Smajor, Sdeparment, pwd = "123456", proText = null, answer = null;
	private static ResultSet resultSet;
	
	public AdminInfoAdd(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 16));
		setSize(550, 535);  //窗体大小
		setLocation(550,100);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 10, 436, 450);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel.setBounds(119, 26, 58, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setBounds(196, 22, 158, 21);
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(119, 62, 58, 15);
		panel.add(lblNewLabel_1);
		
		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setBounds(196, 58, 158, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(119, 109, 58, 15);
		panel.add(lblNewLabel_2);
		
		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_2.setBounds(196, 105, 158, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(119, 159, 88, 15);
		panel.add(lblNewLabel_3);
		
		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_3.setBounds(196, 155, 158, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("\u884C\u653F\u73ED\u7EA7\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(119, 200, 88, 15);
		panel.add(lblNewLabel_4);
		
		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_4.setBounds(196, 196, 158, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("\u4E13\u4E1A\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(119, 244, 58, 15);
		panel.add(lblNewLabel_5);
		
		textField_5 = new JTextField();
		textField_5.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_5.setBounds(196, 240, 158, 21);
		panel.add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("\u9662\u7CFB\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(119, 293, 58, 15);
		panel.add(lblNewLabel_6);
		
		textField_6 = new JTextField();
		textField_6.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_6.setBounds(196, 289, 158, 21);
		panel.add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					sno = textField.getText();
					Sname = textField_1.getText();
					Ssex = textField_2.getText();
					Sdata = textField_3.getText();
					Sclass = textField_4.getText();
					Smajor = textField_5.getText();
					Sdeparment = textField_6.getText();
					if (sno.length() == 0 || Sname.length() == 0 || Ssex.length() == 0 || Sdata.length() == 0 || 
							Sclass.length() == 0 || Smajor.length() == 0 || Sdeparment.length() == 0)
						JOptionPane.showMessageDialog(null, "信息不能为空！", "消息",JOptionPane.WARNING_MESSAGE);
					else {
										
						boolean flag = false;
						
						String sql = "select * from Score where Sno = ?";
						String []paras = {sno};
						SqlTool sqlTool = new SqlTool();
						resultSet = sqlTool.queryExecute(sql, paras);
						
			    		try {	    			
			    			if (resultSet.next()) 
			    				flag = true;
			    		} catch (Exception ex) {
			    			ex.printStackTrace();
			    		} finally {
			    			sqlTool.close();
			    		}
								
						if (flag) {
							JOptionPane.showMessageDialog(null, "添加失败,该学生信息已存在", "消息",JOptionPane.ERROR_MESSAGE);
						} else {
							
							sql = "insert into Student values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
							String []parass = {sno, Sname, Ssex, Sdata, Sclass, Smajor, Sdeparment, pwd, proText, answer};
				    		
				    		try {
				    			
				    			if (sqlTool.cudExecute(sql, parass) == true) 
				    				JOptionPane.showMessageDialog(null, "添加成功", "消息",JOptionPane.INFORMATION_MESSAGE);
				    				
				    			else 
				    				JOptionPane.showMessageDialog(null, "添加失败", "消息",JOptionPane.ERROR_MESSAGE); 
				    			setVisible(false);
				    			
				    		} catch (Exception ex) {
				    			ex.printStackTrace();
				    		} finally {
				    			sqlTool.close();;
				    		}
						}
					}
			}
		});
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(119, 345, 230, 42);
		panel.add(btnNewButton);
		
		setVisible(true);
	}
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
}
