package org.lucylang.ljvm.parser;

import beaver.Symbol;

import java.util.*;

public class SymbolList extends beaver.Symbol implements List<beaver.Symbol> {
    private ArrayList<beaver.Symbol> list = new ArrayList<Symbol>();

    public int size() {
        return list.size();
    }

    public boolean isEmpty() {
        return list.isEmpty();
    }

    public boolean contains(Object o) {
        return list.contains(o);
    }

    public Iterator<Symbol> iterator() {
        return list.iterator();
    }

    public Object[] toArray() {
        return list.toArray();
    }

    public <T> T[] toArray(T[] a) {
        return list.toArray(a);
    }

    public boolean add(Symbol symbol) {
        return list.add(symbol);
    }

    public boolean remove(Object o) {
        return list.remove(o);
    }

    public boolean containsAll(Collection<?> c) {
        return list.containsAll(c);
    }

    public boolean addAll(Collection<? extends Symbol> c) {
        return list.addAll(c);
    }

    public boolean addAll(int index, Collection<? extends Symbol> c) {
        return list.addAll(index, c);
    }

    public boolean removeAll(Collection<?> c) {
        return list.removeAll(c);
    }

    public boolean retainAll(Collection<?> c) {
        return false;
    }

    public void clear() {
        list.clear();
    }

    public Symbol get(int index) {
        return list.get(index);
    }

    public Symbol set(int index, Symbol element) {
        return list.set(index, element);
    }

    public void add(int index, Symbol element) {
        list.add(index, element);
    }

    public Symbol remove(int index) {
        return list.remove(index);
    }

    public int indexOf(Object o) {
        return list.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        return list.lastIndexOf(o);
    }

    public ListIterator<Symbol> listIterator() {
        return list.listIterator();
    }

    public ListIterator<Symbol> listIterator(int index) {
        return list.listIterator(index);
    }

    public List<Symbol> subList(int fromIndex, int toIndex) {
        return list.subList(fromIndex, toIndex);
    }
}
