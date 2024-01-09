# JAVA Spring boot DB

## INFO
*   강의 명  : inflearn 김영한 DB 1편
*   OS : mac m2
*   JAVA version : 17
*   dependency : lombok,h2,jdbc-api

## goal
* DB config에 대한 전반적인 이해. 
* DB connect


### MEMO
* 어떤 DB에도 공통된 인터페이스를 제공 jdbc interface
  * connection : 연결
  * statement : SQL(쿼리문을 담은 내용)
  * ResultSet : SQL 응답 값.
* 위의 interface를 구현한 각각의 벤더사에 대한 구현체가 존재. >> 구현체(Driver)
* 각 DB에 맞는 SQL 구문을 작성해야 한다. 
* SQL Injection 공격을 예방하기 위해 , PreparedStatemente로 파라미터를 바인딩 해줘야 한다. 
* PreparedStatement객체의 사용법 
  * executeUpdate는 변경 
  * executeQuery는 조회


