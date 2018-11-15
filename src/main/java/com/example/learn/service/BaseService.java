package com.example.learn.service;

import com.example.learn.model.Greeting;

public interface BaseService<T extends Greeting> {
    String separator = " ";

    int calculateSomething(T t);

    default T modifySomething(T t) {
        return t;
    }
}
