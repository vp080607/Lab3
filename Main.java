import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
        String f = in.nextLine();
        while (!f.equals("1") && !f.equals("0")){
            out.println("Ошибка ввода, такой функции не существует");
            out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
            f = in.nextLine();
        }
        while (f.equals("1")) {
            out.println("Введите имена игроков через enter (состоящие из одной буквы):");
            String n1 = in.nextLine();
            String n2 = in.nextLine();
            while (n1.length() != 1 || n2.length() != 1 || n1.equals(n2) || n1.equals("-") || n2.equals("-") || n1.equals(" ") || n2.equals(" ")) {
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
            Game pl = new Game(n1, n2);
            out.println(pl);
            int q = 1;
            while (q <= 9) {
                pl.xod(q);
                out.println(pl);
                if (pl.win(q) == 1) {
                    q = 10;
                    out.println("Победил игрок " + n1 + "!");
                    out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                    f = in.nextLine();
                } else if (pl.win(q) == 2) {
                    q = 10;
                    out.println("Победил игрок " + n2 + "!");
                    out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                    f = in.nextLine();
                } else {
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
    String Name1;
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
    public String cheyxod(int q){
        if (q%2==0){
            return "Ход игрока " + Name2;
        }
        else
            return "Ход игрока " + Name1;
    }
    public boolean draw(int q){
        return q == 10;
    }
    public void xod(int q) {
            System.out.println();
            System.out.println(cheyxod(q));
            System.out.println("Введите координаты через enter(Без лишних пробелов)");
            String x1 = in.nextLine();
            String y1 = in.nextLine();
            while (x1.length() != 1 || y1.length() != 1 || !('0' < x1.charAt(0) && x1.charAt(0) < '4' && '0' < y1.charAt(0) && y1.charAt(0) < '4')){
                System.out.println("Ошибка ввода(проверьте на наличие лишних пробелов)");
                x1 = in.nextLine();
                y1 = in.nextLine();
            }
            int x = x1.charAt(0) - '0';
            int y = y1.charAt(0) - '0';
            while (!(0 < x && x < 4 && 0 < y && y < 4) || !pole[x - 1][y - 1].equals("-")) {
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
            if (q % 2 == 0) {
                pole[x - 1][y - 1] = this.Name2;
            } else {
                pole[x - 1][y - 1] = this.Name1;
            }
    }


    public int win(int e){
        String q = Name1 + Name1 + Name1;
        String w = Name2 + Name2 + Name2;
        if ((pole[0][0] + pole[0][1] + pole[0][2]).equals(q) || (pole[1][0] + pole[1][1] + pole[1][2]).equals(q) ||
                (pole[2][0] + pole[2][1] + pole[2][2]).equals(q) || (pole[0][0] + pole[1][0] + pole[2][0]).equals(q) ||
                (pole[0][1] + pole[1][1] + pole[2][1]).equals(q) || (pole[0][2] + pole[1][2] + pole[2][2]).equals(q) ||
                (pole[0][0] + pole[1][1] + pole[2][2]).equals(q) || (pole[0][2] + pole[1][1] + pole[2][0]).equals(q)){
            return 1;
        }
        else {
            if ((pole[0][0] + pole[0][1] + pole[0][2]).equals(w) || (pole[1][0] + pole[1][1] + pole[1][2]).equals(w) ||
                    (pole[2][0] + pole[2][1] + pole[2][2]).equals(w) || (pole[0][0] + pole[1][0] + pole[2][0]).equals(w) ||
                    (pole[0][1] + pole[1][1] + pole[2][1]).equals(w) || (pole[0][2] + pole[1][2] + pole[2][2]).equals(w) ||
                    (pole[0][0] + pole[1][1] + pole[2][2]).equals(w) || (pole[0][2] + pole[1][1] + pole[2][0]).equals(w)) {
                return 2;
            }
            else
                return 0;
        }
    }
}
