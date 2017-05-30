# mRUBiS
Docker image for the [modular Rice University Bidding System (mRUBiS)](https://www.hpi.uni-potsdam.de/giese/public/mdelab/mdelab-projects/case-studies/mrubis/), an online marketplace realized with the Enterprise Java Beans 3 (EJB3) and deployed to Glassfish. In their words:

> Originally, RUBiS (Rice University Bidding System) "is an auction site prototype modeled after eBay.com that is used to evaluate application design patterns and application servers performance scalability" (cf. RUBiS website). (...) We (mRUBiS) modularized the architecture of RUBiS by having multiple, explicit components (multiple EJB modules for different services of RUBiS).

Most of the work in putting mRUBiS working was made on getting the required Glassfish version up and running. This effort was also *dockerized* in a [separate project](https://github.com/jfloff/docker-glassfish-2.1.1) (the base image of this project). Some small changes to mRUBiS itself were also needed, which I detail below.

**Don't forget to acknowledge mRUBiS team if you use this repo**. I couldn't find a reference on how to cite mRUBiS, but from what I've seen you could either [cite the website](https://www.hpi.uni-potsdam.de/giese/public/mdelab/mdelab-projects/case-studies/mrubis/) directly, or [this publication](http://dl.acm.org/citation.cfm?id=2555612) where mRUBiS is used as a case study.

### mRUBIS Usage

For more details please refer to mRUBiS [README](mrubis/ReadMe.txt) or their website:

- `ant start-server`: Starts the GlassFish application server (including the database server as part of GlassFish)

- `ant stop-server`: Stops the GlassFish application server (including the database server as part of GlassFish)

- `ant setup-database`: Creates the mRUBiS database and its schema, and inserts test data into the database

- `ant reset-database`: Resets the data in the database to its original state

- `ant cleanup-database`: Destroys the database (and thus all data contained in the database)

- `ant build`: Compiles and packages mRUBiS to EJB modules that are located in the
  subfolder 'dist' and that can be deployed to GlassFish

- `ant clean`: Cleans up the artifacts created by 'ant build'

- `ant deploy`: Deploys all EJB modules of mRUBiS to GlassFish

- `ant undeploy`: Undeploys all EJB modules of mRUBiS from GlassFish

I've also added a new task specifically to run the test client:
- `ant run-client`: Runs the default [test client](mrubis/client/src/de/hpi/sam/rubis/client/main/ClientSession.java)

### Docker Usage
If you are not familiar with Docker, or just forget the commands all the time like me, here is a resumé:
```
# build image
docker build --rm -t jfloff/mrubis .

# run provided test client
docker run --rm -ti jfloff/mrubis

# run in "dev" mode linking to Glassfish's admin panel and opening bash
docker run --rm -p 4848:4848 -ti jfloff/mrubis bash

# in "dev" you probably also want to mount a volume for the mRUBiS folder
docker run --rm -v "$(pwd)/mrubis:/home/mrubis" -p 4848:4848 -ti jfloff/mrubis bash
```


### Changes made to original mRUBiS
- Added an ant task to run the test client it `ant run-client`
- As their [README](mrubis/ReadMe.txt#L83) states, had to change the `glassfish.home` property at `build.properties` file to the `$GLASSFISH_HOME` env var, provided by the [base Glassfish image](https://github.com/jfloff/docker-glassfish-2.1.1)
- Added a `.gitignore`


### Contribute
Feel free to contribute to this repo in any way you find fit.


## License
MIT. See the `LICENSE` file in this repository.
