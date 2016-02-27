package "trigonometric"

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