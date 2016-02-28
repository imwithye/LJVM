package "message"

import "std"

func clear() {
	var i = 0
	while i < 100 {
		std::print("\n")
		i = i + 1
	}
}

func start() {
	std::print("********************************************\n")
	std::print("*                                          *\n")
	std::print("*           Minesweeper Game               *\n")
	std::print("*             Author: Ciel                 *\n")
	std::print("*          Language: Lucy 0.2.1            *\n")
	std::print("*                                          *\n")
	std::print("********************************************\n")
	std::print("1. Easy\n")
	std::print("2. Difficult\n")
	std::print("Input choice: ")
	choice = std::number(std::input())
	clear()
	return choice
}
