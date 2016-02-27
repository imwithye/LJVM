package "main"

import "std"

func fibonacci(n) {
    if n < 2 {
        return n
    } else {
        return fibonacci(n-1) + fibonacci(n-2)
    }
}

func main() {
    text = "input a number: "
    std::print(text)
    n = std::number(std::input())
    text = "The fibonacci number of " + std::string(n) + " is " + std::string(fibonacci(10))
    std::print(text)
}
