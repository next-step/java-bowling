package bowling;

import java.util.Scanner;

public class BowlingGameApplication {
    public static void main(String[] args) {
        Bowling bowling = new Bowling();

        System.out.print("플레이어 이름(3 english letters)? : ");
        Scanner scanner = new Scanner(System.in);
        String name = scanner.nextLine();
        System.out.println("| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |");
        System.out.println(String.format(
                "|  %s |      |      |      |      |      |      |      |      |      |      |",
                name
        ));

        while (!bowling.isGameEnd()) {
            System.out.print(String.format("%d프레임 투구 : ", bowling.getCurrentFrameNumber()));
            int fallenPins = scanner.nextInt();
            System.out.println(bowling.roll(fallenPins));
        }
    }
}
