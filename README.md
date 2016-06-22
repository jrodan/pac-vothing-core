# PAC VoThing Core

## Setup
- ...
- The private server key has to be set as server JVM parameter "vothing.server.private.key"

## Development Setup

## Testing

# TODO
- fix: voting after updating a survey
- reimplementation of remote model level - how to improve handling of JSON entities for the frontend?
-- how can in a good way a local entity enriched with some client metadata?
- JaCoCo for code coverage
- separate core, model, services and remote services in separate release units
- consistency check of implemented methods and naming
- check permissions before persistence actions
- add exception handling
- testing
- move tests to volative database
- create more tests
-- test every usecase which are relevant for the client
- cleanup
- documentation

# Resolved Tasks
- CI (travis integrated)
- send permissions to client
- create database create dump
- adding / editing of surveys
- create parent maven project