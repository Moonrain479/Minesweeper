import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Number of Columns");
        int columns = sc.nextInt();
        System.out.println("Enter Number of Lines");
        int lines = sc.nextInt();
        System.out.println("Size of Field is: " + columns + "x" + lines);
        int[][] field = new int[columns][lines];
        String[][] game = new String[columns][lines];
        int NumberOfTiles = columns * lines;
        int NumberOfMines = (int) (NumberOfTiles * 0.25f);
        //Mines[] mines = new Mines[NumberOfMines];
        Random rand = new Random();
        for(int i = 0; i < NumberOfMines; i++){
            //Mines mine = new Mines();
            int column = rand.nextInt(columns);
            int line = rand.nextInt(lines);
            while(field[column][line] == 9){
                column = rand.nextInt(columns);
                line = rand.nextInt(lines);
            }
            //mine.setColumn(column);
            //mine.setLine(line);
            field[column][line] = 9;
            //mines[i]= mine;
        }

        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                if (field[j][i] != 9) {
                    int temp = 0;
                    for (int k = i - 1; k < i + 2; k++) {
                        for (int l = j - 1; l < j + 2; l++) {
                            try {
                                if (field[l][k] == 9) {
                                    temp += 1;
                                }
                            }catch (ArrayIndexOutOfBoundsException e){
                                temp += 0;
                            }

                        }
                    }
                    field[j][i] = temp;
                }
            }
        }

        /*for (int i = 0; i < mines.length; i++) {
            System.out.println(mines[i].getColumn());
            System.out.println(mines[i].getLine());
        }*/
        for (int i = 0; i < lines; i++) {
            System.out.print("\n");
            System.out.print(i + "\t");
            for (int j = 0; j < columns; j++) {
                System.out.print(field[j][i]+ " ");
            }
        }


        for (int i = 0; i < lines; i++) {
            for (int j = 0; j < columns; j++) {
                game[j][i]="[ ]";
            }
        }
        boolean gameOnGoing = true;

        while (gameOnGoing){
            for (int i = 0; i < columns; i++) {
                System.out.print("\t " + i );
            }
            for (int i = 0; i < lines; i++) {
                System.out.print("\n");
                System.out.print(i + "\t");
                for (int j = 0; j < columns; j++) {
                    System.out.print(game[j][i]+ " ");
                }
            }

            System.out.println("\n Enter Column");
            int columnsInput = sc.nextInt();
            System.out.println("Enter Line");
            int linesInput = sc.nextInt();

            if(field[columnsInput][linesInput] == 9){
               System.out.println("Game Over");
               gameOnGoing = false;
            }
            else {
                game[columnsInput][linesInput] = "["+field[columnsInput][linesInput]+"]";
                if (field[columnsInput][linesInput] == 0) {
                    boolean open = true;
                    while(open) {
                        for (int i = 0; i < lines; i++) {
                            for (int j = 0; j < columns; j++) {
                                if (game[j][i].equals("[0]")) {
                                    for (int k = i - 1; k < i + 2; k++) {
                                        for (int l = j - 1; l < j + 2; l++) {
                                            try {
                                                game[l][k] = "[" + field[l][k] + "]";
                                                if(!game[k][l].equals("[]")) {
                                                    open = false;
                                                }
                                            } catch (ArrayIndexOutOfBoundsException e) {

                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static void openAllAround(int column, int line, String[][] game, int[][] field){

    }
}