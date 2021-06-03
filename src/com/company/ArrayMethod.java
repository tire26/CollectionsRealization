package com.company;

public class ArrayMethod implements Array {

    private static final int DEFAULT_SIZE = 16;
    private String[] array;
    private int countOfElements;
    private int realSize;

    public ArrayMethod(int startSize) {

        if (startSize > 0) {
            this.array = new String[startSize];
            this.realSize = startSize;
            this.countOfElements = 0;
        } else {

            throw new IllegalArgumentException("Was entered wrong size");
        }
    }

    public ArrayMethod() {

        this(DEFAULT_SIZE);
    }

    public boolean addingElementInTheEnd(String element) {

        if (countOfElements + 1 >= realSize) { // расширение если нужно

            String[] bufferArray = new String[realSize];
            System.arraycopy(array, 0, bufferArray, 0, countOfElements);
            realSize *= 2;
            array = new String[realSize];
            System.arraycopy(bufferArray, 0, array, 0, countOfElements);
        }
        array[countOfElements] = element;
        countOfElements++;
        return true;
    }

    public void addingElementInCurrentPos(int index, String element) {

        if (countOfElements + 1 >= realSize) { // расширение если нужно

            String[] bufferArray = new String[realSize];
            System.arraycopy(array, 0, bufferArray, 0, countOfElements);
            realSize *= 2;
            array = new String[realSize];
            System.arraycopy(bufferArray, 0, array, 0, index);
        }
        System.arraycopy(array, index, array, index + 1, countOfElements - index);
        array[index] = element;
        countOfElements++;
    }

    public int size() {

        return countOfElements;
    }

    public boolean isEmpty() {

        return countOfElements == 0;
    }

    public boolean contains(String o) {

        for (String element : array) {

            if ((o == null && element == null) || (o != null && o.equals(element))) {

                return true;
            }
        }
        return false;
    }

    public String[] toArray() {

        String[] outputArray = new String[countOfElements];
        System.arraycopy(array, 0, outputArray, 0, countOfElements);
        return outputArray;
    }

    public boolean add(String o) {

        return addingElementInTheEnd(o);
    }

    public boolean addAll(Array c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong array");
        }
        int cSize = c.size();
        for (int i = 0; i < cSize; ++i) {

            String element = c.get(i);
            addingElementInTheEnd(element);
        }

        return true;
    }

    public boolean addAll(int index, Array c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong array");
        }
        if (countOfElements < index || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        int cSize = c.size();
        for (int i = 0; i < cSize; ++i) {

            String element = c.get(i);
            if (index == countOfElements) {

                addingElementInTheEnd(element);
            } else {

                addingElementInCurrentPos(index, element);

            }
            index++;
        }
        return true;
    }

    public void clear() {

        for (int i = 0; i < countOfElements; i++) {

            array[i] = null;
        }
        countOfElements = 0;
    }

    public String get(int index) {

        if (countOfElements <= index || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        return array[index];
    }

    public String set(int index, String element) {

        if (countOfElements <= index || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        String changedElement = array[index];
        array[index] = element;
        return changedElement;
    }

    public void add(int index, String element) {

        if (countOfElements < index || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        if (index == countOfElements) {

            addingElementInTheEnd(element);
        } else {

            addingElementInCurrentPos(index, element);
        }
    }

    public String remove(int index) {

        if (countOfElements <= index || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        String removedElement = array[index];
        System.arraycopy(array, index + 1, array, index, countOfElements - index);
        countOfElements--;
        return removedElement;
    }

    public boolean remove(Object o) {

        for (int i = 0; i < countOfElements; i++) {

            String currentElement = array[i];
            if ((currentElement == o && o == null) || (currentElement != null && currentElement.equals(o))) {

                System.arraycopy(array, i + 1, array, i, countOfElements - i);
                countOfElements--;
                return true;
            }
        }
        return false;
    }

    public int indexOf(String o) {

        for (int i = 0; i < countOfElements; i++) {

            String currentElement = array[i];
            if ((currentElement == null && o == null) || (currentElement != null && currentElement.equals(o))) {

                return i;
            }
        }
        return -1;
    }

    public int lastIndexOf(String o) {

        int currentIndex = -1;
        for (int i = 0; i < countOfElements; i++) {

            String currentElement = array[i];
            if ((currentElement == null && o == null) || (currentElement != null && currentElement.equals(o))) {

                currentIndex = i;
            }
        }
        return currentIndex;
    }

    public Array subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || countOfElements < fromIndex || countOfElements < toIndex) {

            throw new IllegalArgumentException("was entered wrong indexes");
        }
        ArrayMethod newArrayMethod = new ArrayMethod();
        for (int i = fromIndex; i < toIndex; ++i) {

            newArrayMethod.add(array[i]);
        }
        return newArrayMethod;
    }

    public boolean removeAll(Array c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong array");
        }
        boolean isArrayChanged = false;
        for (int i = 0; i < countOfElements; i++) {

            for (int j = 0; j < c.size(); j++) {

                String currentElement = array[i];
                String elementFromC = c.get(j);
                if ((currentElement == null && elementFromC == null) || (currentElement != null && currentElement.equals(elementFromC))) {

                    System.arraycopy(array, i + 1, array, i, countOfElements - i);
                    countOfElements--;
                    isArrayChanged = true;
                }
            }
        }
        return isArrayChanged;
    }

    public boolean containsAll(Array c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong array");
        }
        int countOfRemainingChecks = c.size();
        for (int i = 0; i < countOfElements; i++) { // проход по нашему массиву

            for (int j = 0; j < c.size(); j++) { // проход по масссиву c

                String currentElementInOurArray = array[i];
                String currentElementInCArray = c.get(j);
                if ((currentElementInOurArray == null && currentElementInCArray == null) || (currentElementInOurArray != null && currentElementInOurArray.equals(currentElementInCArray))) {

                    countOfRemainingChecks--;
                }
            }
        }
        return countOfRemainingChecks == 0;
    }
}
