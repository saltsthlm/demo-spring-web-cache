# Spring web app with caching enabled

### Ehcache used as the caching provider

This is a demo showing how to set up caching in a Spring Boot web app.

Ehcache is used as the caching provider, so there's some extra set up to enable and customize it.

`src/main/java/resources/ehcache.xml` is the config file for Ehcache, and it's set up to hold a bucket of caches under
the alias `"test1"`, with integer keys and long values, expiring after 10 seconds, holding 2 elements at most in the JVM
heap, and 1 MB of extra cache on the off-heap.

There is also a cache listener that prints out cache events, at
`src/main/java/org/example/demospringwebcache/CacheEventLogger.java`.
