import java.util.Random;
import java.util.Scanner;


public class Krestik2 {


    static void start() {
        char[][] field = getEmptyField();

        drawField(field);

        while (true) {

            doPlayerMove(field);

            if (isWin(field, 'X')) {
                System.out.println("Congratulations!!! Your are winner!!!");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw. Try again.");
                break;
            }

            doAIMove(field);
            if (isWin(field, 'O')) {
                System.out.println("Sorry!!! You are loser :(");
                break;
            }
            if (isDraw(field)) {
                System.out.println("This is draw. Try again.");
                break;
            }
        }
    }

    static boolean isDraw(char[][] field) {
        for (int h = 0; h < field.length; h++) {
            for (int v = 0; v < field.length; v++) {
                if (isEmptyCell(field, h, v)) {
                    return false;
                }
            }
        }
        return true;
    }


    static boolean isWin(char[][] field, char sign) {
        // Horizontal

        for (int i = 0; i < field.length; i++) { //проверка построчно
            for (int j = 0; j < field.length; j++) { // проверка столбцов
                if (field[i][j] != sign) {  // проверка одного эл-та
                    break; // если отличен то уходим на новую строку
                } else {
                    if (j == 2) {  // если равен то проверяем все ли одинаковые
                        return true;
                    }

                }

            }
        }


        // Vertical
        for (int i = 0; i < field.length; i++) { //проверка построчно
            for (int j = 0; j < field.length; j++) { // проверка столбцов
                if (field[j][i] != sign) {  // проверка одного эл-та
                    break; // если отличен то уходим на новую строку
                } else {
                    if (j == 2) {  // если равен то проверяем все ли одинаковые
                        return true;
                    }

                }
            }
        }

        // Diagonals
        for (int i = 0; i < field.length; i++) {
            if (field[i][i] != sign) {
                break;
            } else {
                if (i == 2) {
                    return true;
                }
            }
        }
        for (int i = 0; i < field.length; i++) {
            if (field[i][2 - i] != sign) {
                break;
            } else {
                if (i == 2) {
                    return true;
                }
            }
        }
        return false;
    }

        static void doAIMove ( char[][] field){
            Random random = new Random();
            int horizontal, vertical;

            do {
                horizontal = random.nextInt(field.length);
                vertical = random.nextInt(field.length);
            } while (isNotEmptyCell(field, horizontal, vertical));

            field[horizontal][vertical] = 'O';
            drawField(field);
        }

        static void doPlayerMove ( char[][] field){
            int horizontal, vertical;

            do {
                System.out.println("Your chance. Please enter coordinates...");
                horizontal = getCoordinate('H');
                vertical = getCoordinate('V');
            } while (isNotEmptyCell(field, horizontal, vertical));

            field[horizontal][vertical] = 'X';
            drawField(field);
        }

        static boolean isEmptyCell ( char[][] field, int horizontal, int vertical){
            return field[horizontal][vertical] == '-';
        }

        static boolean isNotEmptyCell ( char[][] field, int horizontal, int vertical){
            return !isEmptyCell(field, horizontal, vertical);
        }

        static int getCoordinate ( char position){
            Scanner scanner = new Scanner(System.in);
            int coordinate;

            do {
                System.out.printf("Please enter %s-coordinate [in range 1-3] ...%n", position);
                coordinate = scanner.nextInt() - 1;
            } while (coordinate < 0 || coordinate > 2);

            return coordinate;
        }

        static void drawField ( char[][] field){
            for (int i = 0; i < field.length; i++) {
                for (int j = 0; j < field.length; j++) {
                    System.out.print(field[i][j]);
                    System.out.print(" ");
                }
                System.out.println();
            }

            System.out.println();
        }

        static char[][] getEmptyField () {

            return new char[][]{
                    {'-', '-', '-'},
                    {'-', '-', '-'},
                    {'-', '-', '-'}
            };
        }

    }
