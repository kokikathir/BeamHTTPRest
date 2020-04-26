# BeamHTTPRest
This repository contains an example to write data to REST endpoint as the last step in the pipeline

## Apache Beam I/O
Based on the documentation of Beam, RestIO is in progress. (https://beam.apache.org/documentation/io/built-in/)

## Scenario
The word count example from Beam is taken as base. (https://beam.apache.org/get-started/wordcount-example/)
Instead of writing the output to file, it will be posted to a Rest Endpoint.

### REST Endpoint
This sample uses https://reqbin.com/ as the sample endpoint to post the data.

```shell script
curl -X POST -d '{"source":"httpevent","time":"1587897374","event":"Welcome: 2"}' https://reqbin.com/sample/post/json
```
### Running the example
This works with Java 8. 
```shell script
mvn compile exec:java -Dexec.mainClass=org.apache.beam.examples.WordCount
```

With Java 13, We will face the below error. Make sure to run in Java 8.
```less
Exception in thread "main" org.apache.beam.vendor.guava.v26_0_jre.com.google.common.util.concurrent.UncheckedExecutionException: java.lang.UnsupportedOperationException: Cannot define class using reflection: Cannot define nest member class java.lang.reflect.AccessibleObject$Cache + within different package then class org.apache.beam.vendor.bytebuddy.v1_9_3.net.bytebuddy.mirror.AccessibleObject
	at org.apache.beam.vendor.guava.v26_0_jre.com.google.common.cache.LocalCache$Segment.get(LocalCache.java:2050)
	at org.apache.beam.vendor.guava.v26_0_jre.com.google.common.cache.LocalCache.get(LocalCache.java:3952)
	at org.apache.beam.vendor.guava.v26_0_jre.com.google.common.cache.LocalCache.getOrLoad(LocalCache.java:3974)
	at org.apache.beam.vendor.guava.v26_0_jre.com.google.common.cache.LocalCache$LocalLoadingCache.get(LocalCache.java:4958)
```


