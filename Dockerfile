# -------- مرحلة البناء --------
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app

# انسخ ملفات المشروع إلى داخل الحاوية
COPY pom.xml .
COPY src ./src

# ابنِ المشروع داخل الحاوية
RUN mvn clean package -DskipTests

# -------- مرحلة التشغيل --------
FROM openjdk:21
WORKDIR /app

# انسخ ملف الـ jar الناتج من المرحلة السابقة
COPY --from=build /app/target/*.jar app.jar

# افتح المنفذ
EXPOSE 8080

# شغّل التطبيق
ENTRYPOINT ["java", "-jar", "app.jar"]
