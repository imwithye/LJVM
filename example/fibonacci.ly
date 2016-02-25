func fibonacci(n) {
    if n < 2 {
        return n
    } else {
        return fibonacci(n-1) + fibonacci(n-2)
    }
}

func main() {
    appender = "The fibonacci number is "
    print(appender + string(fibonacci(10)))
}
