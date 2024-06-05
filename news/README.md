# 사용 기술
- SpringBoot 3.0.4
- Java 17
- MyBatis
- Database: (Embeded - h2)
- FrontEnd: thymeleaf

- 배포 
  - Jar 파일 생성 (IntelliJ 기준)
    - 우측 Gradle-Tasks-build-bootJar 실행
    - 프로젝트 폴더 build-libs 안에 jar 파일 생성
  - Jar 파일 서버 전송 (FTP)
  - 배포 명령어: nohup java -jar javaapi-0.0.1-SNAPSHOT.jar &

- 개선 방안
  - 수집 대상을 현재 a태그로 설정 하였으나 뉴스 사이트별 수집 태그&클래스명 반드시 세부 분석하여 검색 정확도 및 성능 향상 시켜야함