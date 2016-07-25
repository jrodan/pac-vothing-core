# Vothing 

## Project information

### Source Code Management

Git and Github is used for storing the source code. There are two repositories available.

VoThing Core, Distribution and Tests
https://github.com/jrodan/pac-vothing-core

VoThing Client
https://github.com/jrodan/pac-vothing-client

### Issue Tracking

Issues will be tracked in the context of the repositories in the integrated issue system in Github.

https://github.com/jrodan/pac-vothing-core/issues 

https://github.com/jrodan/pac-vothing-client/issues 

### Continous Integration

It is planned to use Travis in the first steps. The tool is completly integrated in Github and can be configured easily.

https://travis-ci.org/jrodan/

### Developer Suite

For the inital development IntelliJ IDEA is used.
	IntelliJ IDEA 2016.1.3
	Build #IU-145.1617, built on June 3, 2016

### Formatting

### File Encoding
Files use UTF-8 encoding and Unix delimiter 

### Naming Conventions
#### Modules
vothing-api: Contains all API interfaces
vothing-config: Contains configuration files (e.g. Code Formatting)
vothing-core: Contains the implementation of the API 
vothing-dist: Assembly project
vothing-remote: Remote Services
vothing-tests: Tests and Remote Tests (Jersey, JUnit, Arquillian)

### Technologies

#### dist
- maven 3.3.9

#### core
- Java 1.8.0
- maven 3.3.9
- JEE 7
- JAX-RX

#### client
- Node 6.2.1
- npm 3.9.3
- react 15.1
- to see the complete dependencies resolved by npm open https://github.com/jrodan/pac-vothing-client/blob/master/package.json 

## Development Setup
- Install MySQL and create a new Database and corresponding user. 
- Import DDL from /pac-vothing-core/vothing-dist/sql/vothing-db-0.0.1.sql
- Install WildFly 10.0.0Final
- Set mysql datasource "java:/MysqlDS" in the standalone.xml in the server 
- The private server key has to be set as server JVM parameter "vothing.server.private.key"

## Build
- `mvn clean source:jar javadoc:jar package install` (server has to run because of wildfly management mode for tests)
- want to build without remote tests then run `mvn clean source:jar javadoc:jar package install -DskipTests=true -pl !vothing-tests`
- managed mode for remote tests can be applied with a maven profile (TODO)

## Releases

E.g. the Release number 2.5.1

### Major X.0.0
Changes in the API

### Minor 0.X.0
New Features but without changing the API

### Bugfix 0.0.X
In this release only Bufixes are included

### Tags
Tags for releases: pac-presentation-X.X.X
Git flow will be used in future and the branching will be defined for this project.

## Testing

Automated tests will run, when the server is set up correctly. Later on, during a built on the CI server is running, the tests will also be done automatically. Manual smoke tests will be set up.

# TODO 1.0.0
- [ ] make remote tests runnable
- [ ] hash password on client side and save it encrypted
- [ ] Use Git Flow 
- [ ] Arquillian Tests
- [ ] Automated maven builds
- [ ] Logger
- [ ] use fontawesome icons
- [ ] fix: voting after updating a survey
- [ ] JaCoCo for code coverage
- [ ] consistency check of implemented methods and naming
- [ ] check permissions before persistence actions
- [ ] add exception handling
- [ ] testing
- [ ] move tests to volative database
- [ ] create more tests - test every usecase which are relevant for the client
- [ ] cleanup
- [ ] documentation
- [ ] create maven profile for management wildfly mode to run remote tests without the need of a started server
- [x] fix problem with monitoring and interception: WELD-001408: Unsatisfied dependencies for type VothingMonitoringCollector with qualifiers @Default at injection point [BackedAnnotatedField] @Inject private com.prodyna.pac.vothing.core.interceptor.VothingMonitoringInterceptor.vothingMonitoringMBean
- [x] separate core, model, services and remote services in separate release units
- [x] reimplementation of remote model level - how to improve handling of JSON entities for the frontend?
- [x] CI (travis integrated)
- [x] send permissions to client
- [x] create database create dump
- [x] adding / editing of surveys
- [x] create parent maven project
