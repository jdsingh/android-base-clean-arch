Android WikiSearch [![Build Status](https://travis-ci.org/jdsingh/android-wikisearch.svg?branch=master)](https://travis-ci.org/jdsingh/android-wikisearch)
[![MIT License](http://img.shields.io/badge/license-MIT-blue.svg?style=flat)](https://github.com/jdsingh/android-wikisearch/blob/master/LICENSE)
=====================
*Android App for Wikipedia Search*                   

### This app contains the following

1. Shows the List of results fetched from Wikipedia API.
2. Clean architecture.
3. Multi-module structure.
4. Dagger 2 dependency injection.
5. Room database for caching search results.
6. Demonstrate the use of RxJava2, Retrofit, Glide.

### To build release APK

To build and install the apk

`./gradlew installDebug`

To run unit tests:

`./gradlew test`

#### TODO

- Ability to Clear cache.
- Check cache validity before using.
