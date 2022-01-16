package bowling.view;

import bowling.domain.Game;

import java.util.Scanner;

public class Input {
    static Scanner SCANNER = new Scanner(System.in);

    public static String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return SCANNER.nextLine();
    }

    public static int inputScore(Game game) {
        System.out.println();
        System.out.print(game.getFrameNum() + " 프레임 투구 : ");
        return SCANNER.nextInt();
    }
}
