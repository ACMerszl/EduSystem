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

public class AdminInfoUpdata extends JDialog implements ActionListener {

	private String sno, Sname, Ssex, Sdata, Sclass, Smajor, Sdeparment, pwd;
	private static ResultSet resultSet;

	public AdminInfoUpdata(Frame owner, String title, boolean modal, InfoModel sm, int rowNum) {

		super(owner, title, modal);
//		for (int i = 0; i <= 7; i++) {
//			System.out.println(i + "***" + (String) sm.getValueAt(rowNum, i));
//		}

		getContentPane().setFont(new Font("宋体", Font.PLAIN, 16));
		setSize(550, 535); // 窗体大小
		setLocation(550, 100);
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
		textField.setEditable(false);
		textField.setFont(new Font("宋体", Font.PLAIN, 14));
		textField.setBounds(196, 22, 158, 21);
		textField.setText((String) sm.getValueAt(rowNum, 0));
		panel.add(textField);
		textField.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u59D3\u540D\uFF1A");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(119, 62, 58, 15);
		panel.add(lblNewLabel_1);

		textField_1 = new JTextField();
		textField_1.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_1.setBounds(196, 58, 158, 21);
		textField_1.setText((String) sm.getValueAt(rowNum, 1));
		panel.add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("\u6027\u522B\uFF1A");
		lblNewLabel_2.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_2.setBounds(119, 109, 58, 15);
		panel.add(lblNewLabel_2);

		textField_2 = new JTextField();
		textField_2.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_2.setBounds(196, 105, 158, 21);
		textField_2.setText((String) sm.getValueAt(rowNum, 2));
		panel.add(textField_2);
		textField_2.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("\u51FA\u751F\u5E74\u6708\uFF1A");
		lblNewLabel_3.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_3.setBounds(119, 159, 88, 15);
		panel.add(lblNewLabel_3);

		textField_3 = new JTextField();
		textField_3.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_3.setBounds(196, 155, 158, 21);
		textField_3.setText((String) sm.getValueAt(rowNum, 3));
		panel.add(textField_3);
		textField_3.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("\u884C\u653F\u73ED\u7EA7\uFF1A");
		lblNewLabel_4.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_4.setBounds(119, 200, 88, 15);
		panel.add(lblNewLabel_4);

		textField_4 = new JTextField();
		textField_4.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_4.setBounds(196, 196, 158, 21);
		textField_4.setText((String) sm.getValueAt(rowNum, 4));
		panel.add(textField_4);
		textField_4.setColumns(10);

		JLabel lblNewLabel_5 = new JLabel("\u4E13\u4E1A\uFF1A");
		lblNewLabel_5.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_5.setBounds(119, 244, 58, 15);
		panel.add(lblNewLabel_5);

		textField_5 = new JTextField();
		textField_5.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_5.setBounds(196, 240, 158, 21);
		textField_5.setText((String) sm.getValueAt(rowNum, 5));
		panel.add(textField_5);
		textField_5.setColumns(10);

		JLabel lblNewLabel_6 = new JLabel("\u9662\u7CFB\uFF1A");
		lblNewLabel_6.setFont(new Font("宋体", Font.PLAIN, 14));
		lblNewLabel_6.setBounds(119, 293, 58, 15);
		panel.add(lblNewLabel_6);

		textField_6 = new JTextField();
		textField_6.setFont(new Font("宋体", Font.PLAIN, 14));
		textField_6.setBounds(196, 289, 158, 21);
		textField_6.setText((String) sm.getValueAt(rowNum, 6));
		panel.add(textField_6);
		textField_6.setColumns(10);

		JButton btnNewButton = new JButton("\u63D0\u4EA4");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("click");
		btnNewButton.setFont(new Font("宋体", Font.PLAIN, 16));
		btnNewButton.setBounds(124, 398, 230, 42);
		panel.add(btnNewButton);

		JLabel label = new JLabel("\u5BC6\u7801\uFF1A");
		label.setFont(new Font("宋体", Font.PLAIN, 14));
		label.setBounds(119, 336, 58, 15);
		panel.add(label);

		textField_7 = new JTextField();
		textField_7.setBounds(198, 333, 156, 21);
		textField_7.setText((String) sm.getValueAt(rowNum, 7));
		panel.add(textField_7);
		textField_7.setColumns(10);

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
	private JTextField textField_7;

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("click")) {
//			sno = textField.getText();
//			Sname = textField_1.getText();
//			Ssex = textField_2.getText();
//			Sdata = textField_3.getText();
//			Sclass = textField_4.getText();
//			Smajor = textField_5.getText();
//			Sdeparment = textField_6.getText();
//			pwd = textField_7.getText();

			InfoModel model = new InfoModel();
			String sql = "update Student set Sname = ?, Ssex = ?, Sdate = ?, Sclass = ?, Smajor = ?, Sdeparment = ?, pwd = ? where Sno = ?";
			String[] paras = { textField_1.getText(), textField_2.getText(), textField_3.getText(),
					textField_4.getText(), textField_5.getText(), textField_6.getText(), textField_7.getText(), textField.getText()};

			boolean flag = true;
			if (!model.cudStu(sql, paras)) {
				JOptionPane.showMessageDialog(this, "修改学生信息失败");
				flag = false;
			}
			if (flag)
				setVisible(false);
		}
	}
}
