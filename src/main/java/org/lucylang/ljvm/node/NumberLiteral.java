package org.lucylang.ljvm.node;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class NumberLiteral extends Node implements IValue {
    private final Logger logger = LogManager.getLogger(NumberLiteral.class.getName());
    private Double value;

    public NumberLiteral(Integer value) {
        logger.trace("New NumberLiteral: <" + value + ">");
        this.value = new Double(value);
    }

    public NumberLiteral(Double value) {
        logger.trace("New NumberLiteral: <" + value + ">");
        this.value = value;
    }

    public Double getValue() {
        logger.trace("Get NumberLiteral: <" + this.value + ">");
        return this.value;
    }
}
