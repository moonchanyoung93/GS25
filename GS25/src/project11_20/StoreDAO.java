package project11_20;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Properties;
import java.util.Vector;

public class StoreDAO {
	//연결
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
	
	//목록
	public Vector listStore() {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" select no, name, company, location, price, amount, (price * amount) money ");
			sb.append(" from store order by location, price ");
			pstmt=conn.prepareStatement(sb.toString());
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				String no=rs.getString("no");
				String name=rs.getString("name");
				String company=rs.getString("company");
				String location=rs.getString("location");
				int price=rs.getInt("price");
				int amount=rs.getInt("amount");
				int money=rs.getInt("money");
				
				row.add(no);
				row.add(name);
				row.add(company);
				row.add(location);
				row.add(price);
				row.add(amount);
				row.add(money);
				
				items.add(row);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
		return items;
	}//end listStore

	
	//추가
	public int insertStore(StoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt= null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" insert into store (no, name, company, location, price, amount) ");
			sb.append(" values(?,?,?,?,?,?) ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getNo());
			pstmt.setString(2, dto.getName());
			pstmt.setString(3, dto.getCompany());
			pstmt.setString(4, dto.getLocation());
			pstmt.setInt(5, dto.getPrice());
			pstmt.setInt(6, dto.getAmount());
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
	}//end insertStore
	
	//수정
	public int updateStore(StoreDTO dto) {
		int result =0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" update store set company=?, location=?, price=?, amount=?  ");
			sb.append(" where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getCompany());
			pstmt.setString(2, dto.getLocation());
			pstmt.setInt(3, dto.getPrice());
			pstmt.setInt(4, dto.getAmount());
			pstmt.setString(5, dto.getName());
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
	}// end update
	
	//삭제
	public int deleteStore(StoreDTO dto) {
		int result=0;
		Connection conn=null;
		PreparedStatement pstmt=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" delete from store ");
			sb.append(" where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, dto.getName());
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
	
	//찾기
	public Vector searchStore(String name) {
		Vector items=new Vector();
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" select no, name, company, location, price, amount, (price* amount) money ");
			sb.append(" from store ");
			sb.append(" where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				Vector row=new Vector();
				row.add(rs.getString("no"));
				row.add(rs.getString("name"));
				row.add(rs.getString("company"));
				row.add(rs.getString("location"));
				row.add(rs.getInt("price"));
				row.add(rs.getInt("amount"));
				row.add(rs.getInt("money"));
				
				items.add(row);
			}
			
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
		return items;
	}
	
	//보기
	public StoreDTO viewStore(String name) {
		StoreDTO dto=null;
		Connection conn=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			conn=oraConn();
			StringBuilder sb=new StringBuilder();
			sb.append(" select no, name, company, location, price, amount, (price*amount) money ");
			sb.append(" from store where name=? ");
			pstmt=conn.prepareStatement(sb.toString());
			pstmt.setString(1, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String no=rs.getString("no");
				String company=rs.getString("company");
				String location=rs.getString("location");
				int price=rs.getInt("price");
				int amount=rs.getInt("amount");
				dto=new StoreDTO(no, name, company, location, price, amount);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(rs!=null)rs.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
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
		return dto;
	}//end viewStore()
	
}
