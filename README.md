# Pdf-Office-Zuul-Proxy
Zuul proxy server for pdf reader application .
kubernetes files can be found in k8s folder .


Prior knowledge of following are needed to run the application .

1 . kubernetes 

2 . GCP or aws 

3 . continou integration 


The application has to be configured along with two other repository :-

https://github.com/Shubhamr837/Pdf-to-Word-Server

https://github.com/Shubhamr837/Pdf-Office-Zuul-Proxy

The kubernetes deployment and service files are in the k8s folder the image url of container has to be configured along with code build or any other build service .
The application uses code build for continous integration which has to be configured .

The load balancer service exposes the service with an external ip.

The ip is the server url which need to be used for apis and in the pdf office android application .
