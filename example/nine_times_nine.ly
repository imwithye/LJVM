package "main"

import "std"

func main() {
    var n = 1, m = 1
    while n <= 9 {
        m = 1
        while m <= n {
            std::print(std::string(n) + " * " + std::string(m) + " = " + std::string(n*m) + "; ")
            m = m + 1
        }
        std::print("\n")
        n = n + 1
    }
}