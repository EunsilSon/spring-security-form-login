# Spring Security의 Form Login을 활용한 인증 구현
[[Spring Security] Form Login 인증 구현하기](https://velog.io/@eunsilson/Spring-Security-Form-Login%EC%9D%84-%EC%BB%A4%EC%8A%A4%ED%85%80%ED%95%B4-%EC%9D%B8%EC%A6%9D-%EA%B5%AC%ED%98%84%ED%95%98%EA%B8%B0) 포스팅의 실제 코드입니다.  
Form Login의 실행 흐름과 클래스 별 세부 설명은 위 포스팅에서 확인할 수 있습니다.

<br>

# 개발 환경
- Spring Boot 3.2.6
- Spring Security 6
- JDK 17
- Mustache
- MySQL 5.7 (Local)

<br>

# 실행 방법
1. 해당 프로젝트의 개발 환경과 동일하게 준비합니다.
    - IntelliJ 사용자의 경우 IDE 내에서 JDK 설치 가능

2. 해당 저장소의 zip 파일을 다운로드 받습니다.

3. application.yml에서 포트 및 데이터베이스를 자신의 상태에 맞게 변경합니다.

<br>

# 디렉토리 구조
```
┌── config/  
│ ├── auth/
│ │ ├── PrincipalDetailService  
│ │ └── PrincipalDetails   
│ ├── SecurityConfig  
│ └── WebMvcConfig  
├── controller/  
│ └── IndexController  
├── model/
│ └── User  
└── repository/
  └── UserRepository  
 
```

<br>

# 실행 흐름
1. `IndexController` : 페이지 이동 및 회원가입 진행
    - 데이터베이스에 User 정보 저장

<br>

2. `PrincipalDetailService` : Security가 대신 로그인을 진행
    - `UserDetailService`의 구현체
    - 로그인 컨트롤러를 만들지 않아도 됨

<br>

3. `PrincipalDetails` : Security Session 등록
    - `UserDetails`의 구현체
    - 로그인 성공 후에만 세션에 저장

<br>

4. `IndexController` : 로그인 성공 후 index.html 리다이렉트

<br>

![image](https://github.com/EunsilSon/spring-security-form-login/assets/46162801/07a03045-3ee1-4a1b-9566-1a7928f3597e)

회원가입과 로그인을 거치며 출력한 실제 콘솔창입니다. 