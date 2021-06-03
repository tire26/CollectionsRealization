package com.company;

import java.util.Scanner;

public class Main {

    public static int enterIntegerForArray(Scanner scanner, int size) {

        int number = 0;
        boolean check = false;

        while (!check) {

            if (scanner.hasNextInt()) {

                number = scanner.nextInt();
                if (number >= 0 && number < size) {
                    check = true;
                } else {

                    System.out.println("Was entered number less than 0 or bigger than size of array, try again: ");
                    scanner.nextLine();
                    check = false;
                }
            } else {

                System.out.println("Was entered not a number, try again: ");
                scanner.nextLine();
                check = false;
            }
        }
        return number;
    }

    public static int enterIntegerForMap(Scanner scanner) {

        int number = 0;
        boolean check = false;

        while (!check) {

            if (scanner.hasNextInt()) {

                number = scanner.nextInt();
                check = true;

            } else {

                System.out.println("Was entered not a number, try again: ");
                scanner.nextLine();
                check = false;
            }
        }
        return number;
    }

    private static final String END_COMMAND = "END", YES_COMMAND = "YES", NO_COMMAND = "NO", ARRAY_COMMAND = "ARRAY", LINKED_COMMAND = "LINKED", MAP_COMMAND = "MAP";

    public static void main(String[] args) {

        String buffer;
        Scanner scanner = new Scanner(System.in);
        String[] sep;
        boolean check = false;
        String mode;
        do {
            mode = scanner.nextLine();
            if (mode.equals(ARRAY_COMMAND) || mode.equals(LINKED_COMMAND) || mode.equals(MAP_COMMAND)) {

                check = true;
            } else {

                System.out.println("Was entered wrong mode");
            }

        } while (!check);

        check = false;
        switch (mode) {

            case ARRAY_COMMAND: {
                ArrayMethod array = new ArrayMethod();
                do {

                    buffer = scanner.nextLine();
                    if (!buffer.equals(END_COMMAND)) {

                        array.add(buffer);
                    }
                } while (!buffer.equals(END_COMMAND));
                int arraySize = array.size();
                System.out.println(arraySize);
                System.out.println("Want you to see all elements?");
                buffer = scanner.nextLine();
                if (array.size() > 0) {
                    do {

                        if (buffer.equals(YES_COMMAND)) {

                            sep = array.toArray();
                            for (String element :
                                    sep) {

                                System.out.println(element);
                            }
                            check = true;
                        } else if (buffer.equals(NO_COMMAND)) {

                            check = true;
                        } else {

                            System.out.println("Was entered wrong command, try again");
                            buffer = scanner.nextLine();
                        }
                    } while (!check);
                    System.out.println("Want you to see certain element?");
                    buffer = scanner.nextLine();
                    do {

                        if (buffer.equals(YES_COMMAND)) {

                            System.out.println("Enter index of certain element");
                            int index = enterIntegerForArray(scanner, arraySize);
                            System.out.println(array.get(index));
                            check = true;
                        } else if (buffer.equals(NO_COMMAND)) {

                            check = true;
                        } else {

                            check = false;
                            System.out.println("Was entered wrong command, try again");
                            buffer = scanner.nextLine();
                        }
                    } while (!check);
                }
            }
            break;

            case LINKED_COMMAND: {

                LinkedMethod linked = new LinkedMethod();
                do {

                    buffer = scanner.nextLine();
                    if (!buffer.equals(END_COMMAND)) {

                        linked.add(buffer);
                    }
                } while (!buffer.equals(END_COMMAND));
                int linkedSize = linked.size();
                System.out.println(linkedSize);
                System.out.println("Want you to see all elements?");
                buffer = scanner.nextLine();
                if (linked.size() > 0) {
                    do {

                        if (buffer.equals(YES_COMMAND)) {

                            sep = linked.toArray();
                            for (String element :
                                    sep) {

                                System.out.println(element);
                            }
                            check = true;
                        } else if (buffer.equals(NO_COMMAND)) {

                            check = true;
                        } else {

                            System.out.println("Was entered wrong command, try again");
                            buffer = scanner.nextLine();
                        }
                    } while (!check);
                    System.out.println("Want you to see certain element?");
                    buffer = scanner.nextLine();
                    do {

                        if (buffer.equals(YES_COMMAND)) {

                            System.out.println("Enter index of certain element");
                            int index = enterIntegerForArray(scanner, linkedSize);
                            System.out.println(linked.get(index));
                            check = true;
                        } else if (buffer.equals(NO_COMMAND)) {

                            check = true;
                        } else {

                            check = false;
                            System.out.println("Was entered wrong command, try again");
                            buffer = scanner.nextLine();
                        }
                    } while (!check);
                }
            }
            break;

            case MAP_COMMAND: {
                String inputKey;
                ;
                int inputValue;
                MapMethod map = new MapMethod();
                do {
                    inputKey = scanner.nextLine();
                    if (!inputKey.equals(END_COMMAND)) {

                        inputValue = enterIntegerForMap(scanner);
                        scanner.nextLine();
                        map.put(inputKey, inputValue);
                    }
                } while (!inputKey.equals(END_COMMAND));


                System.out.println(map.size());
                System.out.println("Want you to see all elements?");
                buffer = scanner.nextLine();
                do {

                    if (buffer.equals(YES_COMMAND)) {


                        if (map.size() > 0) {

                            for (Basket currentBasket : map.getBasketsArray()) {

                                if (currentBasket == null) {

                                    continue;
                                }
                                KeyField currentKey = new KeyField();
                                do {

                                    if (currentKey.getKey() == null) {

                                        currentKey = currentBasket.getFirstKey();
                                    } else {

                                        currentKey = currentKey.getNextKeyField();
                                    }
                                    System.out.print(currentKey.getKey());
                                    System.out.print(" - ");
                                    System.out.println(currentKey.getValue());
                                } while (currentKey.getNextKeyField() != null);
                            }
                        }
                        check = true;
                    } else if (buffer.equals(NO_COMMAND)) {

                        check = true;
                    } else {

                        check = false;
                        System.out.println("Was entered wrong command, try again");
                        buffer = scanner.nextLine();
                    }
                } while (!check);
                System.out.println("Want you to see certain element?");
                buffer = scanner.nextLine();
                do {

                    if (buffer.equals(YES_COMMAND)) {

                        System.out.println("Enter key of certain element");
                        buffer = scanner.nextLine();
                        System.out.println(map.get(buffer));
                        check = true;
                    } else if (buffer.equals(NO_COMMAND)) {

                        check = true;
                    } else {

                        check = false;
                        System.out.println("Was entered wrong command, try again");
                        buffer = scanner.nextLine();
                    }
                } while (!check);
            }
            break;
        }

    }

}
