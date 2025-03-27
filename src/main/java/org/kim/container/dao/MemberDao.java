package org.kim.container.dao;

import org.kim.container.dao.connection.ConnectionProtocol;
import org.kim.container.domain.Member;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class MemberDao {
    private final ConnectionProtocol connectionProtocol;

    public MemberDao(ConnectionProtocol connectionProtocol) {
        this.connectionProtocol = connectionProtocol;
    }

    public List<Member> memberSelectAll() {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Member> memberList = new ArrayList<>();

        try {
            conn = connectionProtocol.getConnection();
            String query = "SELECT * FROM MEMBER";
            pstmt = conn.prepareStatement(query);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Member member = fromResultSetForSelect(rs);
                member.printMemberInfo();
                memberList.add(member);
            }

            return memberList;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionProtocol.closeResource(rs, pstmt, conn);
        }

        return memberList;
    }

    public Member memberSelectOne(int userNo) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectionProtocol.getConnection();

            String query = "SELECT * FROM MEMBER WHERE USERNO = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setInt(1, userNo);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = fromResultSetForSelect(rs);
                member.printMemberInfo();

                return member;
            } else {
                System.out.println("회원 정보가 없습니다.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionProtocol.closeResource(rs, pstmt, conn);
        }

        return null;
    }

    public Member memberSelectById(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = connectionProtocol.getConnection();

            String query = "SELECT * FROM MEMBER WHERE id = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();

            if (rs.next()) {
                Member member = fromResultSetForSelect(rs);
                member.printMemberInfo();

                return member;
            } else {
                System.out.println("회원 정보가 없습니다.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionProtocol.closeResource(rs, pstmt, conn);
        }

        return null;
    }

    public void memberInsert(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectionProtocol.getConnection();

            String query = "INSERT INTO member (userno, id, password, name, email, phone, birth_date, gender, address, join_date, last_login) " +
                    "VALUES (member_seq.NEXTVAL, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, member.getId());
            pstmt.setString(2, member.getPassword());
            pstmt.setString(3, member.getName());
            pstmt.setString(4, member.getEmail());
            pstmt.setString(5, member.getPhone());
            pstmt.setDate(6, new Date(member.getBirthDate().getTime()));
            pstmt.setString(7, String.valueOf(member.getGender()));
            pstmt.setString(8, member.getAddress());
            pstmt.setDate(9, new Date(member.getJoinDate().getTime()));
            pstmt.setDate(10, new Date(member.getLastLogin().getTime()));

            int result = pstmt.executeUpdate();
            if (result > 0) {
                System.out.println("회원 등록이 완료되었습니다!");
            } else {
                System.out.println("회원 등록 실패");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionProtocol.closeResource(null, pstmt, conn);
        }
    }

    public void memberUpdate(Member member) {
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            conn = connectionProtocol.getConnection();

            String query = "UPDATE member SET " +
                    "password = ?, name = ?, email = ?, phone = ?, address = ?, last_login = ? " +
                    "WHERE userNo = ?";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, member.getPassword());
            pstmt.setString(2, member.getName());
            pstmt.setString(3, member.getEmail());
            pstmt.setString(4, member.getPhone());
            pstmt.setString(5, member.getAddress());
            pstmt.setDate(6, member.getLastLogin());
            pstmt.setInt(7, member.getUserNo());

            int result = pstmt.executeUpdate();

            if (result > 0) {
                System.out.println("회원 정보가 업데이트되었습니다.");
            } else {
                System.out.println("업데이트할 회원을 찾지 못했습니다.");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connectionProtocol.closeResource(null, pstmt, conn);
        }
    }

    private Member fromResultSetForSelect(ResultSet rs) throws SQLException {
        int userNo = rs.getInt("userNo");
        String id = rs.getString("id");
        String password = rs.getString("password");
        String name = rs.getString("name");
        String email = rs.getString("email");
        String phone = rs.getString("phone");
        Date birthDate = rs.getDate("birth_date");
        String genderStr = rs.getString("gender");
        char gender = (genderStr != null && !genderStr.isEmpty()) ? genderStr.charAt(0) : ' ';
        String address = rs.getString("address");
        Date joinDate = rs.getDate("join_date");
        Date lastLogin = rs.getDate("last_login");

        return new Member(userNo, id, password, name, email, phone, birthDate, gender, address, joinDate, lastLogin);
    }
}
