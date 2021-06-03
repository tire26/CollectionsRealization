package com.company;

public class LinkedMethod implements Linked {

    Element headElement; // ссылка на последний элемент списка
    Element firstElement; // ссылка на первый элемент списка
    int size = 0;

    public int size() {

        return size;
    }

    public boolean isEmpty() {


        return headElement == null;
    }

    public boolean contains(String o) {

        if (firstElement != null) {

            Element currentElement = firstElement;
            do {

                if ((currentElement.getValue() != null && currentElement.getValue().equals(o)) || (currentElement.getValue() == null && o == null)) {

                    return true;
                }
                currentElement = currentElement.getNextElement();

            } while (currentElement != null);
        }
        return false;
    }

    public String[] toArray() {

        String[] valuesArray = new String[]{};
        if (firstElement != null) {

            Element currentElement = firstElement;
            valuesArray = new String[size];
            for (int i = 0; i < size && currentElement.getNextElement() != null; i++, currentElement = currentElement.getNextElement()) {

                valuesArray[i] = currentElement.getValue();
            }
            valuesArray[size - 1] = currentElement.getValue();
        }
        return valuesArray;
    }

    public void addingInEndOfLink(Element currentElement) { // функция добавления элемента в конец списка

        if (firstElement == null) {

            headElement = currentElement;
            firstElement = currentElement;
        } else {

            headElement.setNextElement(currentElement);
            headElement = currentElement;
        }
        size++;
    }

    public boolean add(String o) {

        Element currentElement = new Element();
        currentElement.setValue(o);
        addingInEndOfLink(currentElement);
        return true;
    }

