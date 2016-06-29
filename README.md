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

### Naming Conventions

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
- to see the complete dependencies resolved by npm open https://github.com/jrodan/pac-vothing-client/package.json 

## Development Setup
- Install WildFly 10.0.0Final
- Set mysql datasource "java:/MysqlDS" in the standalone.xml in the server 
- The private server key has to be set as server JVM parameter "vothing.server.private.key"

## Build
- `mvn clean install`
- want to build without remote tests then run `mvn clean install -pl !vothing-tests`

## Testing

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
- [x] fix problem with monitoring and interception: WELD-001408: Unsatisfied dependencies for type VothingMonitoringCollector with qualifiers @Default at injection point [BackedAnnotatedField] @Inject private com.prodyna.pac.vothing.core.interceptor.VothingMonitoringInterceptor.vothingMonitoringMBean
- [x] separate core, model, services and remote services in separate release units
- [x] reimplementation of remote model level - how to improve handling of JSON entities for the frontend?
- [x] CI (travis integrated)
- [x] send permissions to client
- [x] create database create dump
- [x] adding / editing of surveys
- [x] create parent maven project