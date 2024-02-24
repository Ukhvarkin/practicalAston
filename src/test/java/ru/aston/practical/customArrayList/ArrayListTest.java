package ru.aston.practical.customArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {

    private ArrayList<Integer> arrayList;

    @BeforeEach
    void beforeAll() {
        arrayList = new ArrayList<>();
    }

    @AfterEach
    void afterAll() {
        arrayList.clear();
    }

    @Test
    @DisplayName("Тест добавления элемента в список")
    void add_Test() {
        arrayList.add(0, 1);
        arrayList.add(1, 2);
        arrayList.add(2, 3);

        assertEquals(3, arrayList.size());

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
    }

    @Test
    @DisplayName("Тест добавления элемента с неверным индексом")
    void addWithInvalidIndex_Test() {
        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayList.add(1, 1)
        );
    }

    @Test
    @DisplayName("Тест добавления всех элементов в список")
    void addAll_Test() {
        List<Integer> testList = List.of(1, 2, 3, 4, 5);
        arrayList.addAll(testList);

        assertEquals(5, arrayList.size());

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(3, arrayList.get(2));
        assertEquals(4, arrayList.get(3));
        assertEquals(5, arrayList.get(4));
    }

    @Test
    @DisplayName("Тест проверки на пустой список")
    void isEmpty_Test() {
        assertTrue(arrayList.isEmpty());

        arrayList.add(0, 1);
        assertFalse(arrayList.isEmpty());
    }

    @Test
    @DisplayName("Тест удаления по существующему индексу")
    void removeAtIndex_Test() {
        arrayList.add(0, 1);
        arrayList.remove(0);
        assertTrue(arrayList.isEmpty());
    }

    @Test
    @DisplayName("Тест удаления по несущестующему индексу")
    void removeAtInvalidIndex_Test() {
        arrayList.add(0, 1);
        assertThrows(IndexOutOfBoundsException.class, () ->
                arrayList.remove(1)
        );
    }

    @Test
    @DisplayName("Тест удаления по существующему элементу")
    void removeObject_Test() {
        arrayList.add(0, 1);
        arrayList.remove(Integer.valueOf(1));
        assertTrue(arrayList.isEmpty());
    }

    @Test
    @DisplayName("Тест удаления по несущестующему элементу")
    void removeInvalidObject_Test() {
        arrayList.add(0, 1);
        assertFalse(arrayList.remove(Integer.valueOf(2)));
        assertTrue(arrayList.remove(Integer.valueOf(1)));
    }

    @Test
    @DisplayName("Тест быстрой сортировки - числа")
    void sortIntegerArrayList() {
        arrayList.add(0,5);
        arrayList.add(1,2);
        arrayList.add(2,8);
        arrayList.add(3,1);
        arrayList.add(4,7);

        arrayList.sort(Integer::compareTo);

        assertEquals(1, arrayList.get(0));
        assertEquals(2, arrayList.get(1));
        assertEquals(5, arrayList.get(2));
        assertEquals(7, arrayList.get(3));
        assertEquals(8, arrayList.get(4));
    }

    @Test
    @DisplayName("Тест быстрой сортировки - буквы")
    void sortStringArrayList() {
        ArrayList<String> arrayListString = new ArrayList<>();
        arrayListString.add(0,"z");
        arrayListString.add(1,"b");
        arrayListString.add(2,"t");
        arrayListString.add(3,"c");
        arrayListString.add(4,"a");

        arrayListString.sort(String::compareTo);

        assertEquals("a", arrayListString.get(0));
        assertEquals("b", arrayListString.get(1));
        assertEquals("c", arrayListString.get(2));
        assertEquals("t", arrayListString.get(3));
        assertEquals("z", arrayListString.get(4));
    }
}