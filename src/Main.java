import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        playPuzzle();

    }

    public static void playPuzzle() {
        Random random = new Random();

        int[][] puzzleArray = generatePuzzleArray(random);

        System.out.println("재미있는 15퍼즐!");

        int turn = 1;

        while (true) {
            System.out.println("Turn: " + turn);
            printPuzzleArray(puzzleArray);

            if (SwitchPuzzle(puzzleArray)) {
                turn++;
            }

            if (isPuzzleSolved(puzzleArray)) {
                System.out.println("축하합니다! " + (turn - 1) + "턴만에 퍼즐을 완성했습니다!");
                break;
            }
        }
    }

    public static int[][] generatePuzzleArray(Random random) {

        int i = 0, j = 0;
        int[][] puzzleArray = new int[4][4];
        ArrayList<Integer> list = new ArrayList<Integer>();

        for (i = 0; i < 16; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                puzzleArray[i][j] = list.remove(0);
            }
        }

        return puzzleArray;
    }

    public static void printPuzzleArray(int[][] puzzleArray) {

        int i = 0, j = 0;

        for (i = 0; i < 4; i++) {
            for (j = 0; j < 4; j++) {
                System.out.print("[");
                if (puzzleArray[i][j] == 0)
                    System.out.print("  ");
                else if (puzzleArray[i][j] < 10)
                    System.out.print(" " + puzzleArray[i][j]);
                else
                    System.out.print(puzzleArray[i][j]);
                System.out.print("]");
            }
            System.out.println("");
        }

    }

    public static boolean SwitchPuzzle(int[][] puzzleArray) {
        int x = 0, y = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (puzzleArray[i][j] == 0) {
                    x = i;
                    y = j;
                }
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.printf("숫자 입력> ");
        int number = scanner.nextInt();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (puzzleArray[i][j] == number) {
                    if (Math.abs(x - i) + Math.abs(y - j) == 1) {
                        puzzleArray[x][y] = puzzleArray[i][j];
                        puzzleArray[i][j] = 0;
                        return true;
                    }
                }
            }
        }

        System.out.println("유효하지 않은 입력입니다. 0 주변의 숫자를 입력해주세요.");
        return false;
    }

    public static boolean isPuzzleSolved(int[][] puzzleArray) {
        int[][] answerArray = {
                { 1, 2, 3, 4 },
                { 5, 6, 7, 8 },
                { 9, 10, 11, 12 },
                { 13, 14, 15, 0 }
        };
        int check = 0;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (answerArray[i][j] == puzzleArray[i][j]) {
                    check++;
                }
            }
        }
        if (check != 16) // 퍼즐의 모든 칸이 정답과 일치해야 함
            return false;
        return true;
    }
}