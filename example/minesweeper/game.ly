package "game"

import "util"
import "std"
import "message"

func valid_index(i, j, size) {
	index = i * size + j
	if index >= 0 && index < size * size {
		return index
	} else {
		return -1
	}
}

func init_map(map, size) {
	var i = 0, j = 0, total = 0
	while total < size {
		i = 0
		j = 0
		while i < size {
			while j < size {
				var index = i * size + j
				if util::random(size) && total < size && map[index] != 1 {
					map[index] = -1
					total = total + 1
				}
				j = j + 1
			}
			i = i + 1
			j = 0
		}
	}
	i = 0
	j = 0
	while i < size {
		while j < size {
			count = 0
			if valid_index(i-1, j-1, size) != -1 {
				if map[valid_index(i-1, j-1, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i-1, j, size) != -1 {
				if map[valid_index(i-1, j, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i-1, j+1, size) != -1 {
				if map[valid_index(i-1, j+1, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i, j-1, size) != -1 {
				if map[valid_index(i, j-1, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i, j+1, size) != -1 {
				if map[valid_index(i, j+1, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i+1, j-1, size) != -1 {
				if map[valid_index(i+1, j-1, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i+1, j, size) != -1 {
				if map[valid_index(i+1, j, size)] == -1 {
					count = count + 1
				}
			}
			if valid_index(i+1, j+1, size) != -1 {
				if map[valid_index(i+1, j+1, size)] == -1 {
					count = count + 1
				}
			}
			if map[i*size+j] != -1 {
				map[i*size+j] = count
			}
			j = j + 1
		}
		i = i + 1
		j = 0
	}
	return map
}

func print_map(map, size) {
	j = -1
	while j < size {
		if j >= 0 {
			std::print(util::formatter(2, j))
		} else {
			std::print(util::formatter(2, ""))
		}
		std::print(" ")
		j = j + 1
	}
	std::print("\n")
	i = 0
	j = -1
	while i < size {
		while j < size {
			if j == -1 {
				std::print(util::formatter(2, i))
			} else {
				std::print(util::formatter(2, map[i * size + j]))
			}
			std::print(" ")
			j = j + 1
		}
		std::print("\n")
		i = i + 1
		j = -1
	}
}

func print_and_next(cover_map, size, found) {
	print_map(cover_map, size)
	std::print("Total mines: " + std::string(size) + " You flaged " + std::string(found) + "\n")
	row = -1
	while row < 0 || row >= size {
		std::print("Input position(row): ")
		row = std::number(std::input())
	}
	col = -1
	while col < 0 || col >= size {
		std::print("Input position(col): ")
		col = std::number(std::input())
	}
	choice = ""
	while !(choice == "O" || choice == "F" || choice == "U" || choice == "C") {
		std::print("Input choice([O]pen, [F]lag, [U]nflag, [C]ancel: ")
		choice = std::input()
	}
	return [row, col, choice]
}

func open(map, cover_map, size, row, col) {
	index = row * size + col
	if map[index] == -1 {
		return [true, cover_map]
	}
	if map[index] == 0 {
		cover_map[index] = " "
		left = (row - 1) * size + col
		if left >= 0 && left < size*size {
			if map[left] == 0 && cover_map[left] != " " {
				result = open(map, cover_map, size, row - 1, col)
				cover_map = result[1]
			}
			if map[left] != -1 && cover_map[left] != " " {
				cover_map[left] = map[left]
			}
		}
		right = (row + 1) * size + col
		if right >= 0 && right < size*size {
			if map[right] == 0 && cover_map[right] != " " {
				result = open(map, cover_map, size, row + 1, col)
				cover_map = result[1]
			}
			if map[right] != -1 && cover_map[right] != " " {
				cover_map[right] = map[right]
			}
		}
		up = row * size + (col - 1)
		if up >= 0 && up < size*size {
			if map[up] == 0 && cover_map[up] != " " {
				result = open(map, cover_map, size, row, col - 1)
				cover_map = result[1]
			}
			if map[up] != -1 && cover_map[up] != " " {
				cover_map[up] = map[up]
			}
		}
		down = row * size + (col + 1)
		if down >= 0 && down < size*size {
			if map[down] == 0 && cover_map[down] != " " {
				result = open(map, cover_map, size, row, col + 1)
				cover_map = result[1]
			}
			if map[down] != -1 && cover_map[down] != " " {
				cover_map[down] = map[down]
			}
		}
		return [false, cover_map]
	} else {
		cover_map[index] = map[index]
		return [false, cover_map]
	}
}

func flag(cover_map, size, row, col) {
	index = row * size + col
	if cover_map[index] == "^" {
		cover_map[index] = "F"
		return [true, cover_map]
	} else {
		return [false, cover_map]
	}
}

func unflag(cover_map, size, row, col) {
	index = row * size + col
	if cover_map[index] == "F" {
		cover_map[index] = "^"
		return [true, cover_map]
	} else {
		return [false, cover_map]
	}
}

func check_game(map, cover_map, size) {
	total = size
	correct = 0
	incorrect = 0
	i = 0
	j = 0
	while i < size {
		while j < size {
			index = i * size + j
			if map[index] == -1 && cover_map[index] == "F" {
				correct = correct + 1
			}
			if map[index] == -1 && cover_map[index] != "F" {
				cover_map[index] = "*"
			}
			if map[index] != -1 && cover_map[index] == "F" {
				cover_map[index] = "W"
				incorrect = incorrect + 1
			}
			j = j + 1
		}
		j = 0
		i = i + 1
	}
	if correct < total {
		return [false, cover_map]
	} else {
		return [true, cover_map]
	}
}

func start(size) {
	map = init_map(util::init_array(size * size, 0), size)
	cover_map = util::init_array(size * size, "^")
	total = size
	found = 0
	game_end = false
	while found < size && !game_end {
		next_move = print_and_next(cover_map, size, found)
		if next_move[2] == "O" {
			result = open(map, cover_map, size, next_move[0], next_move[1])
			game_end = result[0]
			cover_map = result[1]
		}
		if next_move[2] == "F" {
			result = flag(cover_map, size, next_move[0], next_move[1])
			if result[0] {
				found = found + 1
			}
			cover_map = result[1]
		}
		if next_move[2] == "U" {
			result = unflag(cover_map, size, next_move[0], next_move[1])
			if result[0] {
				found = found - 1
			}
			cover_map = result[1]
		}
		message::clear()
	}
	result = check_game(map, cover_map, size)
	print_map(result[1], size)
	if result[0] {
		std::print("You win!\n")
		std::print("Thanks for trying! http://lucy-lang.org\n")
	} else {
		std::print("You lose!\n")
		std::print("Thanks for trying! http://lucy-lang.org\n")
	}
}
