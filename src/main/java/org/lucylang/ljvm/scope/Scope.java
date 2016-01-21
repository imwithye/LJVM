package org.lucylang.ljvm.scope;

import java.util.HashMap;

public class Scope<K, V> {
    private HashMap<K, V> scope;
    protected Scope<K, V> parent;

    public Scope() {
        this(null);
    }

    public Scope(Scope<K, V> parent) {
        this.scope = new HashMap<K, V>();
        this.parent = parent;
    }

    public void setParent(Scope<K, V> parent) {
        this.parent = parent;
    }

    public Scope<K, V> getParent() {
        return this.parent;
    }

    public Scope put(K key, V value) throws OverdefinedException {
        if (this.isDefined(key)) {
            throw new OverdefinedException();
        }
        this.scope.put(key, value);
        return this;
    }

    public boolean isDefined(K key) {
        if (this.scope.get(key) != null) {
            return true;
        }
        return false;
    }

    public V get(K key) {
        V value = this.scope.get(key);
        if (value == null) {
            if (this.parent == null) {
                return null;
            } else {
                return this.parent.get(key);
            }
        } else {
            return value;
        }
    }
}
