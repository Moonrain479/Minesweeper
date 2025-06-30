import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);            //Initial setup
        System.out.println("Enter Number of Columns");
        int columns = sc.nextInt();
        System.out.println("Enter Number of Lines");
        int lines = sc.nextInt();
        System.out.println("Size of Field is: " + columns + "x" + lines);

        int[][] field = new int[columns][lines];        //Matrix for Solution
        String[][] game = new String[columns][lines];   //Matrix with actual gameplaz
        int NumberOfTiles = columns * lines;
        int NumberOfMines = (int) (NumberOfTiles * 0.25f);
        //Mines[] mines = new Mines[NumberOfMines];     //Array of all Mines with their Coordinates(not needed for Gameplay)

        Random rand = new Random();                     //Set Mines on random tiles
        for(int i = 0; i < NumberOfMines; i++){
            int column = rand.nextInt(columns);
            int line = rand.nextInt(lines);
            while(field[column][line] == 9){            //reroll Mine if there already is one
                column = rand.nextInt(columns);
                line = rand.nextInt(lines);
            }
            field[column][line] = 9;
            //Mines mine = new Mines();                 //Initialize new Mines
            //mine.setColumn(column);                   //stores Coordinates of all Mines
            //mine.setLine(line);
            //mines[i]= mine;
        }

        for (int i = 0; i < lines; i++) {               //calculates for every Tile how many Mines are around it
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
                            }

                        }
                    }
                    field[j][i] = temp;                 //sets the Number of Mines around it on tile
                }
            }
        }

        /*for (int i = 0; i < mines.length; i++) {      //prints all Coordinates of Mines
            System.out.println(mines[i].getColumn());
            System.out.println(mines[i].getLine());
        }*/

        for (int i = 0; i < lines; i++) {               //prints solution
            System.out.print("\n");
            System.out.print(i + "\t");
            for (int j = 0; j < columns; j++) {
                System.out.print(field[j][i]+ " ");
            }
        }

        for (int i = 0; i < lines; i++) {               //Fill game field with [ ] as tiles
            for (int j = 0; j < columns; j++) {
                game[j][i]="[ ]";
            }
        }

        boolean gameOnGoing = true;
        while (gameOnGoing){                            //Gameloop
            for (int i = 0; i < columns; i++) {         //prints gamefield
                System.out.print("\t " + i );
            }
            for (int i = 0; i < lines; i++) {
                System.out.print("\n");
                System.out.print(i + "\t");
                for (int j = 0; j < columns; j++) {
                    System.out.print(game[j][i]+ " ");
                }
            }

            System.out.println("\n Enter o for open or x for mine");    //Asks for gameinput
            String OpenOrMine = sc.next();
            System.out.println("Enter Column");
            int columnsInput = sc.nextInt();
            System.out.println("Enter Line");
            int linesInput = sc.nextInt();

            if(OpenOrMine.equals("x")){                     //sets x if Player believes there is a mine
                game[columnsInput][linesInput] = "[x]";
            }
            else if(field[columnsInput][linesInput] == 9){  //game is over when Mine is opened
               System.out.println("Game Over");
               gameOnGoing = false;
            }
            else {                                          //puts Number of Mines, which are around the opened tile in gamefield
                game[columnsInput][linesInput] = "["+field[columnsInput][linesInput]+"]";
                if (field[columnsInput][linesInput] == 0) {
                    openAllAround(columns,lines,game,field);
                }
            }
        }
    }

    //efficient lol
    public static void openAllAround(int column, int line, String[][] game, int[][] field) { //opens all tilesaround 0
        boolean open = true;
        while (open) {
            for (int i = 0; i < line; i++) {
                for (int j = 0; j < column; j++) {
                    if (game[j][i].equals("[0]")) {
                        for (int k = i - 1; k < i + 2; k++) {
                            for (int l = j - 1; l < j + 2; l++) {
                                try {
                                    game[l][k] = "[" + field[l][k] + "]";
                                    if (!game[k][l].equals("[]")) {
                                        open = false;
                                    }
                                } catch (ArrayIndexOutOfBoundsException e) {

                                }
                            }
                        }
                    }
                }
            }
            for (int i = line - 1; i > 0; i--) {
                for (int j = column - 1; j > 0; j--) {
                    if (game[j][i].equals("[0]")) {
                        for (int k = i + 1; k > i - 2; k--) {
                            for (int l = j + 1; l > j - 2; l--) {
                                try {
                                    game[l][k] = "[" + field[l][k] + "]";
                                    if (!game[k][l].equals("[]")) {
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