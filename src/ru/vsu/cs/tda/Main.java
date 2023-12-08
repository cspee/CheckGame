package ru.vsu.cs.tda;

import java.util.Scanner;

public class Main {
    //реализовано только движение белых шашек вперед на пустые(* ) клетки
    String[][] chesT = new String[10][10];

    public static void main(String[] args) {
        Main m = new Main();


        m.fullingChest();
        m.sol();
        //m.print();


    }

    


    /*int unicodeValue = 65; // Значение Unicode для символа 'A'
    char charFromUnicode = Character.toChars(unicodeValue)[0];
    System.out.println("Символ из Unicode: " + charFromUnicode);*/
    private void fullingChest() {

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (i == 0) {
                    chesT[i][j] = "— ";
                } else if (j == 0 || (i == 9 && j == 9)) {
                    chesT[i][j] = "| ";
                } else if (i == 9 && j >= 1 && j != 9) {
                    chesT[i][j] = (Character.toChars(65 + j - 1)[0]) + " ";
                } else if (i != 0 && i != 9 && j == 9) {
                    chesT[i][j] = (Character.toChars(49 + i - 1)[0]) + " ";
                } else {
                    chesT[i][j] = "* ";
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if ((i == 1 || i == 3) && j % 2 == 0 && j > 0) {
                    chesT[i][j] = "B ";
                } else if (i == 2 && j % 2 != 0 && j != 9) {
                    chesT[i][j] = "B ";
                } else if ((i == 6 || i == 8) && j % 2 != 0 && j != 9) {
                    chesT[i][j] = "W ";
                } else if (i == 7 && j % 2 == 0 && j > 0) {
                    chesT[i][j] = "W ";
                }
            }
        }
    }

    private void print() {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(chesT[i][j]);
            }
            System.out.println();
        }
    }

    public void sol() {
        System.out.println("Сделайте шаг, например: шашка A6 на B5");
        print();
        Scanner scanner = new Scanner(System.in);
        String s1 = scanner.nextLine();
        String s2 = scanner.nextLine();
        doMove(s1, s2);

    }


    private void doMove(String fromPos, String toPos) {

        if ((fromPos.length() > 2 && toPos.length() > 2) || (fromPos.length() == 1 && toPos.length() == 1)) {
            System.out.println("Ошибка ввода ");
            sol();
        }

        String stolbecFrom = "";
        String stolbecTo = "";
        if (chekLetter(fromPos, toPos)) {
            stolbecFrom = String.valueOf(fromPos.charAt(0));
            stolbecTo = String.valueOf(toPos.charAt(0));
        } else {
            System.out.println("Ошибка ввода");
            sol();
        }
        String strokFrom = String.valueOf(fromPos.charAt(1));
        String strokaTo = String.valueOf(toPos.charAt(1));

        int fromY = Integer.parseInt(strokFrom);
        int toY = Integer.parseInt(strokaTo);
        if ((fromY >= 1 && fromY <= 8) && (toY >= 1 && toY <= 8)) {
            if (chesT[fromY][getNumberOfletter(stolbecFrom)].trim().equals("W")) {// к этому моменту имеем уже прговеренные входные данные
                if (isMoveForwardCorrect(fromPos, toPos)) {
                    chesT[fromY][getNumberOfletter(stolbecFrom)] = "* ";
                    chesT[toY][getNumberOfletter(stolbecTo)] = "W ";
                    sol();
                }else {
                    System.out.println("Неправильное перемещение");
                    sol();
                }

            } else if (chesT[fromY][getNumberOfletter(stolbecFrom)].trim().equals("B")) {
                chesT[fromY][getNumberOfletter(stolbecFrom)] = "* ";
                chesT[toY][getNumberOfletter(stolbecTo)] = "B ";
                sol();
            } else {
                System.out.println("Вы выбрали не шашку");
                sol();
            }

        } else {
            System.out.println("Ошибка ввода");
            sol();
        }
    }

    //C6 D5
    private boolean isMoveForwardCorrect(String fromPos, String toPos) {
        boolean isOk = false;
        if (chesT[Integer.parseInt(String.valueOf(fromPos.charAt(1))) - 1][getNumberOfletter(String.valueOf(fromPos.charAt(0))) + 1].equals("* ")/*Integer.parseInt(fromPos.charAt(1))])*/) {
            isOk = true;
        } else if (chesT[Integer.parseInt(String.valueOf(fromPos.charAt(1))) - 1]
                [getNumberOfletter(String.valueOf(fromPos.charAt(0))) - 1].equals("* ")) {
            isOk = true;
        }
        //[Integer.parseInt(String.valueOf(fromPos.charAt(1))) - 1] = "";
        return isOk;
    }


    private boolean chekLetter(String fromPos, String toPos) {
        boolean result1 = false;
        boolean result2 = false;
        for (char c = 'A'; c <= 'H'; c++) {
            if (c == fromPos.charAt(0)) {
                result1 = true;
            }
            if (c == toPos.charAt(0)) {
                result2 = true;
            }
        }
        if (result1 && result2) {
            return true;
        } else {
            return false;
        }

    }

    private int getNumberOfletter(String letter) {
        int result = 1;

        for (int i = 1; i <= 8; i++) {
            if (letter.equals(chesT[9][i].trim())) {

                break;
            } else {
                result++;
            }
        }
        if (result < 1 || result > 8) {
            return 0;
        }

        return result;
    }
}
