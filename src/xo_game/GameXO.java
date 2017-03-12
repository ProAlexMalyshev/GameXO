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

    public static final int LEN = 3;

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


                 do {
                     System.out.println("Ввидите два числа от 1 до " + SIZE_X + " через пробел чтобы сделать ход!!!");
                     x = sc.nextInt() - 1;
                     y = sc.nextInt() - 1;
                 } while (!isBorderField(x, y) || !isEmptyCell(x , y));

                 FIELD[x][y] = PLAYER_DOT;

    }


    public static void moveAI(){
        if (aiWin()) return;
        if (lockPlayer()) return;

        do{
            x = rnd.nextInt(SIZE_X);
            y = rnd.nextInt(SIZE_Y);
        }while (!isEmptyCell(x, y));
            FIELD[x][y] = AI_DOT;

        System.out.println("");
    }

    public static boolean aiWin(){

        for (int i = 0; i <SIZE_X ; i++) {
            for (int j = 0; j <SIZE_Y ; j++) {
                if (isEmptyCell(i, j)){
                    FIELD [i][j] = AI_DOT;
                    if (checkWin(AI_DOT)) {
                        return true;
                    }
                    FIELD [i][j] = EMPTY_DOT;
                }
            }
        }
       return false;
    }

    public static boolean lockPlayer(){

        for (int i = 0; i <SIZE_X ; i++) {
            for (int j = 0; j <SIZE_Y ; j++) {
                if (isEmptyCell(i, j)){
                    FIELD [i][j] = PLAYER_DOT;
                    if (checkWin(PLAYER_DOT)){
                        FIELD [i][j] = AI_DOT;
                        return true;
                    }
                    FIELD [i][j] = EMPTY_DOT;
                }
            }
        }
        return false;
    }

    public static boolean isEmptyCell (int x, int y){
        return FIELD [x][y] == EMPTY_DOT;
    }

    public static boolean isBorderField (int x, int y){
        return x >= 0 && x < SIZE_X && y >= 0 && y < SIZE_Y;
    }

    public static boolean checkWin (char c){

        for (int i = 0; i <SIZE_X ; i++) {
            for (int j = 0; j <SIZE_Y ; j++) {

                if (line(i, j, 1, 0, LEN, c)) return true;
                if (line(i, j, 1, 1, LEN, c)) return true;
                if (line(i, j, 0, 1, LEN, c)) return true;
                if (line(i, j, -1, 1, LEN, c)) return true;
                if (line(i, j, -1, 0, LEN, c)) return true;
                if (line(i, j, -1, -1, LEN, c)) return true;
                if (line(i, j, 0, -1, LEN, c)) return true;
                if (line(i, j, 1, -1, LEN, c)) return true;
            }
        }

        return false;
    }

    public static boolean line (int x, int y, int vx, int vy, int len, char c){

        int far_x = x + (len - 1)*vx;
        int far_y = y + (len - 1)*vy;
        if (!isBorderField(far_x, far_y)) return false;
        for (int i = 0; i < len ; i++) {
                if (FIELD [x + i * vx][y + i * vy] != c) return false;
        }
        return true;
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
