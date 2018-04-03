
# Info

A sample skeleton project of an Eclipse plugins, supporting:
  * Tycho build and proper setup of ther Eclipse development environment (target platform)
    * with sources attached (TODO)
  * Supports non-OSGi, non-p2 Maven artifacts.


### Building the IDE:

 * To build, run:
 * `mvn clean verify` at the root of the repository. 

### Setup development environment:
 * Open `dev-environment.target` , set as target platform. 
  * (if this file is customized, save outside the repository)


### Other notes:
 * Make sure Maven output directories "target/" are marked as derived resources on Eclipse