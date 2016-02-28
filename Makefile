.PHONY: build test clean package

VERSION = 0.2.0

build:
	mvn package -DskipTests

test:
	mvn package

clean:
	rm -rf lucy-${VERSION}
	mvn clean

package:
	@echo "Packaging Lucy Verison ${VERSION}"
	@echo
	make clean test
	mkdir -p lucy-${VERSION} lucy-${VERSION}/bin lucy-${VERSION}/lib
	cp tools/lucy lucy-${VERSION}/bin/lucy
	cp tools/lucy.cmd lucy-${VERSION}/bin/lucy.cmd
	cp target/lucy-${VERSION}.jar lucy-${VERSION}/lib/lucy.jar
	cp README.md lucy-${VERSION}/README.md
	cp CHANGELOG.md lucy-${VERSION}/CHANGELOG.md
