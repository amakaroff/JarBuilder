package com.makarov.builder.action;

public interface Action<T> {

    boolean isActionComplete(T data);
}
