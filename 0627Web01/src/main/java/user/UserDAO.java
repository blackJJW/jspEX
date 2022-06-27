package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

// Data Access Object = 데이터에 접근 하는 역할을 맡은 객체
public class UserDAO {
	private Connection conn; // 자바랑 db 연결하는 객체
	private PreparedStatement pstmt; // 준비된 문장 = 쿼리 준비
	private ResultSet rs; // 쿼리 실행 후 결과를 담을 객체
	
	// 생성자
	public UserDAO() {
		try {
			String dbURL = "jdbc:mysql://localhostL3306/JSPTEST?serverTimezone=UTC";
			// jdbc : Java DataBase Connectivity
			String dbId = "root";
			String dbPassword = "2155";
			
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(dbURL, dbId, dbPassword);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	// 로그인 기능 수행 함수. 로구인 화면에서 유저가 아이디 비번을 치고 밑의 로그인 버튼을 눌렀을 때 실행
	public int login(String userID, String userPassword) {
		String SQL ="SELECT userPassword FROM TABLE_USER WHERE userID=?";
		// 실행ㅎ할 쿼. 유저가 친 아이티에 해당하는 비밈번호를 가져온다.
		try {
			pstmt = conn.prepareStatement(SQL); // 문자열 쿼리를 pstmt에 대입
			pstmt.setString(1, userID); // 첫번 째 물음표에 userID값 대입
			// 프로그래밍 언어에서 인덱스는 0부터 시작이지만 쿼리에서는 1부터 시작
			rs = pstmt.executeQuery();
			if(rs.next()) { // 결과의 리스트를 받았는데 아음해의 데이터가 있으면
				if(rs.getString(1).equals(userPassword)){
					// 남은 것의 첫번재의 값이 login 함수를 호출할 대 전달받은 비밀번호와 같은지 검사
					return 1; // 로그인 성공
				} else {
					return 0; // 로그인 실패
				}
				
			}
			return -1; // 아이디 없음
		} catch(Exception e) {
			e.printStackTrace();
		}
		return -2; // 데이터 베이스 오류
	}
}
