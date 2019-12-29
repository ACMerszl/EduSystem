package AdminScore;

import java.awt.Font;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import javax.swing.JButton;

public class AdminScoreUpdata extends JDialog implements ActionListener {
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	
	public AdminScoreUpdata(Frame owner, String title, boolean modal, ScoreModel sm, int rowNum) {
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
		textField.setEditable(false);
		textField.setBounds(169, 90, 180, 21);
		textField.setText((String)sm.getValueAt(rowNum, 0));
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
		textField_1.setText((String)sm.getValueAt(rowNum, 4));
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(169, 215, 180, 21);
		panel.add(textField_2);
		textField_2.setText((String)sm.getValueAt(rowNum, 3));
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(169, 174, 180, 21);
		textField_3.setText((String)sm.getValueAt(rowNum, 2));
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(169, 130, 180, 21);
		textField_4.setText((String)sm.getValueAt(rowNum, 1));
		panel.add(textField_4);
		textField_4.setColumns(10);
		
		JButton btnNewButton = new JButton("\u786E\u5B9A");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("click");
		btnNewButton.setBounds(86, 307, 263, 35);
		panel.add(btnNewButton);
		
		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("click")) {
			ScoreModel model = new ScoreModel(); 
		      String sql = "update Score set CidNum = ?, Cname = ?, Sscore = ?, Srescore = ? where Sno = ?"; 
		      String []paras = {textField_4.getText(), textField_3.getText(), 
		    		  textField_2.getText(), textField_1.getText(), textField.getText()}; 
		      if(!model.cudStu(sql, paras)) 
		        JOptionPane.showMessageDialog(this, "修改学生信息失败"); 
		      //========关闭窗口 
		      this.setVisible(false);
		}
	}
}
