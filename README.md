# loginSpringServer-public

로그인을 위한 스프링으로 구현한 Rest API 서버이다.

DB 연동은 따로 DBManager를 이용하여 DB에 접근한다.  
  -> DBManager에서 보안적인 요소(IP, ID, Password 등)은 DBConfig로 따로 빼서 관리된다. -> 만약 fork 하여 만들면, DBConfig 안에 내용을 채워 넣을 것

기능
- 회원가입  
  -> 따로 Password 암호화는 없음
- 중복아이디 검사)
- 로그인(ID, Password 대조)  
  -> 대조할 때 따로 암호화는 안 하니, 보안을 신경 쓴다면 미리 클라이언트에서 암호화해서 보낼 것

추가로 할 일  
- build.gradle -> "implementation 'org.springframework.boot:spring-boot-starter-jdbc'" 주석 풀기
- resources/templates/apllication.properties에 밑의 내용 찾아서 채워 넣을 것  
spring.datasource.url=  
spring.datasource.username=  
spring.datasource.password=  
spring.datasource.driver-class-name=  
