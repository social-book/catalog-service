# DOCKER
## Commands
* ``` docker ps ``` lists all active images
* ``` docker images ``` lists all images
* ``` docker build -t nribic/service-catalog:0.0.1 ``` if in root (at Dockerfile dir) 
then create image. ``` nribic ``` is username on dockerhub
* ``` docker push nribic/service-catalog:0.0.1  ``` pushes created image to
docker hub
* ``` docker run nribic/service-catalog:0.0.1 ``` runs on download and run image 
* ``` docker tag nribic/service-catalog:0.0.1 nribic/service-catalog:latest```
this tags service 0.0.1 as latest. Its admin duty to do this. Kubernetes check
locally for latest and does not pull updated ones!!!    
    
# TRAVIS CI
 * Connect github with travis CI
 * Enable CI for particular repo
 * Set environment variables ```$DOCKER_PASSWORD``` and ``` $DOCKER_USERNAME ```
 for automatic deployment to *dockerhub*
 