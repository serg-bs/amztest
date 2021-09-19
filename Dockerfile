FROM openjdk:11-jre-slim

ENV APP_HOME /app
RUN mkdir $APP_HOME
COPY ./entrypoint.sh $APP_HOME/
COPY ./build/libs/demo-0.0.1.jar $APP_HOME/
WORKDIR $APP_HOME
RUN chmod +x entrypoint.sh
ENTRYPOINT ["./entrypoint.sh"]
CMD ["demo"]