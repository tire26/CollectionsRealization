package com.company;

public class MapMethod implements Map {

    private static final int DEFAULT_CAPACITY = 16;
    private static final double DEFAULT_BORDER = 2 / 3.0;
    private int currentCapacity; // порог мапы ключей
    private int size; // кол-во элементов в мапе
    private Basket[] basketsArray;
    double borderOfBasketsArray;

    public MapMethod(int capacitySize, double borderOfBasketsArray) {

        if (capacitySize <= 0) {
            throw new IllegalArgumentException("was entered wrong size");
        } else if (borderOfBasketsArray <= 0 || borderOfBasketsArray > 1) {

            throw new IllegalArgumentException("was entered wrong border of filling baskets array");
        }
        this.currentCapacity = capacitySize;
        this.size = 0;
        this.basketsArray = new Basket[currentCapacity];
        this.borderOfBasketsArray = borderOfBasketsArray;
    }

    public MapMethod() {

        this(DEFAULT_CAPACITY, DEFAULT_BORDER);
    }

    public void increaseOfArray() { // увеличение массива

        currentCapacity *= 2;
        Basket[] newBasketsArray = new Basket[currentCapacity];
        for (Basket currentBasket : basketsArray) {
            if (currentBasket == null) {

                continue;
            }
            KeyField currentKeyFieldFromMap = currentBasket.getFirstKey();
            do { // проход по всем ключам в корзине

                if (currentKeyFieldFromMap == null) {

                    continue;
                }

                String key = currentKeyFieldFromMap.getKey(); // получаем из map значение key
                Integer value = currentKeyFieldFromMap.getValue(); // получаем из map value
                addingElement(key, value, newBasketsArray);
                currentKeyFieldFromMap = currentKeyFieldFromMap.getNextKeyField();
            } while (currentKeyFieldFromMap != null);
        }
        basketsArray = newBasketsArray;
    }

    public Integer calculationHashCode(String key) {

        if (key == null) {

            return 0;
        }
        return Math.abs(key.hashCode() % (currentCapacity));
    }

    public Integer addingElement(String key, Integer value, Basket[] baskets) { // функция добавления элемента в map

        int currentPosInArray = calculationHashCode(key);
        if (baskets[currentPosInArray] == null) { // создаётся новая корзина и туда добавляется пара ключ-значение

            baskets[currentPosInArray] = new Basket();
            KeyField newKeyField = new KeyField();
            newKeyField.setKey(key);
            newKeyField.setValue(value);
            baskets[currentPosInArray].setFirstKey(newKeyField);
        } else { // если нашу пару нужно положить в уже имещуюся корзину

            KeyField currentKeyFieldFromMap = baskets[currentPosInArray].getFirstKey();
            KeyField previousKeyFieldFromMap;
            do {

                if (currentKeyFieldFromMap.getKey() != null && currentKeyFieldFromMap.getKey().equals(key) || (currentKeyFieldFromMap.getKey() == null && key == null)) { // если ключ текущего элемента равен ключу нашего

                    int lastValue = currentKeyFieldFromMap.getValue();
                    currentKeyFieldFromMap.setValue(value);
                    return lastValue;
                }
                previousKeyFieldFromMap = currentKeyFieldFromMap;
                currentKeyFieldFromMap = currentKeyFieldFromMap.getNextKeyField();
            } while (currentKeyFieldFromMap != null);
            // добавление новой пары в корзину
            KeyField newKeyField = new KeyField();
            newKeyField.setKey(key);
            newKeyField.setValue(value);
            previousKeyFieldFromMap.SetNextKeyField(newKeyField);
        }
        baskets[currentPosInArray].incrementSize();
        size++;
        if (size > currentCapacity * borderOfBasketsArray) { // проверка, что в мапе не слишком много корзин

            size = 0;
            increaseOfArray();
        }
        return null;
    }

