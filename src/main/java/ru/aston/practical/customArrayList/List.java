package ru.aston.practical.customArrayList;

import java.util.Collection;
import java.util.Comparator;

public interface List<E> {
    void add(int index, E element);

    void addAll(Collection<? extends E> c);

    void clear();

    E get(int index);

    boolean isEmpty();

    E remove(int index);

    boolean remove(Object o);

    void sort(Comparator<? super E> c);

    int size();
}
