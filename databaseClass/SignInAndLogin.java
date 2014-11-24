package likinsDatabase;

import java.sql.ResultSet;

public class SignInAndLogin extends Access_db{
    private String userID; 
	private String password; 
	private String sql;      //sql statement to perform various operations 
	
	/** constructor */
	public SignInAndLogin(){
		userID="";
		password="";
	}
	/** set user ID */
	public void setUserID(String id){
		this.userID=id;
	}
	
	/** get user ID */
	public String getUserID(){
		return userID;
	}
	
	/** set password */
	public void setPassword(String pass){
		this.password=pass;
	}
	
	/** get password */
	public String getPassword(){
		return password;
	}
	
	/** Determine whether the ID is exist */
	public boolean isUserIDExist(String userid){
		//query the ID on table userinfo in mysql 
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"';";
		ResultSet rs=null;
		boolean is_exist=false;
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){  //if rs is null,return false;else return true
				is_exist=true;  //the ID is exist
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());  //print the error message
		}
		return is_exist;
	}
	
	/** add user to the table userinfo */
	public boolean addUser(String id,String password){
		boolean is_add_success=false;  //Determine whether add successful
		
		sql="insert into userinfo (userID,password) value("+"'"+getUserID()+"','"+getPassword()+"');";
		is_add_success=super.exeSQL(sql);
		return is_add_success;	
	}
	
	/** Determine whether the password is correct */
	public boolean isPasswordValid(String userid,String pass){
		//send the sql statement to select
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"' and";
		sql=sql+"password="+"'"+getPassword()+"';";
		ResultSet rs=null;      //The result set returned by the query 
		boolean is_password_valid=false;   //determine whether the password is correct
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){       //if rs is null,return false;else return true
				is_password_valid=true;   //the password is correct
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	return is_password_valid;
	}
}
