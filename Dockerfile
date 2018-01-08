FROM openjdk:8

ADD target/todo-list.jar todo-list.jar
EXPOSE 9000
ENTRYPOINT ["java", "-jar", "todo-list.jar"]
