func convert(temp) {
    return (temp * 9 / 5) + 32
}

func main() {
    print("Please give in a lower limit, limit >= 0: ")
    lower = number(input())
    if lower < 0 {
        print("lower limit must be greater than 0\n")
        return
    }
    print("Please give in a higher limit, 10 > limit <= 50000: ")
    higher = number(input())
    if higher < lower or higher < 10 or higher > 50000 {
        print("higher limit must be greater than lower and in range of (10, 50000]\n")
        return
    }
    while lower <= higher {
        print("Celsius is " + string(lower) + " and Fahrenheit is " + string(convert(lower)) + "\n")
        lower = lower + 1
    }
}