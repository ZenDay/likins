package likinsDatabase;

import java.sql.ResultSet;

public class SignInAndLogin extends Access_db{
    private String userID;  //�˺�
	private String password; //����
	private String sql;      //ִ�и��ֲ�����sql���
	
	public SignInAndLogin(){
		userID="";
		password="";
	}
	/** �����˺� */
	public void setUserID(String id){
		this.userID=id;
	}
	
	/** ��ȡ�˺� */
	public String getUserID(){
		return userID;
	}
	
	/** �������� */
	public void setPassword(String pass){
		this.password=pass;
	}
	
	/** ��ȡ���� */
	public String getPassword(){
		return password;
	}
	
	/** �ж��˺��Ƿ���� */
	public boolean isUserIDExist(String userid){
		//����sql����ѯ���˺��Ƿ����
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"';";
		ResultSet rs=null;
		boolean is_exist=false;
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){  //rs��Ϊnull�򷵻�true������null�򷵻�false
				is_exist=true;  //���˺Ŵ���
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());  //����쳣��Ϣ
		}
		return is_exist;
	}
	
	/** ����û�  ����true����ӳɹ������򲻳ɹ� */
	public boolean addUser(String id,String password){
		boolean is_add_success=false;  //�ж��Ƿ�ɹ�����
		
		sql="insert into userinfo (userID,password) value("+"'"+getUserID()+"','"+getPassword()+"');";
		is_add_success=super.exeSQL(sql);
		return is_add_success;	
	}
	
	/** �ж������Ƿ���ȷ����ȷ�򷵻�true�����򷵻�false */
	public boolean isPasswordValid(String userid,String pass){
		sql="select ID,userID from userinfo where userID="+"'"+getUserID()+"' and";
		sql=sql+"password="+"'"+getPassword()+"';";
		ResultSet rs=null;
		boolean is_password_valid=false;
		try{
			rs=super.exeSqlQuery(sql);
			while(rs.next()){   //���������Ϊ�գ����˺�����ƥ��
				is_password_valid=true;   //���ʾ����
				break;
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	return is_password_valid;
	}
}
