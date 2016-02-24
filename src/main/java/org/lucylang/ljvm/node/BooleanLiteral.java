package org.lucylang.ljvm.node;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BooleanLiteral extends Node implements IValue {
    private final Logger logger = LogManager.getLogger(BooleanLiteral.class.getName());
    private Boolean value;

    public BooleanLiteral(Boolean value) {
        logger.trace("New BooleanLiteral: <" + value + ">");
        this.value = value;
    }

    public Boolean getValue() {
        logger.trace("Get BooleanLiteral: <" + value + ">");
        return this.value;
    }
}
