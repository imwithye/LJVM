import "std"

func convert(temp) {
    return (temp * 9 / 5) + 32
}

func main() {
    std::print("Please give in a lower limit, limit >= 0: ")
    lower = std::number(std::input())
    if lower < 0 {
        print("lower limit must be greater than 0\n")
        return
    }
    std::print("Please give in a higher limit, 10 > limit <= 50000: ")
    higher = std::number(std::input())
    if higher < lower or higher < 10 or higher > 50000 {
        std::print("higher limit must be greater than lower and in range of (10, 50000]\n")
        return
    }
    while lower <= higher {
        std::print("Celsius is " + std::string(lower) + " and Fahrenheit is " + std::string(convert(lower)) + "\n")
        lower = lower + 1
    }
}