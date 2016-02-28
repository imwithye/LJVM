minesweeper: main.lyo message.lyo game.lyo util.lyo
	lucy -c message.lyo main.lyo game.lyo util.lyo -o minesweeper

main.lyo: main.ly
	lucy -c main.ly -o main.lyo

message.lyo: message.ly
	lucy -c message.ly -o message.lyo

game.lyo:	game.ly
	lucy -c game.ly -o game.lyo

util.lyo: util.ly
	lucy -c util.ly -o util.lyo

clean:
	rm -rf *.lyo
	rm minesweeper
