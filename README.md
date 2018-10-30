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

* ``` docker network ls ``` lists all docker networks
*       
    docker run -d -p 2379:2379 --name etcd --volume=/tmp/etcd-data:/etcd-data quay.io/coreos/etcd:latest  /usr/local/bin/etcd --name my-etcd-1 --data-dir /etcd-data --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://0.0.0.0:2379 --listen-peer-urls http://0.0.0.0:2380 --initial-advertise-peer-urls http://0.0.0.0:2380 --initial-cluster my-etcd-1=http://0.0.0.0:2380    --initial-cluster-token my-etcd-token --initial-cluster-state new  --auto-compaction-retention 1 -cors="*"
Zagon 
    
    
# TRAVIS CI
 * Connect github with travis CI
 * Enable CI for particular repo
 * Set environment variables ```$DOCKER_PASSWORD``` and ``` $DOCKER_USERNAME ```
 for automatic deployment to *dockerhub*
 
