package likinsDatabase;

import java.sql.ResultSet;

public class SignInAndLogin extends Access_db{
    private String userID;  //账号
	private String password; //密码
	private String sql;      //执行各种操作的sql语句
	
	public SignInAndLogin(){
		userID="";
		password="";
	}
	/** 设置账号 */
	public void setUserID(String id){
		this.userID=id;
	}
	
	/** 获取账号 */
	public String getUserID(){
		return userID;
	}
	
	/** 设置密码 */
	public void setPassword(String pass){
		this.password=pass;
	}
	
	/** 获取密码 */
	public String getPassword(){
		return password;
	}
	
	/** 判断账号是否存在 */
	public boolean isUserIDExist(String userid){
		//发送sql语句查询该账号是否存在
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"';";
		ResultSet rs=null;
		boolean is_exist=false;
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){  //rs不为null则返回true，若是null则返回false
				is_exist=true;  //该账号存在
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());  //输出异常信息
		}
		return is_exist;
	}
	
	/** 添加用户  返回true则添加成功，否则不成功 */
	public boolean addUser(String id,String password){
		boolean is_add_success=false;  //判断是否成功插入
		
		sql="insert into userinfo (userID,password) value("+"'"+getUserID()+"','"+getPassword()+"');";
		is_add_success=super.exeSQL(sql);
		return is_add_success;	
	}
	
	/** 判断密码是否正确，正确则返回true，否则返回false */
	public boolean isPasswordValid(String userid,String pass){
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"' and";
		sql=sql+"password="+"'"+getPassword()+"';";
		ResultSet rs=null;
		boolean is_password_valid=false;
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){   //若结果集不为空，即账号密码匹配
				is_password_valid=true;   //则表示存在
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	return is_password_valid;
	}
}
