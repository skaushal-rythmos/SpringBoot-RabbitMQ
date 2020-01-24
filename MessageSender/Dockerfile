FROM java:8
EXPOSE 8080
ADD target/message-sender.jar message-sender.jar 
ENTRYPOINT ["java","-jar","/message-sender.jar"]