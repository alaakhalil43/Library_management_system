# استخدم JDK 21
FROM openjdk:21-jdk-slim

# تعريف المتغير PORT
ENV PORT=8080

# تحديد مجلد العمل
WORKDIR /app

# نسخ ملف jar الناتج من المافن
COPY target/*.jar app.jar

# فتح المنفذ
EXPOSE 8080

# تشغيل التطبيق
CMD ["sh", "-c", "java -jar app.jar"]