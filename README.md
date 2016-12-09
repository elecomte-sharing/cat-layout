# Log with cats !

[![Travis CI Build Status](https://travis-ci.org/manuito/cat-layout.svg)](https://travis-ci.org/manuito/cat-layout)

So you like cats ? To add some meow and cats in you java app logs, here comes a very useful cat encoder for [logback](http://logback.qos.ch/) 

## What ?
With this declinaison of the default ch.qos.logback.classic.encoder.PatternLayoutEncoder, the [pattern](http://logback.qos.ch/manual/layouts.html) is still processed at log time, but in a "cat" way ... 

All the other properties from [default encoder](http://logback.qos.ch/manual/encoders.html) are still supported (immediateFlush, outputPatternAsHeader). 

## How ?
**Note** : you need to use [logback](http://logback.qos.ch/) for logging outputs on your project (logback is the default log writter for many java dev fwks, like Spring-boot)

### Add the dependency to your pom.xml like this :

    (to come)

### Then to use this encoder instead of the default layout def, in your logback.xml, specify the encoder class, as this : 

    <encoder class="fr.manuito.CatPatternLayoutEncoder">
       <pattern>%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
    </encoder>
 
Other properties are not modified. 

### Restart you project. Now the cats are talking to you.
