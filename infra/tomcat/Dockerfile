FROM tomcat:10.0.27-jre17
COPY musubi.war /usr/local/tomcat/webapps/musubi.war
COPY server.xml /usr/local/tomcat/conf/server.xml
CMD ["catalina.sh", "jpda", "run"]
