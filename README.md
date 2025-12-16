## Отчет по лабораторной работе № 3

#### № группы: `ПМ-2502`

#### Выполнил: `Пашенко Виталий Витальевич`

#### Вариант: `13`

### Cодержание:

- [Постановка задачи](#1-постановка-задачи)
- [Входные и выходные данные](#2-входные-и-выходные-данные)
- [Алгоритм](#3-алгоритм)
- [Программа](#4-программа)

 ### 1. Постановка задачи

> Разработать программу для классической игры «Крестики-нолики» для двух игроков.
Реализовать функционал создания игрового поля, обработки ходов игроков, проверки текущего состояния игры и определения победителя.

Для создания игрового поля и действий, связанных с ним я создам новый класс `Game`. В нем буду хранить поле и имена игроков, реализую такие функции: 
определение очередности хода, совершение хода, проверка на победу, проверка на ничнью, перезапуск игры(после ее окончания).

### 2. Входные и выходные данные

#### Данные на вход

На вход программа получает два символа - имена игроков (в классических крестиках ноликах это символы `x` и `o`). Затем, когда игровое поле будет создано,
пользователям будет необходимо вводить координаты, на которые они хотят походить.

#### Данные на выход

На вывод программа каждый раз будет выводить игровое поле класса `Game`(и консольнгые сообщения, связанные с действием над игрой).

### 3. Алгоритм
  1. Вводим имена игроков и задаем новое игровое поле.
  2. Всего у нас может быть совершено 9 ходов, так как игровое поле состоит из 9 клеток. Поэтому пока не будет совершено все 9 ходов, нам необходимо делать проверки
на победиля и спрашивать координаты на которые хочет пойти игрок.
  3. Игра заканчивается, когда совершено все 9 ходов или найден победитель.
  4. После завершения игры, программа спросить пользователей, хотят ли они начать новую игру.

### 4. Программа
```java
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
        String f = in.nextLine();
        while (!f.equals("1") && !f.equals("0")){ // Проверка, что введена правильная функция
            out.println("Ошибка ввода, такой функции не существует");
            out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
            f = in.nextLine();
        }
        while (f.equals("1")) {
            out.println("Введите имена игроков через enter (состоящие из одной буквы):");
            String n1 = in.nextLine();
            String n2 = in.nextLine();
            while (n1.length() != 1 || n2.length() != 1 || n1.equals(n2) || n1.equals("-") || n2.equals("-") || n1.equals(" ") || n2.equals(" ")) {
// Проверка на то, что имена введены правильно
                if (n1.length() != 1 || n2.length() != 1){
                    out.println("Неверная длина имен(возможно поставлен лишний пробел)");
                    n1 = in.nextLine();
                    n2 = in.nextLine();
                }
                else
                    if (n1.equals(n2)){
                        out.println("Имена не должны быть одинаковыми");
                        n1 = in.nextLine();
                        n2 = in.nextLine();
                    }
                    else {
                        out.println("Именна должны быть символом(за исключением - и проблела");
                        n1 = in.nextLine();
                        n2 = in.nextLine();
                    }
            }
            Game pl = new Game(n1, n2); //Задание нового игрового поля
            out.println(pl);
            int q = 1;
            while (q <= 9) {
                pl.xod(q);
                out.println(pl);
                if (pl.win(q) == 1) { //Проверяем условие победы 1 игрока
                    q = 10;
                    out.println("Победил игрок " + n1 + "!");
                    out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                    f = in.nextLine();
                } else if (pl.win(q) == 2) { //Проверяем условие победы 2 игрока
                    q = 10;
                    out.println("Победил игрок " + n2 + "!");
                    out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                    f = in.nextLine();
                } else { //Если никто не выиграл, до проверяем на ничью. Если нет ничьи, то делаем новый ход
                    q++;
                    if (pl.draw(q)){
                        out.println("Ходы кончились, никто не выиграл, ничья!");
                        out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                        f = in.nextLine();
                    }
                    else
                        out.println("Игра в процессе");
                }
            }
        }
    }
}
class Game{
    public static Scanner in = new Scanner(System.in); 
    String Name1; //Храним в полях имена и игровое поле
    String Name2;
    String[][]pole = {{"-", "-", "-"}, {"-", "-", "-"}, {"-", "-", "-"}};
    @Override
    public String toString(){
        return "| " + pole[0][0] + " | " + pole[0][1] + " | " + pole[0][2] + " |" + "\n" + "| " + pole[1][0] + " | " + pole[1][1] + " | " + pole[1][2] + " |"
                + "\n" + "| " + pole[2][0] + " | " + pole[2][1] + " | " + pole[2][2] + " |";
    }
    public Game(String Name1, String Name2){
        this.Name1 = Name1;
        this.Name2 = Name2;
    }
    public String cheyxod(int q){ //Определяем чья очередность хода
        if (q%2==0){
            return "Ход игрока " + Name2;
        }
        else
            return "Ход игрока " + Name1;
    }
    public boolean draw(int q){ //Проверяем, закончились ли ходы
        return q == 10;
    }
    public void xod(int q) {
            System.out.println();
            System.out.println(cheyxod(q));
            System.out.println("Введите координаты через enter(Без лишних пробелов)");
            String x1 = in.nextLine();
            String y1 = in.nextLine();
            while (x1.length() != 1 || y1.length() != 1 || !('0' < x1.charAt(0) && x1.charAt(0) < '4' && '0' < y1.charAt(0) && y1.charAt(0) < '4')){
                System.out.println("Ошибка ввода(проверьте на наличие лишних пробелов)"); //Проверяем, что координаты введены правильно(в плане, что введены числа)
                x1 = in.nextLine();
                y1 = in.nextLine();
            }
            int x = x1.charAt(0) - '0';
            int y = y1.charAt(0) - '0';
            while (!(0 < x && x < 4 && 0 < y && y < 4) || !pole[x - 1][y - 1].equals("-")) { //Проверяем, что координаты введены правильно(в плане, что клетка не занята и координаты не выходят за границы)
                if (!(0 < x && x < 4 && 0 < y && y < 4)) {
                    System.out.println();
                    System.out.println("Введены неверные координаты!");
                    System.out.println(cheyxod(q));
                    System.out.println("Введите координаты через enter(Без лишних пробелов)");
                    x1 = in.nextLine();
                    y1 = in.nextLine();
                    while (x1.length() != 1 || y1.length() != 1 || !('0' < x1.charAt(0) && x1.charAt(0) < '4' && '0' < y1.charAt(0) && y1.charAt(0) < '4')) {
                        System.out.println("Ошибка ввода(проверьте на наличие лишних пробелов)");
                        x1 = in.nextLine();
                        y1 = in.nextLine();
                    }
                    x = x1.charAt(0) - '0';
                    y = y1.charAt(0) - '0';
                }
                else{
                    System.out.println();
                    System.out.println("Это поле занято, введи другие координаты!");
                    System.out.println(cheyxod(q));
                    System.out.println("Введите координаты через enter(Без лишних пробелов)");
                    x1 = in.nextLine();
                    y1 = in.nextLine();
                    while (x1.length() != 1 || y1.length() != 1 || !('0' < x1.charAt(0) && x1.charAt(0) < '4' && '0' < y1.charAt(0) && y1.charAt(0) < '4')) {
                        System.out.println("Ошибка ввода(проверьте на наличие лишних пробелов)");
                        x1 = in.nextLine();
                        y1 = in.nextLine();
                    }
                    x = x1.charAt(0) - '0';
                    y = y1.charAt(0)  - '0';
                    }
                }
            if (q % 2 == 0) {//Отмечаем, куда походил игрок
                pole[x - 1][y - 1] = this.Name2;
            } else {
                pole[x - 1][y - 1] = this.Name1;
            }
    }


    public int win(int e){//Проверяем, что какой-либо игрок выиграл
        String q = Name1 + Name1 + Name1;
        String w = Name2 + Name2 + Name2;
        if ((pole[0][0] + pole[0][1] + pole[0][2]).equals(q) || (pole[1][0] + pole[1][1] + pole[1][2]).equals(q) ||
                (pole[2][0] + pole[2][1] + pole[2][2]).equals(q) || (pole[0][0] + pole[1][0] + pole[2][0]).equals(q) ||
                (pole[0][1] + pole[1][1] + pole[2][1]).equals(q) || (pole[0][2] + pole[1][2] + pole[2][2]).equals(q) ||
                (pole[0][0] + pole[1][1] + pole[2][2]).equals(q) || (pole[0][2] + pole[1][1] + pole[2][0]).equals(q)){
            return 1; //Если выиграл 1 игрок
        }
        else {
            if ((pole[0][0] + pole[0][1] + pole[0][2]).equals(w) || (pole[1][0] + pole[1][1] + pole[1][2]).equals(w) ||
                    (pole[2][0] + pole[2][1] + pole[2][2]).equals(w) || (pole[0][0] + pole[1][0] + pole[2][0]).equals(w) ||
                    (pole[0][1] + pole[1][1] + pole[2][1]).equals(w) || (pole[0][2] + pole[1][2] + pole[2][2]).equals(w) ||
                    (pole[0][0] + pole[1][1] + pole[2][2]).equals(w) || (pole[0][2] + pole[1][1] + pole[2][0]).equals(w)) {
                return 2; //Если выиграл 2 игрок
            }
            else
                return 0; //Если никто не выиграл
        }
    }
}
```
