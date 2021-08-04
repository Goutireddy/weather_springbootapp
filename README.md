# Weather springboot app 
Contains code for getting the weather report based on provided zipcode/city

## Pre-requisites for building and running the java application:

* [JDK] 1.8
* [Maven] 3

### You can use the Spring Boot Maven plugin like so:
`mvn spring-boot:run`

## Infra Code to deploy 

* For testing we deploying to kind cluster for running local kubernetes cluster using container nodes
 Kind is a tool for running local kubernetes cluster using docker container nodes

### Follow below commands to build and deploy:

Step-1: Create docker image with below command
```bash
$ cd weather_springbootapp/
$ docker build -t weather:v0.1 .
```
Step-2: Install kind & create Kind cluster
```bash
$ brew install kind
$ kind create cluster
```
Step-4: Create the Image and load the image to kind 
```bash
$ kind load docker-image weather:v0.1
```
Step-5: Deploy
```bash
$ cd Kubernetes_deployment/
$ kubectl apply -f .
```
Above will create Deployment,service,Horizonalpodscaler
To handle fault tolerance, I have created 3 Replicas
For scalability, we used Horizonalpodscaler
We can change the service type to loadBalancer to expose service to outside cluster or Ingress

Step6: Since its local deployment, we have created ClusterIP service to access it run below command
```bash
kubectl port-forward service/weather-service 8080:80
```

##Test Endpoint

*Open browser and hit endpoint http://localhost:8080/weather?searchText=philadelphia

searchText is a query parameter pass zipcode or city

*You can also test using postman UI:

Below is the json response from postman
```bash
{
    "location": {
        "name": "Philadelphia",
        "region": "Pennsylvania",
        "country": "United States of America",
        "lat": 39.95,
        "lon": -75.16,
        "tz_id": "America/New_York",
        "localtime_epoch": 1628103361,
        "localtime": "2021-08-04 14:56"
    },
    "current": {
        "last_updated_epoch": 1628102700,
        "last_updated": "2021-08-04 14:45",
        "temp_c": 25,
        "temp_f": 77,
        "is_day": 1,
        "condition": {
            "text": "Overcast",
            "icon": "//cdn.weatherapi.com/weather/64x64/day/122.png",
            "code": 1009
        },
        "wind_mph": 11.9,
        "wind_kph": 19.1,
        "wind_degree": 90,
        "wind_dir": "E",
        "pressure_mb": 1020,
        "pressure_in": 30.12,
        "precip_mm": 0,
        "precip_in": 0,
        "humidity": 54,
        "cloud": 100,
        "feelslike_c": 25.7,
        "feelslike_f": 78.2,
        "vis_km": 16,
        "vis_miles": 9,
        "uv": 6,
        "gust_mph": 6.7,
        "gust_kph": 10.8
    }
}```
