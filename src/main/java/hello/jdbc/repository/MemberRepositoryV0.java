package hello.jdbc.repository;


import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;


/**
 * JDBC -DriverManager을 사용해서 구현.
 */
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException {

        String sql = "insert into member(member_id, money) values(?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        /**
         * SQL injection공격을 막기 위해, PreparedStatement를 통해 파라미터 바인딩을 해줘야 한다.
         */

        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());
            pstmt.executeUpdate();
            return member;
        } catch (SQLException e) {
            log.error("db-error", e);
            throw e;
        } finally {
            close(con,pstmt,null);
        }

    }

    public Member findById(String memberId) throws SQLException{
        String sql = "select * from member where member_id = ? " ;
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);
            rs = pstmt.executeQuery();
            if(rs.next()){
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }

        } catch (SQLException e) {
            log.error("db- error",e);
            throw e;
        } finally {
          close(con,pstmt,rs);
        }
    }



    /**
     * 사용한 자원을 닫아주기 위한 메소드
     */
    private void close(Connection con, Statement stmt, ResultSet rs){
        if(rs != null){
            try {
                rs.close();
            } catch (SQLException e) {
                log.info("error",e);
            }
        }


        if(stmt != null){
            try {
                stmt.close();
            } catch (SQLException e) {
                log.info("error", e);
            }
        }

        if(con != null){
            try {
                con.close();
            } catch (SQLException e) {
                log.info("error",e);
            }
        }
    }



    private static Connection getConnection() {
        return DBConnectionUtil.getConnection();
    }




}
