# ServletMVC
- spring을 배우기 전에 간단하게 Servlet을 사용하여 MVC 패턴의 서버를 구성하기

![image.png](attachment:bd278fb4-5d62-456f-b906-9e3659936fb9:image.png)

- Presentation
    - Request
        - 클라이언트의 HTTP 요청을 **Servlet**이 받아 처리한다.
        - `@WebServlet` 어노테이션을 사용하여 URL 패턴과 서블릿을 매핑한다.
        - 요청 정보는 `HttpServletRequest` 객체를 통해 전달받는다.
    - Response
        - 처리 결과를 `HttpServletRequest`에 `setAttribute()`로 저장하고,
        - `RequestDispatcher`를 이용해 **JSP로 포워딩**하여 사용자에게 결과를 보여준다.
        - JSP는 **View의 역할**만 담당하며, 비즈니스 로직 없이 화면만 구성한다.
        - JSP에서는 `${}` 문법 등을 활용해 request 범위의 데이터를 출력한다.
- Business
    - Domain 패키지에 Member 모델을 사용하여 처리
    - Service
        - `service` 패키지에는 실제 **비즈니스 로직을 처리하는 클래스**가 위치한다.
        - 컨트롤러(Servlet)로부터 요청을 받아, 필요한 작업을 DAO를 통해 수행하고 결과를 다시 컨트롤러에 전달한다.
        - 트랜잭션 처리, 조건 분기, 예외 처리 등의 **로직 중심 처리**가 이루어진다.
- DataAccessLayer
    - DAO (Data Access Object)
        - `dao` 패키지에는 각 테이블에 대응하는 DAO 클래스가 위치한다.
        - DAO 클래스는 **SQL 실행**, **ResultSet 처리**, **자원 정리(close)** 등의 책임을 가진다.
        - DaoFactory를 활용한 책인 분배 및 관심 분리
            - ConnectionProtocol이라는 인터페이스 생성
                - Connection 객체 전달하는 getConnection 함수와 리소스를 닫아주는 closeResource 함수 선언
                - 코드
                    
                    ```java
                    public interface ConnectionProtocol {
                        public Connection getConnection () throws SQLException, ClassNotFoundException;
                        public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn);
                    }
                    ```
                    
            - ConnectionProtocol을 구현한 MemberDaoConnectionProtocol 클래스를 생성하여 해당 interface 함수 구현
                - 코드
                    
                    ```java
                    
                    public class MemberConnectionProtocol implements ConnectionProtocol {
                    
                        @Override
                        public Connection getConnection() throws SQLException, ClassNotFoundException {
                            Class.forName("oracle.jdbc.driver.OracleDriver");
                            return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/xepdb1", "hun", "3469");
                        }
                    
                        @Override
                        public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn) {
                            try {
                                if (rs != null) rs.close();
                                if (pstmt != null) pstmt.close();
                                if (conn != null) conn.close();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                    ```
                    
            - DaoFactory에서 ConnectionProtocol을 생성자 파라미터로 받는 UserDao 객체를 생성해서 리턴
                - 코드
                    
                    ```java
                    public class DaoFactory {
                        public MemberDao memberDao() {
                            return new MemberDao(new MemberConnectionProtocol());
                        }
                    }
                    ```
                    
- dataBase(Oracle)
    - userNo를 pk로한 멤버 테이블 생성
        - insert문 실행 시 sequence객체로 일련번호를 생성하여 userNo에 적용
    - id는 중복될 수 없으므로 유일성과 최소성 만족(pk로 사용 가능)
    - password, name 은 가입 시 필수 입력으로 무결성 제약
        
        ```sql
        CREATE TABLE MEMBER (
            USERNO     NUMBER PRIMARY KEY,
            ID          VARCHAR2(50) NOT NULL UNIQUE,
            PASSWORD    VARCHAR2(100) NOT NULL,
            NAME        VARCHAR2(50) NOT NULL,
            EMAIL       VARCHAR2(100),
            PHONE       VARCHAR2(20),
            BIRTH_DATE  DATE,
            GENDER      CHAR(1),
            ADDRESS     VARCHAR2(200),
            JOIN_DATE   DATE DEFAULT SYSDATE,
            LAST_LOGIN  DATE
        );
        ```
