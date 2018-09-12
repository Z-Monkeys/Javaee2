package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class DatabaseDao {
	public static String databaseType;
	public static String databaseUrl;
	public static String databaseUser;
	public static String databasePassword;
	Connection connection = null;
	Statement statement = null;
	ResultSet resultset = null;

	public DatabaseDao() throws Exception {
		Class.forName(databaseType);// 加载数据库驱动
		connection = DriverManager.getConnection(databaseUrl, databaseUser, databasePassword);// 连接数据库
		statement = connection.createStatement();// 创建数据库操作对象
	}
	
	public void close()throws Exception{
		connection.close();
	}

	public void query(String sql) throws Exception {
		resultset = statement.executeQuery(sql); // 执行查询命令，并返回查询结果
	}

	public int update(String sql) throws Exception {
		return statement.executeUpdate(sql);// 执行更新命令，并返回受影响的行数
	}

	public boolean next() throws Exception {
		return resultset.next();
	}

	public String getString(String filed) throws Exception {
		return resultset.getString(filed);// 获取字段
	}

	public Integer getInteger(String filed) throws Exception {
		return resultset.getInt(filed);// 获取字段
	}

	public LocalDateTime getLocalDateTime(String field)throws Exception{
		return resultset.getTimestamp(field).toLocalDateTime();
	}
	
	public Timestamp getTimestamp(String filed) throws Exception {
		return resultset.getTimestamp(filed);// 获取字段
	}

	public Float getFloat(String filed) throws Exception {
		return resultset.getFloat(filed);// 获取字段
	}

	public ArrayList<String> FiledList(String tableName) throws Exception {
		ArrayList<String> filedlist = new ArrayList<String>();
		String sql = "SELECT * FROM " + tableName + " LIMIT 1";// 一次查询一个元组
		query(sql);
		ResultSetMetaData fileds = resultset.getMetaData();// 记录元数据
		for (int i = 1; i < fileds.getColumnCount() + 1; i++) {
			filedlist.add(fileds.getColumnName(i));// 获取查询到的元组各个字段名称并存放进数列
		}
		return filedlist;
	}

	public int getCount(String sql) throws Exception {
		query(sql);
		while (next()) {
			return this.getRs().getInt("count1");// 获取结果集中符合条件的记录数目
		}
		return 0;
	}

	public ArrayList<String> getStringFiledValue(String tableName, String filedName) throws Exception {
		ArrayList<String> FiledValueList = new ArrayList<String>();
		query("SELECT DISTINCT " + filedName + " FROM " + tableName);// 从表tableName查询字段filedName的所有不重复值
		while (next()) {
			FiledValueList.add(getString(filedName));// 获取字段filedName的值并放入队列
		}
		return FiledValueList;
	}
	//修改表的某个字段
	public Integer updateAStringFieldById(String tableName,Integer id,String fieldName,String fieldValue)throws Exception{
		String sql="update "+tableName+" set "+fieldName+"='"+fieldValue+"' where "+
		tableName.toLowerCase()+"Id="+id.toString();
		return update(sql);
	}
	//判断用户Id是否存在
	public boolean hasId(String tableName,Integer id)throws Exception{
		String sql="select * from "+tableName.toLowerCase()+" where "+
		tableName.toLowerCase()+"Id="+id.toString();
		query(sql);
		if(next()){
			return true;
		}else{
			return false;
		}
		
	}
	
	public void getById(String tableName,Integer id)throws Exception{
		String sql="select * from "+tableName.toLowerCase()+" where "+tableName+"Id='"+id.toString()+"' ";
		query(sql);
	}
	
	public Integer deletes(String tableName,String ids,DatabaseDao databaseDao)throws Exception{
		if(ids!=null&&ids.length()>0){
			String sql="delete from "+tableName+" where "+tableName.toLowerCase()+"Id in ("+ids+") ";
			return databaseDao.update(sql);
		}else{
			return -1;
		}
		
	}
	
	public ResultSet getRs() {
		return resultset;// 返回查询结果集
	}

	public void setRs(ResultSet resultset) {
		this.resultset = resultset;
	}

	public void setAutoCommit(boolean f) throws Exception {
		connection.setAutoCommit(f);// 当f为true时，自动提交事务；f为false时禁用自动提交
	}

	public void commit() throws Exception {
		connection.commit();// 手动提交sql命令
	}

	public void setConnction(Connection connection) {
		this.connection = connection;
	}

	public Connection getConnection() {
		return connection;// 返回连接对象
	}
}
