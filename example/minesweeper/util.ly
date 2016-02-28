package "util"

import "std"
import "math"

func formatter(length, value) {
	var value_len = std::len(std::string(value))
	if length > value_len {
		var diff = length - value_len
		while diff > 0 {
			value = " " + std::string(value)
			diff = diff - 1
		}
	}
	return std::string(value)
}

func random(size) {
	return math::random() < (1 / size)
}

func append(array, value) {
	return array + [value]
}

func init_array(size, value) {
	var i = 0, array = []
	while i < size {
		array = append(array, value)
		i = i + 1
	}
	return array
}
