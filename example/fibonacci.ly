func fibonacci(n) {
    if n < 2 {
        return n
    } else {
        return fibonacci(n-1) + fibonacci(n-2)
    }
}

func main() {
    text = "input a number: "
    print(text)
    n = number(input())
    text = "The fibonacci number of " + string(n) + " is " + string(fibonacci(10))
    print(text)
}
