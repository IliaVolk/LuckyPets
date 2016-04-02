package com.luckypets.dao.impl;

import java.util.List;

public class Util {
    public static <E> List<E> sublist(List<E> list, int beginIndex, int count) {
        if (list.size() > beginIndex + count) {
            return list.subList(beginIndex, list.size());
        }
        return list.subList(beginIndex, count);
    }
}
