FROM java:8
COPY target/ProjektTwo-0.0.1-SNAPSHOT.jar .
EXPOSE 8085
CMD java -jar ProjektTwo-0.0.1-SNAPSHOT.jar
