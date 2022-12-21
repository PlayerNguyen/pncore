package com.github.playernguyen.pncore.utils;

public interface Container<T> {

    void load();

    void unload();

    void put(T element);

    void remove(T element);

    boolean has(T element);
}
