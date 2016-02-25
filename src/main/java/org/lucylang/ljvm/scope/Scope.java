package org.lucylang.ljvm.scope;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Scope<K, V> implements Serializable {
    private HashMap<K, V> scope;
    protected Scope<K, V> parent;
    protected NameGenerator nameGenerator = new NameGenerator(Scope.class);
    protected String name;

    public Scope() {
        this.init(null, nameGenerator.getAnonymousName());
    }

    public Scope(String name) {
        this.init(null, nameGenerator.getName(name));
    }

    public Scope(Scope<K, V> parent) {
        this.init(parent, nameGenerator.getAnonymousName());
    }

    public Scope(Scope<K, V> parent, String name) {
        this.init(parent, nameGenerator.getName(name));
    }

    protected void init(Scope<K, V> parent, String name) {
        assert name != null;
        this.scope = new HashMap<K, V>();
        this.parent = parent;
        this.name = name;
    }

    public void setParent(Scope<K, V> parent) {
        this.parent = parent;
    }

    public Scope<K, V> getParent() {
        return this.parent;
    }

    public Scope set(K key, V value) {
        assert key != null;
        this.scope.put(key, value);
        return this;
    }

    public Scope safeSet(K key, V value) throws OverdefinedException {
        assert key != null;
        if (this.isDefined(key)) {
            throw new OverdefinedException(key, this);
        }
        return this.set(key, value);
    }

    public boolean isDefined(K key) {
        assert key != null;
        if (this.scope.get(key) != null) {
            return true;
        }
        return false;
    }

    public V get(K key) {
        assert key != null;
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

    public V safeGet(K key) throws UndefinedException {
        assert key != null;
        V value = this.get(key);
        if (value == null) {
            throw new UndefinedException(key, this);
        }
        return value;
    }

    public Set<K> keySet() {
        return this.scope.keySet();
    }

    public Set<K> allKeySet() {
        Set<K> keySet = new HashSet<K>();
        Scope<K, V> scope = this;
        while (scope != null) {
            keySet.addAll(scope.keySet());
            scope = scope.getParent();
        }
        return keySet();
    }

    @Override
    public String toString() {
        return this.name;
    }
}
