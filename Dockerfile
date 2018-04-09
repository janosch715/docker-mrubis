FROM jfloff/glassfish-2.1.1:6
MAINTAINER João Loff <jfloff@gsd.inesc-id.pt>

WORKDIR /home/mrubis
COPY mrubis /home/mrubis
EXPOSE 4848

# init database schema right on build so we don't have to redo that each run
RUN ant start-database setup-database

# default command runs the test client
CMD ant start-server build deploy run-client
