package UI;

import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import DBManager.SqlTool;

public class StuModel extends AbstractTableModel{
	
	private ResultSet resultSet;
	private Vector columnNames; 
	private Vector rowDates; 
	private String Sno, average;

	public StuModel() {
		
	}
	
	public boolean cudStu(String sql, String []paras) {
		return new SqlTool().cudExecute(sql, paras);
	}
	
	public void queryStu(String sql, String []paras) 
	  { 
		
		
		SqlTool sqlTool = null;
		columnNames.add("序号");
		columnNames.add("课程号");
		columnNames.add("课程名");
		columnNames.add("学分");
		columnNames.add("绩点");
		columnNames.add("学分绩点");
		
		try {
			sqlTool = new SqlTool();
			resultSet = sqlTool.queryExecute(sql, paras);
			double studyScore = 0, multySum = 0, multy = 0;		
			int index = 1;
			while (resultSet.next()) {
				Vector tmp = new Vector();
				double tmp1 = resultSet.getDouble(4), tmp2;
				if (resultSet.getDouble(6) == 0)
					tmp2 = resultSet.getDouble(5);
				else 
					tmp2 = resultSet.getDouble(6);
				tmp.add(String.valueOf(index));
				tmp.add(resultSet.getString(1));
				tmp.add(resultSet.getString(2));
				tmp.add(resultSet.getString(3));
				if (resultSet.getDouble(6) == 0)
					tmp.add(resultSet.getString(5));
				else
					tmp.add(resultSet.getString(6));
				multy = tmp1 * tmp2;
				
				String format = new DecimalFormat("#.00").format(multy);
//				System.out.println(format);
				tmp.add(format);
				
				studyScore += tmp1;
				multySum += multy;
				index++;
				rowDates.add(tmp);
//				System.out.println(studyScore + "**" + multy);
			}
			
//			System.out.println(multySum);
			double ans = multySum / studyScore;		
			
			String format = new DecimalFormat("#.00").format(ans);
//			System.out.println(format);
			average = format;
//			System.out.println(average);
			
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			sqlTool.close();
		}
	  } 
	
	  public String getAverage() {
		  return average;
	  }
	  
	 @Override
	  public int getColumnCount() { 
	    // TODO Auto-generated method stub 
	    return this.columnNames.size(); 
	  } 
	  
	  @Override
	  public int getRowCount() { 
	    // TODO Auto-generated method stub 
	    return this.rowDates.size(); 
	  } 
	  
	  @Override
	  public Object getValueAt(int row, int col) { 
	    // TODO Auto-generated method stub 
	    if(!rowDates.isEmpty()) 
	      return ((Vector)this.rowDates.get(row)).get(col); 
	    else
	      return null; 
	  } 
	  
	    
	  @Override
	  public String getColumnName(int column) { 
	    // TODO Auto-generated method stub 
	    return (String)this.columnNames.get(column); 
	  } 
}
