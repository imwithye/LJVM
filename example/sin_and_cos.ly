package "main"

import "std"

func power(n, exp) {
    result = 1
    while exp > 0 {
        result = result * n
        exp = exp - 1
    }
    return result
}

func factorial(n) {
    if n <= 1 {
        return 1
    } else {
        return n * factorial(n-1)
    }
}

func sin(x) {
    var upper = 50, i = 0, sum = 0, sign = true
    while i < upper {
        if sign {
            sum = sum + power(x, 1 + 2 * i) / factorial(1 + 2 * i)
        } else {
            sum = sum - power(x, 1 + 2 * i) / factorial(1 + 2 * i)
        }
        sign = !sign
        i = i + 1
    }
    return sum
}

func cos(x) {
    var upper = 50, i = 0, sum = 0, sign = true
    while i < upper {
        if sign {
            sum = sum + power(x, 2 * i) / factorial(2 * i)
        } else {
            sum = sum - power(x, 2 * i) / factorial(2 * i)
        }
        sign = !sign
        i = i + 1
    }
    return sum
}

func main() {
    std::print("input n: ")
    n = std::input()
    while n != "end" {
        n = std::number(n)
        std::print("sin(" + std::string(n) + ") = " + std::string(sin(n)) + "\n")
        std::print("cos(" + std::string(n) + ") = " + std::string(cos(n)) + "\n")
        std::print("input n: ")
        n = std::input()
    }
}