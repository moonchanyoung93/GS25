package project11_20;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

public class LoginDAO {

	public Connection oraConn() {
		Connection conn=null;
		try {
			FileInputStream fis=new FileInputStream("d:\\oracle.prop");
			Properties prop=new Properties();
			prop.load(fis);

			String url = prop.getProperty("url");
			String id= prop.getProperty("id");
			String password=prop.getProperty("password");
			conn=DriverManager.getConnection(url, id, password);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}//end dbConn()
	
	
	
	public void listLogin(LoginDTO dto) {
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		 try {
			 conn=oraConn();
			 StringBuilder sb= new StringBuilder();
			 sb.append(" select userid, password ");
			 sb.append(" from login ");
			 sb.append(" where userid =? and password =? ");
			 pstmt=conn.prepareStatement(sb.toString());
			 pstmt.setString(1, dto.getUserid());
			 pstmt.setString(2, dto.getPassword());
			 rs=pstmt.executeQuery();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null) rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(pstmt!=null) pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null) conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}//finally
	}
	
	
	
	public int insertLogin(LoginDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" insert into login (userid, password, phone) ");
			sb.append(" values(?,?,?) ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getUserid());
			pstmt.setString(2, dto.getPassword());
			pstmt.setString(3, dto.getPhone());
			result=pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
			try {
				if(conn!=null)conn.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	
	
}
