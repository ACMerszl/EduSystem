package AdminScore;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;

import javax.swing.JTextField;

import DBManager.SqlTool;
import java.awt.Frame; 
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import DBManager.SqlTool;

public class AdminScoreAdd extends JDialog implements ActionListener{
	
	private String sno, cid, name, reScore, score;

	private static ResultSet resultSet;
	
	public AdminScoreAdd(Frame owner, String title, boolean modal) {
		
		super(owner, title, modal);
		getContentPane().setFont(new Font("宋体", Font.PLAIN, 16));
		setSize(550, 535);  //窗体大小
		setLocation(550,100);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(42, 37, 436, 359);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u5B66\u53F7\uFF1A");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel.setBounds(86, 92, 56, 15);
		panel.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(169, 90, 180, 21);
		
		panel.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("\u8BFE\u7A0B\u53F7\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_1.setBounds(86, 132, 78, 15);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("\u8BFE\u7A0B\u540D\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(86, 176, 78, 15);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("\u7EE9\u70B9\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(86, 217, 78, 15);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("\u8865\u8003\u7EE9\u70B9\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 16));
		lblNewLabel_4.setBounds(86, 259, 91, 15);
		panel.add(lblNewLabel_4);
		
		textField_1 = new JTextField();
		textField_1.setBounds(169, 257, 180, 21);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(169, 215, 180, 21);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(169, 174, 180, 21);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(169, 130, 180, 21);
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sno = textField.getText();
				cid = textField_4.getText();
				name = textField_3.getText();
				score = textField_2.getText();
				reScore = textField_1.getText();
				if (sno.length() == 0 || cid.length() == 0 || name.length() == 0 || score.length() == 0 || reScore.length() == 0)
					JOptionPane.showMessageDialog(null, "信息不能为空！", "消息",JOptionPane.WARNING_MESSAGE);
				else {
					float scoreOne = Float.valueOf(score.toString());
					float scoreTwo = Float.valueOf(score.toString());
					
					boolean flag = false;
					
					String sql = "select * from Score where Sno = ? and CidNum = ?";
					String []paras = {sno, cid};
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
						JOptionPane.showMessageDialog(null, "添加失败,该记录已存在", "消息",JOptionPane.ERROR_MESSAGE);
					} else {
						
						sql = "insert into Score values(?,?,?,?,?)";
						String []parass = {sno, cid, name, score, reScore};
			    		
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
		btnNewButton.setBounds(86, 307, 263, 35);
		panel.add(btnNewButton);
		
		setVisible(true);
	}
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
