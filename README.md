# redis-java-client-demo
自研redis客户端，学习下 resp 协议

+单行字符串，以 crlf 结尾
    +ok\r\n, 就可以传输ok，但是不允许传输内容中含有\r\n，因为会影响解析

-错误信息，以 crlf 结尾
    -null point exception\r\n

:数组，以crlf结尾
    :10086\r\n

$多行字符串
    ${字节长度}\r\n{字节内容}\r\n
    $5\r\nhello\r\n，传输hello
    $0\r\n\r\n，传输控字符串
    $-1\r\n，传输 null

*数组
    *3\r\n
    $3\r\nset\r\n
    $4\r\nmsg\r\n
    $10\r\nhelloworld\r\n
    表示传 [set,msg,helloworld]


v1.0
    先把 spring boot 启动起来，支持通过 url 传入 put get 操作，访问到 redis
