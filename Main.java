import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

public class Main {
    public static Scanner in = new Scanner(System.in);
    public static PrintStream out = System.out;

    public static void main(String[] args) throws IOException {
        out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
        int f = in.nextInt();
        String none = in.nextLine();
        while (f == 1) {
            out.println("Введите имена игроков через enter (состоящие из одной буквы):");
            String n1 = in.nextLine();
            String n2 = in.nextLine();
            while (n1.equals(n2)) {
                out.println("Имена не могут быть одинаковыми, введите новые");
                n1 = in.nextLine();
                n2 = in.nextLine();
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
                    f = in.nextInt();
                    none = in.nextLine();
                } else if (pl.win(q) == 2) {
                    q = 10;
                    out.println("Победил игрок " + n2 + "!");
                    out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                    f = in.nextInt();
                    none = in.nextLine();
                } else {
                    q++;
                    if (pl.draw(q)){
                        out.println("Ходы кончились, никто не выиграл, ничья!");
                        out.println("Выберите опцию:" + "\n" + "1 - начать новую игру" + "\n" + "0 - не начинать новую игру");
                        f = in.nextInt();
                        none = in.nextLine();
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
    public void newgame(){
        for (int i = 0; i<3; i++){
            for (int j = 0; j<3; j++){
                pole[i][j] = "-";
            }
        }
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
            System.out.println("Введите координаты через пробел");
            int x = in.nextInt();
            int y = in.nextInt();
            while (!(0 < x && x < 4 && 0 < y && y < 4) || !pole[x - 1][y - 1].equals("-")) {
                if (!(0 < x && x < 4 && 0 < y && y < 4)) {
                    System.out.println();
                    System.out.println("Введены неверные координаты!");
                    System.out.println(cheyxod(q));
                    System.out.println("Введите координаты через пробел");
                    x = in.nextInt();
                    y = in.nextInt();
                }
                else{
                    System.out.println();
                    System.out.println("Это поле занято, введи другие координаты!");
                    System.out.println(cheyxod(q));
                    System.out.println("Введите координаты через пробел");
                    x = in.nextInt();
                    y = in.nextInt();
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
