package org.lucylang.ljvm.node;

public class Package extends Node {
    private String packageName;

    public Package(String packageName) {
        assert packageName != null;
        this.packageName = packageName;
    }

    public String getPackageName() {
        return this.packageName;
    }
}
