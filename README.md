# Java Virtual thread test program.

Java 19のVirtual Threadのテストプログラム。

## 実行環境

- Java 19
- MySQL 8
- Gradle 7.5
 
## ビルド方法

```
gradle build
```

## 実行方法

```
java --enable-preview -jar build/libs/virtualthread-0.0.1-SNAPSHOT.jar
```

## Benchmark

```
wrk -t5 -c500 -d30s http://localhost:8080/api/users
```