    public boolean addAll(Linked c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong linked");
        }
        int lastSize = size;
        for (int i = 0; i < c.size(); ++i) {

            Element currentElement = new Element();
            currentElement.setValue(c.get(i));
            addingInEndOfLink(currentElement);
        }
        return lastSize != size;
    }

    public boolean addAll(int index, Linked c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong linked");
        }
        if (index > size || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        int lastSize = size;
        int currentIndex = 0;
        Element currentElement = firstElement;
        Element previousElement = null;
        do {

            if (currentIndex == index || index == size) {

                for (int i = 0; i < c.size(); ++i) {

                    String element = c.get(i);
                    Element bufferElement = new Element();
                    if (index == 0) { // если начинаем заполнять с первого элемента

                        bufferElement.setValue(element);
                        bufferElement.setNextElement(firstElement);
                        firstElement = bufferElement;
                        if (headElement == null) {

                            headElement = bufferElement;
                        }
                        previousElement = firstElement;
                        size++;
                    } else if (currentIndex == size || index == size) { // если начинаем заполнять с последнего элемента

                        Element newElement = new Element();
                        newElement.setValue(element);
                        addingInEndOfLink(newElement);
                    } else { // добавление элем-ов где-то по середине

                        bufferElement.setValue(element);
                        bufferElement.setNextElement(currentElement);
                        previousElement.setNextElement(bufferElement);
                        previousElement = bufferElement;
                        size++;
                    }
                    currentIndex++;
                    index++;
                }
            }
            currentIndex++;
            previousElement = currentElement;
            currentElement = currentElement.getNextElement();
        } while (currentElement != null);
        return lastSize != size;
    }

    public void clear() {

        firstElement = null;
        headElement = null;
        size = 0;
    }

    public String get(int index) {

        if (index >= size || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        Element currentElement = firstElement;
        int currentElementPos = 0;
        do {

            if (currentElementPos == index) {

                return currentElement.getValue();
            }
            currentElement = currentElement.getNextElement();
            currentElementPos++;

        } while (currentElement != null);
        return null;
    }

    public String set(int index, String element) {

        if (index >= size || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        Element currentElement = firstElement;
        int currentElementPos = 0;
        do {

            if (currentElementPos == index) {

                String oldElement = currentElement.getValue();
                currentElement.setValue(element);
                return oldElement;
            }
            currentElement = currentElement.getNextElement();
            currentElementPos++;
        } while (currentElement != null);
        return null;
    }

    public void add(int index, String element) {

        if (index > size || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        boolean isCheckDone = false;
        int countOfElements = 1;
        Element currentElement = firstElement;
        do {

            if (index == 0) { // если добавляем элемент в начало

                Element bufferElement = new Element();
                bufferElement.setNextElement(currentElement);
                bufferElement.setValue(element);
                firstElement = bufferElement;
                isCheckDone = true;
                if (size == 0) {

                    headElement = bufferElement;
                }
                size++;
            } else if (index == size) { // если добавляем в конец

                Element newElement = new Element();
                newElement.setValue(element);
                addingInEndOfLink(newElement);
                isCheckDone = true;
            } else if (countOfElements == index) { // если добавляем по-середине

                Element bufferElement = new Element();
                bufferElement.setValue(element);
                bufferElement.setNextElement(currentElement.getNextElement());
                currentElement.setNextElement(bufferElement);
                size++;

                isCheckDone = true;
            }
            if (isCheckDone) {

                break;
            }
            countOfElements++;
            currentElement = currentElement.getNextElement();
        } while (currentElement != null);
    }

    public String remove(int index) {

        if (index >= size || index < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        int currentIndex = 0;
        Element currentElement = firstElement;
        Element previousElement = null;
        do {

            if (currentIndex == index) {

                String deletedElement = currentElement.getValue();
                if (index == 0) {

                    firstElement = currentElement.getNextElement();
                } else if (currentIndex == size - 1) {

                    previousElement.setNextElement(null);
                    headElement = previousElement;
                } else {

                    previousElement.setNextElement(currentElement.getNextElement());
                }
                size--;
                return deletedElement;
            }
            currentIndex++;
            previousElement = currentElement;
            currentElement = currentElement.getNextElement();
        } while (currentElement != null);
        return null;
    }

    public boolean remove(Object o) {


        if (size == 0) {

            return false;
        }

        if (size == 1) {

            if ((firstElement.getValue() != null && firstElement.getValue().equals(o)) || (firstElement.getValue() == null && firstElement == null)) {

                firstElement = null;
                headElement = null;
                size--;
                return true;
            }
            return false;
        }
        Element previousElement = firstElement;
        Element currentElement = previousElement.getNextElement();
        do {

            if ((currentElement.getValue() != null && currentElement.getValue().equals(o)) || (currentElement.getValue() == null && o == null)) {

                size--;
                if (currentElement == headElement) {

                    previousElement.setNextElement(null);
                    headElement = previousElement;
                    return true;
                }
                previousElement.setNextElement(currentElement.getNextElement());
                return true;
            }
            currentElement = currentElement.getNextElement();
            previousElement = currentElement;
        } while (currentElement != null);
        return false;
    }

    public int indexOf(String o) {
        int currentIndex = 0;
        if (firstElement != null) {

            if (size == 1) {

                if ((firstElement.getValue() != null && firstElement.getValue().equals(o)) || (firstElement.getValue() == null && firstElement == null)) {

                    return currentIndex;
                } else {

                    return -1;
                }
            }
            Element currentElement = firstElement;
            do {

                if ((currentElement.getValue() != null && currentElement.getValue().equals(o)) || (currentElement.getValue() == null && o == null)) {

                    return currentIndex;
                }
                currentIndex++;
                currentElement = currentElement.getNextElement();
            } while (currentElement != null);
        }
        return -1;
    }

    public int lastIndexOf(String o) {

        int currentIndex = -1;
        int indexOfElement = -1;
        boolean isCheckDone = false;
        if (firstElement != null) {

            Element currentElement = firstElement;
            currentIndex++;
            do {

                if ((currentElement.getValue() != null && currentElement.getValue().equals(o)) || (currentElement.getValue() == null && o == null)) {

                    indexOfElement = currentIndex;
                    isCheckDone = true;
                }
                currentIndex++;
                currentElement = currentElement.getNextElement();
            } while (currentElement != null);
        }
        if (isCheckDone) {

            return indexOfElement;
        } else {
            return -1;
        }
    }

    public Linked subList(int fromIndex, int toIndex) {

        if (fromIndex < 0 || toIndex > size || fromIndex > size - 1 || toIndex < 0) {

            throw new IllegalArgumentException("was entered wrong index");
        }
        int currentIndex = 0;
        Element currentElement = firstElement;
        LinkedMethod returnedLink = new LinkedMethod();
        do {
            if (currentIndex >= fromIndex && currentIndex < toIndex) {

                returnedLink.add(currentElement.getValue());
            }
            currentElement = currentElement.getNextElement();
            currentIndex++;
        } while (currentElement != null);
        return returnedLink;
    }

    public boolean removeAll(Linked c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong linked");
        }
        boolean pointer;
        boolean isLinkChanged = false;
        if (size > 0) {

            Element currentElement = firstElement;
            Element previousElement = null;
            do {

                pointer = false;
                for (int i = 0; i < c.size() && !pointer; ++i) {

                    String element = c.get(i);
                    if ((currentElement.getValue() != null && currentElement.getValue().equals(element)) || (currentElement.getValue() == null && element == null)) {
                        if (size == 1) { // если рзамер спика 1

                            firstElement = null;
                            isLinkChanged = true;
                            pointer = true;
                            size--;
                        } else if (currentElement == firstElement) { // если удаляемый элемент 1

                            firstElement = currentElement.getNextElement();
                            isLinkChanged = true;
                            pointer = true;
                            size--;
                        } else {

                            if (currentElement == headElement) {

                                headElement = previousElement;
                            }
                            previousElement.setNextElement(currentElement.getNextElement());
                            isLinkChanged = true;
                            pointer = true;
                            size--;
                        }
                    }
                }
                if (!pointer) {

                    previousElement = currentElement;
                }
                currentElement = currentElement.getNextElement();
            } while (currentElement != null);
        }
        return isLinkChanged;
    }

    public boolean containsAll(Linked c) {

        if (c == null) {

            throw new IllegalArgumentException("was entered wrong linked");
        }
        boolean isChekDone = false;
        for (int i = 0; i < c.size(); ++i) {

            String currentElementFromC = c.get(i);
            Element currentElement = firstElement;
            do {

                if ((currentElement.getValue() != null && currentElement.getValue().equals(currentElementFromC)) || (currentElement.getValue() == null && currentElementFromC == null)) {

                    isChekDone = true;
                    break;
                }
                currentElement = currentElement.getNextElement();
            } while (currentElement != null);
            if (!isChekDone) {

                return false;
            }
        }
        return isChekDone;
    }
}
