For building and running the application you need:

JDK 1.8
Maven 3

You can use the Spring Boot Maven plugin like so:
mvn spring-boot:run

** For testing we deploying to kind cluster ( Kind is a tool for running local kubernetes cluster using docker container nodes)

1. Create docker image with below command
   * cd weather_springbootapp/
   * docker build -t weather:v0.1 .
2. Install kind below command
   * brew install kind
3. create kind container use command
   * kind create cluster
4. Create the Image and load the image to kind
   * kind load docker-image weather:v0.1
5. Change directory to Kubernetes_deployment
   cd Kubernetes_deployment
6. Deploy resource into Kind cluster
   * kubectl apply -f .

Above will create Deployment,service,Horizonalpodscaler

We can change the service type to loadbalancer to expose service to outside cluster or
Ingress we can use

since its local deployment we have create ClusterIP service to access it run below command

kubectl port-forward service/weather-service 8080:80

Open Browser and run pwd
below command to verify
http://localhost:8080/weather?searchText=philadelphia

searchText is a query parameter pass zipcode or city

You can also test using postman UI 

Below is the json response from postman
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
}


