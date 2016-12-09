# cat-layout
So you like cats ? Here comes a very useful cat encoder for logback (http://logback.qos.ch/) ! 

## What ?
With this declinaison of the default ch.qos.logback.classic.encoder.PatternLayoutEncoder, the pattern is still processed at log time, but in a "cat" way ... 

All the other properties from default encoder are still supported (immediateFlush, outputPatternAsHeader). 
See details on default logback encoder here : http://logback.qos.ch/manual/encoders.html 

## How ?
**Note** : you need to use logback on your project.

### Add the dependency to your pom.xml like this :

    (to come)

### Then to use this encoder instead of the default layout def, in your logback.xml, specify the encoder class, as this : 

    <encoder class="fr.manuito.CatPatternLayoutEncoder">
       <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
 
Other properties are not modified. 

### Restart you project. Now the cats are talking to you.
