import "std"

func main() {
    var n = 1, m = 1
    while n <= 9 {
        m = 1
        while m <= n {
            print(string(n) + " * " + string(m) + " = " + string(n*m) + "; ")
            m = m + 1
        }
        print("\n")
        n = n + 1
    }
}