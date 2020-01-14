FROM java:8
EXPOSE 8084
ADD target/message-sender.jar message-sender.jar 
ENTRYPOINT ["java","-jar","/message-sender.jar"]