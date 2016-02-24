package org.lucylang.ljvm.node;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StringLiteral extends Node implements IValue {
    private final Logger logger = LogManager.getLogger(StringLiteral.class.getName());
    private String value;

    public StringLiteral(String value) {
        logger.trace("New StringLiteral: <\"" + value + "\">");
        this.value = value;
    }

    public String getValue() {
        logger.trace("Get StringLiteral: <\"" + value + "\">");
        return this.value;
    }
}
