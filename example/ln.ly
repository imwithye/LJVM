import "std"

func power(n, exp) {
	result = 1
	while exp > 0 {
		result = result * n
		exp = exp - 1
	}
	return result
}


func ln(x) {
	var upper = 50, i = 0, sum = 0
	while i < upper {
		sum = sum + 1 / (2*i+1) * power((x-1)/(x+1), 2*i+1)
		i = i + 1
	}
	return 2 * sum
}

func main() {
	print("input n: ")
	n = number(input())
	print("ln(n) = " + string(ln(n)))
}
