# 計測結果

## 環境

- Ubuntu Desktop 22.04
- Memory 32
- CPU AMD Ryzen 5 3600 6-Core

## テスト１

Native Threadで処理するパターン。

url:  http://localhost:8080/api/articles
thread 5
同時接続数: 500

1回目
```
yaku@ubuntu:~$ wrk -t5 -c500 -d30s http://localhost:8080/api/articles
Running 30s test @ http://localhost:8080/api/articles
  5 threads and 500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   311.84ms  207.85ms   1.84s    72.09%
    Req/Sec   335.71    119.71   770.00     66.34%
  49339 requests in 30.08s, 3.54GB read
  Socket errors: connect 0, read 0, write 0, timeout 1
Requests/sec:   1640.14
Transfer/sec:    120.48MB
```

画像: Screenshot-normal-1.png

２回目
```
yaku@ubuntu:~$ wrk -t5 -c500 -d30s http://localhost:8080/api/articles
Running 30s test @ http://localhost:8080/api/articles
  5 threads and 500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   244.59ms  140.92ms   1.28s    72.79%
    Req/Sec   417.50    125.41     1.13k    73.34%
  61028 requests in 30.08s, 4.37GB read
Requests/sec:   2028.76
Transfer/sec:    148.72MB
```

画像: Screenshot-normal-2.png

## テスト２

Virtual Threadで処理するパターン。

### 一回目
```commandline
yaku@ubuntu:~$ wrk -t5 -c500 -d30s http://localhost:8080/api/articles
Running 30s test @ http://localhost:8080/api/articles
  5 threads and 500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   305.18ms  203.35ms   1.99s    79.87%
    Req/Sec   344.68    144.35   808.00     68.14%
  50687 requests in 30.07s, 3.63GB read
  Socket errors: connect 0, read 0, write 0, timeout 79
Requests/sec:   1685.55
Transfer/sec:    123.55MB
```

画像: Screenshot-vt-1.png

### 二回目

```commandline
yaku@ubuntu:~$ wrk -t5 -c500 -d30s http://localhost:8080/api/articles
Running 30s test @ http://localhost:8080/api/articles
  5 threads and 500 connections
  Thread Stats   Avg      Stdev     Max   +/- Stdev
    Latency   261.18ms   79.57ms 890.32ms   91.12%
    Req/Sec   378.99    116.71   828.00     69.27%
  56642 requests in 30.05s, 4.05GB read
Requests/sec:   1885.12
Transfer/sec:    138.18MB
```

画像: Screenshot-vt-2.png
