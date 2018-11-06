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
* ```docker run -d -p 2379:2379 --name etcd --volume=/tmp/etcd-data:/etcd-data quay.io/coreos/etcd:latest  /usr/local/bin/etcd --name my-etcd-1 --data-dir /etcd-data --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://0.0.0.0:2379 --listen-peer-urls http://0.0.0.0:2380 --initial-advertise-peer-urls http://0.0.0.0:2380 --initial-cluster my-etcd-1=http://0.0.0.0:2380    --initial-cluster-token my-etcd-token --initial-cluster-state new  --auto-compaction-retention 1 -cors="*" ```
this process run etcd server so we can configure services in runtime
    
    
# TRAVIS CI
 * Connect github with travis CI
 * Enable CI for particular repo
 * Set environment variables ```$DOCKER_PASSWORD``` and ``` $DOCKER_USERNAME ```
 for automatic deployment to *dockerhub*
 
# Create sandbox with services
## Default commands
* ```docker ps -a``` 
* ```docker rm cont-name```
## Start/create POSTGRES
* ``` docker run -d --name postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=nejc -p 5432:5432 postgres:10.5 ```
* ```docker start postgres```
* * ``` docker run -d --name postgres --network=test-nejc -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=nejc -p 5432:5432 postgres:10.5 ```

## Start/create ETCD
* ```docker run -d --name etcd -p 2379:2379  --volume=/tmp/etcd-data:/etcd-data quay.io/coreos/etcd:latest  /usr/local/bin/etcd --name my-etcd-1 --data-dir /etcd-data --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://0.0.0.0:2379 --listen-peer-urls http://0.0.0.0:2380 --initial-advertise-peer-urls http://0.0.0.0:2380 --initial-cluster my-etcd-1=http://0.0.0.0:2380    --initial-cluster-token my-etcd-token --initial-cluster-state new  --auto-compaction-retention 1 -cors="*"```
* ```docker start etcd```
* * ```docker run -d --name etcd --network=test-nejc -p 2379:2379  --volume=/tmp/etcd-data:/etcd-data quay.io/coreos/etcd:latest  /usr/local/bin/etcd --name my-etcd-1 --data-dir /etcd-data --listen-client-urls http://0.0.0.0:2379 --advertise-client-urls http://0.0.0.0:2379 --listen-peer-urls http://0.0.0.0:2380 --initial-advertise-peer-urls http://0.0.0.0:2380 --initial-cluster my-etcd-1=http://0.0.0.0:2380    --initial-cluster-token my-etcd-token --initial-cluster-state new  --auto-compaction-retention 1 -cors="*"```


## Create network
* ```docker network ls```
* ```docker network create test-nejc``` Create network with name ```test-nejc```
* ```docker network inspect test-nejc``` Return information about network and which containers are connected in

## Start container in network
* ```docker run -itd --network=test-nejc image-name```


# Pagination, filtering, sortering
## Usages
* http://localhost:8081/v1/albums?where=album_id:EQ:1
* http://localhost:8081/v1/albums/categories?order=category_id DESC
* http://localhost:8081/v1/albums/categories?filter=category_id:GT:3
* http://localhost:8081/v1/albums/categories?fields=category_id
* http://localhost:8081/v1/albums/categories?limit=4&offset=2