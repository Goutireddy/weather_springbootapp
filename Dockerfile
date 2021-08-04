FROM maven:3.3-jdk-8

RUN mkdir APP

COPY . APP/

RUN cd APP

EXPOSE 8080

CMD [ "sh", "APP/startapp.sh" ]

ENTRYPOINT [ "sh", "APP/startapp.sh" ]