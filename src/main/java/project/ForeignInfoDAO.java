package project;

import project.dto.KoreaInfoDTO;
import project.dto.ForeignInfoDTO;
import project.dto.MapInfoDTO;

import java.sql.Connection;
import java.util.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ForeignInfoDAO {
	
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "scott";
	private String pw = "tiger";
	
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection con;
	private DataSource dataFactory;
	
	
	public ForeignInfoDAO() {
		try {

			Context ctx = new InitialContext();
			Context envContext = (Context) ctx.lookup("java:comp/env");
			dataFactory = (DataSource) envContext.lookup("jdbc/oracle");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void connDB() {
		try {
			
			Class.forName(driver);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try {
			con = DriverManager.getConnection(url, id, pw);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			stmt = con.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
	
	
	public List<ForeignInfoDTO> selectForeignInfoList(){
		List<ForeignInfoDTO> list = new ArrayList<ForeignInfoDTO>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yy/MM/dd");
		Date date = new Date();
		String now = dateFormat.format(date);

//		String now = (String) new Date(0);
		
		
		try {
			con = dataFactory.getConnection();
//			int now = list.size();
			
			String query = "";
			query += "select * ";
			query += "from foreign_info where foreign_time = ?";
			System.out.println(query);
			pstmt = new LoggableStatement(con, query);
			pstmt.setString(1, now);

			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				int foreignId = rs.getInt("foreign_id");
				int foreignInfo = rs.getInt("foreign_info");
				int foreignDanger = rs.getInt("foreign_danger");
				int foreignDeath = rs.getInt("foreign_death");
				String foreignLocal = rs.getString("foreign_local");
				int foreignLocalInfo = rs.getInt("foreign_local_info");
				String foreignTime = rs.getString("foreign_time");
				
				
				ForeignInfoDTO dto = new ForeignInfoDTO();
				dto.setForeignId(foreignId);
				dto.setForeignInfo(foreignInfo);
				dto.setForeignDanger(foreignDanger);
				dto.setForeignDeath(foreignDeath);
				dto.setForeignLocal(foreignLocal);
				dto.setForeignLocalInfo(foreignLocalInfo);
				dto.setForeignTime(foreignTime);
				
				list.add(dto);
			}
			if(rs != null) {
				rs.close();
			}
			if(pstmt != null) {
				pstmt.close();
			}
			if(con != null) {
				con.close();
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
}
