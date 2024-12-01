package org.example;

import java.util.Arrays;
import java.util.Comparator;

public class MyArrayList <T>  {
    Object[] array;
    private int size;
    private static final int DEFAULT_CAPACITY = 10;

    /**
     * Конструктор создающий пустой лист с 10 ячейками по умолчанию.
     */
    public MyArrayList() {
        array = new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Метод вначале проверяет не заполнен ли лист.
     * В случае переполнения примняет метод ensureCapacity для увелечения вместимости в двое.
     * После добавляет значение @param item в конец листа.
     */
    public void add(T item) {
        ensureCapacity();
        array[size++] = item;
    }

    /**
     * Метод добавляет элемент по индексу
     * @param index - место добавления элемента
     * @param item - добавялемый элемент
     * Перед добавлением элемента проверяет корректность индекса и не будет ли переполнен массив.
     */
    public void add(int index, T item) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = item;
        size++;
    }

    /**
     * Возвращает значение по индексу
     * @param index - номер ячейки из которой необходимо получить значение
     * @return - возвращаемое значение
     * перед выполнением метода checkIndex проверяет корректность введенного индекса
     */
    public T get(int index) {
        checkIndex(index);
        return (T) array[index];
    }

    /**
     * Метод удаляет значение ячейки по индексу со сдвигом всех оставшихся элементов влево
     * @param index - номер ячейки которую необходимо очистить
     */
    public T remove(int index) {
        checkIndex(index);
        T removedItem = (T) array[index];
        System.arraycopy(array, index + 1, array, index, size - index - 1);
        array[--size] = null;
        return removedItem;
    }

    /**
     * Метод полностью очищает лист
     */
    public void clear() {
        Arrays.fill(array, 0, size, null);
        size = 0;
    }

    /**
     * Метод для замены значения ячейки по индексу
     * @param index - номер ячейки которую необходимо заменить
     * @param item - новое значение ячейки
     */
    public void set(int index, T item) {
        checkIndex(index);
        array[index] = item;
    }

    /**
     * Метод возвращает количество элементов
     */
    public int size() {
        return size;
    }

    /**
     * Метод для реализации быстрой сортировки с предоставленным компаратором
     * @param comparator - предоставленный компаратор
     */
    public void sort(Comparator<? super T> comparator) {
        quickSort(0, size - 1, comparator);
    }

    /**
     * Метод для реализации быстрой сортировки посредвом comparable
     */
    public void sort() {
        if (!(array[0] instanceof Comparable)) {
            throw new UnsupportedOperationException("Elements must implement Comparable interface");
        }
        quickSort(0, size - 1, null); //Используем Comparable если Comparator не указан
    }

    /**
     * Метод для увеличения внутреннего размера массива в два раза если он заполнен
     */
    private void ensureCapacity() {
        if (size == array.length) {
            int newCapacity = array.length * 2;
            Object[] newArray = new Object[newCapacity];
            System.arraycopy(array, 0, newArray, 0, size);
            array = newArray;
        }
    }

    /**
     * Метод для проверки корректности введенного индекса
     * Возвращает ошибку если индекс не корректен
     * @param index - получаемый для проверки индекс
     */
    private void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    /**
     * Рекурсивная функция быстрой сортировки
     * Разбивает массив на две части вокруг опорного элемента pivot
     */
    private void quickSort(int low, int high, Comparator<? super T> comparator) {
        if (low < high) {
            int pivotIndex = partition(low, high, comparator);
            quickSort(low, pivotIndex - 1, comparator);
            quickSort(pivotIndex + 1, high, comparator);
        }
    }

    /**
     * Метод разделяет массив на две части
     * Элементы которого меньше pivot и элементы которого больше pivot
     */
    private int partition(int low, int high, Comparator<? super T> comparator) {
        T pivot = (T) array[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            int cmp = (comparator != null) ? comparator.compare((T) array[j], pivot) : ((Comparable<? super T>) array[j]).compareTo(pivot);
            if (cmp < 0) {
                i++;
                swap(i, j);
            }
        }
        swap(i + 1, high);
        return (i + 1);
    }

    /**
     * Метод меняющий местами элементы массива
     */
    private void swap(int i, int j) {
        Object temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}

