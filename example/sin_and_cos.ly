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

func main() {
    print("input n: ")
    n = input()
    while n != "end" {
        n = number(n)
        print("sin(" + string(n) + ") = " + string(sin(n)) + "\n")
        print("input n: ")
        n = input()
    }
}