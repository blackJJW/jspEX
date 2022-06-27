<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "user.UserDAO" %> <!-- DAO 인스턴스 -->
<%@ page import = "java.io.PrintWriter" %> <!-- 출력 도와주는 객체 -->
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="user" class="user.User" scope="page"></jsp:useBean>
<jsp:setProperty name="user" property="userID" />
<jsp:setProperty name="user" property="userPassword" />
<!-- jsp에서 클래스의 setter 함수를 호출한 거나 마찬가지 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<% 
	UserDAO userDAO = new UserDAO();
	int result = userDAO.login(user.getUserID(), user.getUserPassword());
	// 로그인 함수 실행 결과겂에 따라서 화면으로뿌려줄 스크립트 생성
	if(result==1) { // 로그인 성공시
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("location.href='index.jsp'");
		script.println("</script>");
	}
	else if(result==0){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('wrong password!!!')");
		script.println("history.back()");
		script.println("</script>");
	}
	else if(result==-1){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('no ID!!!')");
		script.println("history.back()");
		script.println("</script>");
	}
	else if(result==-2){
		PrintWriter script = response.getWriter();
		script.println("<script>");
		script.println("alert('DB problem!!!')");
		script.println("history.back()");
		script.println("</script>");
	}
%>
</body>
</html>