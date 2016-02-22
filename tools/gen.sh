#!/usr/bin/env bash

DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
java -jar "$DIR/jflex.jar" "--nobak" "$DIR/../src/main/java/org/lucylang/ljvm/parser/lexer.flex"
java -jar "$DIR/beaver.jar" "$DIR/../src/main/java/org/lucylang/ljvm/parser/grammar.beaver"
