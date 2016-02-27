package "main"

import "std"

func main() {
    std::print("sin = " + std::string(trigonometric::sin(1)) + "\n")
    std::print("cos = " + std::string(trigonometric::cos(1)) + "\n")
}