import "std"
import "math"

func main() {
    var result = 0
    print("input n: ")
    n = std::number(std::input())
    floor = math::floor(n)
    print("The floor of " + std::string(n) + " is " + std::string(floor) + "\n")
    ceil = math::ceil(n)
    print("The ceil of " + std::string(n) + " is " + std::string(ceil) + "\n")
}