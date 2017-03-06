package xo_game;

import java.util.Random;
import java.util.Scanner;

/**
 * Java. Console game "Cross-zero"
 * @author Aleksey Malyshev
 * @version 1.0.0 dated March 06, 2017
 */

public class GameXO {

    public static final char EMPTY_DOT = '*';
    public static final char AI_DOT = 'O';
    public static final char PLAYER_DOT = 'X';

    public static int x;
    public static int y;

    public static Scanner sc = new Scanner(System.in);
    public static Random rnd = new Random();

    public static final int SIZE_X = 3;
    public static final int SIZE_Y = 3;
    public static final char[][] FIELD = new char[SIZE_X][SIZE_Y];

    public static void main (String[] args){

        initMap();
        printMap();

        while (true){

            movePlayer();
            printMap();

            if (checkWin(PLAYER_DOT)){
                System.out.println ("Выиграл игрок!!!");
                break;
            }

            if (checkFieldFull()){
                System.out.println ("Ничья!!!");
                break;
            }

            moveAI();
            printMap();

            if (checkWin(AI_DOT)){
                System.out.println ("Выиграл компьютер!!!");
                break;
            }

            if (checkFieldFull()){
                System.out.println ("Ничья!!!");
                break;
            }



        }

    }

    public static void initMap(){
        for (int i = 0; i < SIZE_X ; i++) {
            for (int j = 0; j < SIZE_Y ; j++) {
                FIELD [i][j] = EMPTY_DOT;
            }
        }
    }

    public static void printMap(){

        for (int i = 0; i <= SIZE_X; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < SIZE_X ; i++) {
            System.out.print(i + 1 + " ");

            for (int j = 0; j < SIZE_Y ; j++) {
                System.out.print ( FIELD [i][j] + " ");
            }
            System.out.println();
        }

    }

    public static void movePlayer(){

        try {

            do {
                System.out.println("Ввидите два числа от 1 до " + SIZE_X + " через пробел чтобы сделать ход!!!");
                x = sc.nextInt() - 1;
                y = sc.nextInt() - 1;
            } while (!isBorderField() || !isEmptyCell());

        }catch (Exception e){
            System.out.println("Ошибка ввода!!");
        }
            FIELD[x][y] = PLAYER_DOT;
    }

    public static void moveAI(){

        do{
            x = rnd.nextInt(SIZE_X);
            y = rnd.nextInt(SIZE_Y);
        }while (!isEmptyCell());
            FIELD[x][y] = AI_DOT;

        System.out.println("");

    }

    public static boolean isEmptyCell (){
        return FIELD [x][y] == EMPTY_DOT;
    }

    public static boolean isBorderField (){
        return x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y;
    }

    public static boolean checkWin (char c){
        return false;
    }

    public static boolean checkFieldFull (){

        for (int i = 0; i < SIZE_X ; i++) {
            for (int j = 0; j < SIZE_Y ; j++) {
                if (FIELD [i][j] == EMPTY_DOT){
                    return false;
                }
            }
        }
        return true;
    }


}
