package "main"

import "message"
import "game"

func main() {
	choice = message::start()
	if choice == 1 {
		game::start(10)
	} else {
		game::start(25)
	}
}
