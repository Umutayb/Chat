### To run: 
```shell
mvn clean -q install exec:java -DskipTests
``` 

Make sure your ChatGPT token in your `src/test/resources/test.properties` file:
```
gpt-token=your-token
model-name=gpt-3.5-turbo
log-headers=false
```