    public int size() {

        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean containsKey(String key) {

        int currentPosInArray = calculationHashCode(key);
        if (basketsArray[currentPosInArray] != null) {

            KeyField currentKeyField = basketsArray[currentPosInArray].getFirstKey();
            do { // проход по всем парам в корзине

                if (currentKeyField == null) {

                    continue;
                }
                if (currentKeyField.getKey() != null && currentKeyField.getKey().equals(key) || (currentKeyField.getKey() == null && key == null)) {

                    return true;
                }
                currentKeyField = currentKeyField.getNextKeyField();
            } while (currentKeyField != null);
        }
        return false;
    }

    public boolean containsValue(Integer value) {

        if (size > 0) {

            for (Basket currentBasket : basketsArray) { // проход по всем корзинам

                if (currentBasket == null) {

                    continue;
                }
                KeyField currentKeyField = currentBasket.getFirstKey();
                do { // проход по всем парам в корзине

                    if (currentKeyField == null) {

                        continue;
                    }
                    if (currentKeyField.getValue() != null && currentKeyField.getValue().equals(value) || (currentKeyField.getValue() == null && value == null)) {

                        return true;
                    }
                    currentKeyField = currentKeyField.getNextKeyField();
                } while (currentKeyField != null);
            }
        }
        return false;
    }

    public Integer get(String key) {

        int posInMap = calculationHashCode(key);
        if (basketsArray[posInMap] != null) {
            KeyField currentKeyFieldFromMap = basketsArray[posInMap].getFirstKey();
            do {
                if (currentKeyFieldFromMap == null) {

                    continue;
                }
                if (currentKeyFieldFromMap.getKey() != null && currentKeyFieldFromMap.getKey().equals(key) || (currentKeyFieldFromMap.getKey() == null && key == null)) {

                    return currentKeyFieldFromMap.getValue();
                }
                currentKeyFieldFromMap = currentKeyFieldFromMap.getNextKeyField();
            } while (currentKeyFieldFromMap != null);
        }
        return null;
    }

    public Integer put(String key, Integer value) {

        return addingElement(key, value, basketsArray);
    }

    public Integer remove(String key) {

        int currentPosInArray = calculationHashCode(key);
        if (basketsArray[currentPosInArray] != null) {
            KeyField currentKeyField = basketsArray[currentPosInArray].getFirstKey();
            KeyField previousKeyField;
            Integer lastValue;

            if (currentKeyField.getKey() != null && currentKeyField.getKey().equals(key) || (currentKeyField.getKey() == null && key == null)) { // проверка 1-го элемента

                lastValue = currentKeyField.getValue();
                if (basketsArray[currentPosInArray].getSize() == 1) {

                    size--;
                    basketsArray[currentPosInArray].decrementSize();
                    basketsArray[currentPosInArray] = null;
                    return lastValue;
                } else if (basketsArray[currentPosInArray].getFirstKey() == currentKeyField) {

                    size--;
                    basketsArray[currentPosInArray].decrementSize();
                    basketsArray[currentPosInArray].setFirstKey(currentKeyField.getNextKeyField());
                    return lastValue;
                }
            }
            previousKeyField = currentKeyField;
            currentKeyField = currentKeyField.getNextKeyField();
            do { // проход по всем парам в корзине

                if (currentKeyField.getKey() != null && currentKeyField.getKey().equals(key) || (currentKeyField.getKey() == null && key == null)) {

                    lastValue = currentKeyField.getValue();
                    if (basketsArray[currentPosInArray].getSize() > 1) { // если размер корзины с удаляемым элементом >= 2

                        previousKeyField.SetNextKeyField(currentKeyField.getNextKeyField());
                        basketsArray[currentPosInArray].decrementSize();
                    } else {

                        basketsArray[currentPosInArray] = null;
                    }
                    size--;
                    return lastValue;
                }
                previousKeyField = currentKeyField;
                currentKeyField = currentKeyField.getNextKeyField();
            } while (currentKeyField != null);
        }
        return null;

    }

    public void putAll(Map map) {

        if (map.size() > 0) {

            MapMethod currentMap = (MapMethod) map;
            for (Basket currentBasket : currentMap.getBasketsArray()) { // проход по всем корзинам в map
                if (currentBasket == null) {

                    continue;
                }
                KeyField currentKeyFieldFromMap = currentBasket.getFirstKey();
                do { // проход по всем ключам в корзине из map

                    if (currentKeyFieldFromMap == null) {

                        continue;
                    }
                    String key = currentKeyFieldFromMap.getKey(); // получаем из map значение key
                    Integer value = currentKeyFieldFromMap.getValue(); // получаем из map value
                    addingElement(key, value, basketsArray);
                    currentKeyFieldFromMap = currentKeyFieldFromMap.getNextKeyField();
                } while (currentKeyFieldFromMap != null);
            }
        }
    }

    public void clear() {

        basketsArray = new Basket[currentCapacity];
        size = 0;
    }

    public Basket[] getBasketsArray() {

        return basketsArray;
    }

}
