package ru.aston.practical.customArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;

public class ArrayList<E> implements List<E> {
    Object[] elementData;
    private int size;

    public ArrayList() {
        this.elementData = new Object[10];
        this.size = 0;
    }

    @Override
    public void add(int index, E element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
        ensureCapacity();

        System.arraycopy(elementData, index, elementData, index + 1, size - index);
        elementData[index] = element;
        size++;
    }

    @Override
    public void addAll(Collection<? extends E> c) {
        for (E element : c) {
            add(size, element);
        }
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public E get(int index) {
        validIndex(index, size);
        return (E) elementData[index];
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    @Override
    public E remove(int index) {
        validIndex(index, size);

        E removedElement = (E) elementData[index];

        System.arraycopy(elementData, index + 1, elementData, index, size - index - 1);
        elementData[size - 1] = null;
        size--;

        return removedElement;
    }

    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < size; i++) {
            if (o.equals(elementData[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public void sort(Comparator<? super E> c) {
        if (size > 1) {
            E[] tempArray = Arrays.copyOf((E[]) elementData, size);
            quickSort(tempArray, 0, size - 1, c);
            elementData = Arrays.copyOf(tempArray, size);
        }
    }

    private void quickSort(E[] array, int low, int high, Comparator<? super E> c) {
        if (low < high) {
            int index = partition(array, low, high, c);
            quickSort(array, low, index - 1, c);
            quickSort(array, index + 1, high, c);
        }
    }

    private int partition(E[] array, int low, int high, Comparator<? super E> c) {
        E e = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (c.compare(array[j], e) <= 0) {
                i++;
                E temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        E temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    @Override
    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (elementData.length == size) {
            int newCapacity = elementData.length * 2;
            elementData = Arrays.copyOf(elementData, newCapacity);
        }
    }

    private void validIndex(int index, int size) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + " Size: " + size);
        }
    }
}
