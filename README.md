# Servlet MVC Practice 🧩

Spring을 배우기 전에 간단하게 Servlet을 사용하여 MVC 패턴의 서버를 구성하기 위한 학습 프로젝트입니다.

---

## 🧱 프로젝트 구조

![MVC 구조도](./docs/mvc-diagram.png) <!-- 이미지 경로는 실제 위치에 따라 수정 -->

---

## 📂 패키지 계층별 구성

### 📌 Presentation

#### ✅ Request
- 클라이언트의 HTTP 요청을 **Servlet**이 받아 처리한다.
- `@WebServlet` 어노테이션을 사용하여 URL 패턴과 서블릿을 매핑한다.
- 요청 정보는 `HttpServletRequest` 객체를 통해 전달받는다.

#### ✅ Response
- 처리 결과를 `HttpServletRequest`에 `setAttribute()`로 저장하고,
- `RequestDispatcher`를 이용해 **JSP로 포워딩**하여 사용자에게 결과를 보여준다.
- JSP는 **View의 역할**만 담당하며, 비즈니스 로직 없이 화면만 구성한다.
- JSP에서는 `${}` 문법 등을 활용해 request 범위의 데이터를 출력한다.

---

### 📌 Business

#### ✅ Domain
- 도메인 모델(`Member`)은 DB의 `MEMBER` 테이블과 매핑되는 자바 객체이다.
- 회원 정보를 표현하는 데이터 구조로, 주로 Service와 DAO 간 데이터 전달에 사용된다.

#### ✅ Service
- `service` 패키지에는 실제 **비즈니스 로직을 처리하는 클래스**가 위치한다.
- 컨트롤러(Servlet)로부터 요청을 받아, 필요한 작업을 DAO를 통해 수행하고 결과를 다시 컨트롤러에 전달한다.
- 트랜잭션 처리, 조건 분기, 예외 처리 등의 **로직 중심 처리**가 이루어진다.

---

### 📌 DataAccessLayer

#### ✅ DAO (Data Access Object)
- `dao` 패키지에는 각 테이블에 대응하는 DAO 클래스가 위치한다.
- DAO 클래스는 **SQL 실행**, **ResultSet 처리**, **자원 정리(close)** 등의 책임을 가진다.

#### ✅ DaoFactory를 활용한 책임 분리 및 관심사 분리

**🔸 ConnectionProtocol 인터페이스**

```java
public interface ConnectionProtocol {
    public Connection getConnection () throws SQLException, ClassNotFoundException;
    public void closeResource(ResultSet rs, PreparedStatement pstmt, Connection conn);
}
