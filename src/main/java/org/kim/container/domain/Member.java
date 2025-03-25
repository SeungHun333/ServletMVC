package org.kim.container.domain;

import java.sql.Date;

public class Member {
    private int userNo;
    private String id;
    private String password;
    private String name;
    private String email;
    private String phone;
    private Date birthDate;
    private char gender;
    private String address;
    private Date joinDate;
    private Date lastLogin;

    public Member(int userNo, String id, String password, String name, String email, String phone, Date birthDate, char gender, String address, Date joinDate, Date lastLogin) {
        this.userNo = userNo;
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.joinDate = joinDate;
        this.lastLogin = lastLogin;
    }

    public int getUserNo() {
        return userNo;
    }

    public String getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public char getGender() {
        return gender;
    }

    public String getAddress() {
        return address;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public Date getLastLogin() {
        return lastLogin;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setLastLogin(Date lastLogin) {
        this.lastLogin = lastLogin;
    }

    public void printMemberInfo() {
        System.out.println("회원 번호 (userNo): " + userNo);
        System.out.println("아이디 (id): " + id);
        System.out.println("이름 (name): " + name);
        System.out.println("이메일 (email): " + email);
        System.out.println("전화번호 (phone): " + phone);
        System.out.println("생년월일 (birthDate): " + birthDate);
        System.out.println("성별 (gender): " + gender);
        System.out.println("주소 (address): " + address);
        System.out.println("가입일 (joinDate): " + joinDate);
        System.out.println("마지막 로그인 (lastLogin): " + lastLogin);
        System.out.println("--------------------------------------------");
    }
}
