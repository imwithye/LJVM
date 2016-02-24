package org.lucylang.ljvm.node;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class NoneLiteral extends Node implements IValue {
    private final Logger logger = LogManager.getLogger(NoneLiteral.class.getName());

    public NoneLiteral() {
        logger.trace("New NoneLiteral");
    }
}
