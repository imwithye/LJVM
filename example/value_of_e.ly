import "std"

func factorial(n) {
    result = 1
    while n > 0 {
        result = result * n
        n = n - 1
    }
    return result
}

func main() {
    result = 0
    n = 50
    while n > 0 {
        result = result + 1 / factorial(n)
        n = n - 1
    }
    std::print("E is " + std::string(result))
}