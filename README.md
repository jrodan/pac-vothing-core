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
- `mvn clean install` (server has to run because of wildfly management mode for tests)
- want to build without remote tests then run `mvn clean install -DskipTests=true`

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

# Project Structure

```
├── README.md
├── pac.iml
├── pom.xml
├── vothing-api
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── prodyna
│   │                   └── pac
│   │                       └── vothing
│   │                           └── api
│   │                               ├── Vothing.java
│   │                               ├── annotion
│   │                               │   └── VothingMonitoringAnn.java
│   │                               ├── constants
│   │                               │   ├── EntityOrder.java
│   │                               │   ├── PermissionEnum.java
│   │                               │   ├── RoleConstants.java
│   │                               │   └── VothingConstants.java
│   │                               ├── exception
│   │                               │   └── PrivateKeyException.java
│   │                               ├── model
│   │                               │   ├── BaseModel.java
│   │                               │   ├── BaseModelImpl.java
│   │                               │   ├── LoginCredentials.java
│   │                               │   ├── Permission.java
│   │                               │   ├── Role.java
│   │                               │   ├── Survey.java
│   │                               │   ├── SurveyOption.java
│   │                               │   ├── SurveyOptionRating.java
│   │                               │   ├── User.java
│   │                               │   └── api
│   │                               │       ├── PermissionInterface.java
│   │                               │       ├── RoleInterface.java
│   │                               │       ├── SurveyInterface.java
│   │                               │       ├── SurveyOptionInterface.java
│   │                               │       ├── SurveyOptionRatingInterface.java
│   │                               │       └── UserInterface.java
│   │                               └── service
│   │                                   ├── BaseService.java
│   │                                   ├── PermissionService.java
│   │                                   ├── RoleService.java
│   │                                   ├── SecurityService.java
│   │                                   ├── SurveyOptionRatingService.java
│   │                                   ├── SurveyOptionService.java
│   │                                   ├── SurveyService.java
│   │                                   └── UserService.java
│   └── vothing-api.iml
├── vothing-config
│   └── pac-code-style.xml
├── vothing-core
│   ├── pom.xml
│   ├── src
│   │   ├── main
│   │   │   ├── java
│   │   │   │   └── com
│   │   │   │       └── prodyna
│   │   │   │           └── pac
│   │   │   │               └── vothing
│   │   │   │                   └── core
│   │   │   │                       ├── VothingBean.java
│   │   │   │                       ├── annotion
│   │   │   │                       │   └── PermissionAnn.java
│   │   │   │                       ├── filter
│   │   │   │                       │   ├── CorsFilter.java
│   │   │   │                       │   ├── VothingPermissionFilter.java
│   │   │   │                       │   └── VothingSecurityFilter.java
│   │   │   │                       ├── interceptor
│   │   │   │                       │   └── VothingMonitoringInterceptor.java
│   │   │   │                       ├── model
│   │   │   │                       │   ├── PermissionImpl.java
│   │   │   │                       │   ├── RoleImpl.java
│   │   │   │                       │   ├── SurveyImpl.java
│   │   │   │                       │   ├── SurveyOptionImpl.java
│   │   │   │                       │   ├── SurveyOptionRatingImpl.java
│   │   │   │                       │   └── UserImpl.java
│   │   │   │                       ├── monitoring
│   │   │   │                       │   ├── PerformanceEntry.java
│   │   │   │                       │   ├── VothingMonitoringMXBean.java
│   │   │   │                       │   └── impl
│   │   │   │                       │       └── VothingMonitoringCollector.java
│   │   │   │                       ├── producer
│   │   │   │                       │   ├── EntityManagerProducer.java
│   │   │   │                       │   ├── LogProducer.java
│   │   │   │                       │   └── MBeanServerProducer.java
│   │   │   │                       └── service
│   │   │   │                           ├── BaseServiceImpl.java
│   │   │   │                           ├── PermissionServiceImpl.java
│   │   │   │                           ├── RoleServiceImpl.java
│   │   │   │                           ├── SecurityServiceImpl.java
│   │   │   │                           ├── SurveyOptionRatingServiceImpl.java
│   │   │   │                           ├── SurveyOptionServiceImpl.java
│   │   │   │                           ├── SurveyServiceImpl.java
│   │   │   │                           └── UserServiceImpl.java
│   │   │   └── resources
│   │   │       └── META-INF
│   │   │           ├── persistence.xml
│   │   │           └── test-persistence.xml
│   │   └── test
│   │       ├── java
│   │       │   └── com
│   │       │       └── prodyna
│   │       │           └── pac
│   │       │               └── vothing
│   │       │                   └── test
│   │       │                       └── PersistenceTest.java
│   │       ├── resources
│   │       │   └── arquillian.xml
│   │       └── resources-jbossas-remote
│   │           └── test-persistence.xml
│   └── vothing-core.iml
├── vothing-dist
│   ├── assembly.xml
│   ├── documentation
│   ├── pom.xml
│   ├── sql
│   │   └── vothing-db-0.0.1.sql
│   ├── target
│   │   ├── Vothing\ App.zip
│   │   └── archive-tmp
│   └── vothing-dist.iml
├── vothing-remote
│   ├── pom.xml
│   ├── src
│   │   └── main
│   │       └── java
│   │           └── com
│   │               └── prodyna
│   │                   └── pac
│   │                       └── vothing
│   │                           └── remote
│   │                               ├── helper
│   │                               │   ├── SurveyConverter.java
│   │                               │   ├── SurveyOptionConverter.java
│   │                               │   └── SurveyOptionRatingConverter.java
│   │                               ├── model
│   │                               │   ├── BaseRemoteEntity.java
│   │                               │   ├── BaseRemoteEntityImpl.java
│   │                               │   ├── SurveyOptionRatingRemote.java
│   │                               │   ├── SurveyOptionRemote.java
│   │                               │   └── SurveyRemote.java
│   │                               └── service
│   │                                   ├── SecurityRemoteService.java
│   │                                   ├── SurveyOptionRatingRemoteService.java
│   │                                   ├── SurveyRemoteService.java
│   │                                   └── impl
│   │                                       ├── SecurityRemoteServiceImpl.java
│   │                                       ├── SurveyOptionRatingRemoteServiceImpl.java
│   │                                       └── SurveyRemoteServiceImpl.java
│   └── vothing-remote.iml
├── vothing-tests
│   ├── pom.xml
│   ├── src
│   │   └── test
│   │       ├── java
│   │       │   └── com
│   │       │       └── prodyna
│   │       │           └── pac
│   │       │               └── vothing
│   │       │                   └── test
│   │       │                       ├── BaseRESTTest.java
│   │       │                       ├── BaseTest.java
│   │       │                       ├── VotingRESTTest.java
│   │       │                       └── VotingServiceTest.java
│   │       ├── resources
│   │       │   ├── arquillian.xml
│   │       │   ├── beans.xml
│   │       │   └── persistence.xml
│   │       └── test.iml
│   └── vothing-tests.iml
└── vothing.iml
```

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
- [ ] SonarQube for static code analysis
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
