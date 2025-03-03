# 사용할 베이스 이미지 설정 (OpenJDK 17 예시)
FROM openjdk:17-jdk-slim

WORKDIR /app

# JAR 파일 복사
COPY target/*.jar BaseProject-1.0.0.jar

# 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java", "-jar", "BaseProject-1.0.0.jar"]

# 컨테이너가 사용할 포트 설정 (예: 8080)
EXPOSE 30000
