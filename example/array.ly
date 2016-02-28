package "main"

import "std"

func modify(array) {
    array[0] = 2
    return array
}

func main() {
    array = [1]

    modify(array)
    std::print(array)

    array = modify(array)
    std::print(array)
}