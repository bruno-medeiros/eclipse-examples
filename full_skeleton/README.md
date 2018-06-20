
# Info

A sample skeleton project of an Eclipse plugins, supporting:
  * Tycho build and proper setup of ther Eclipse development environment (target platform)
    * with sources attached (TODO)
  * Supports non-OSGi, non-p2 Maven artifacts.


### Building:

 * Execute `mvn -f maven_deps/ initialize` to initialize maven POM deps as p2 repo.
 * Executw `mvn clean verify` at the root of the repository. 

### Setup development environment:
 * Open `dev-environment.target`, set as target platform. 
  * (Don't modify this file direclty, save a copy outside the repository)


### Other notes:
 * Make sure Maven output directories "target/" are marked as derived resources on Eclipse