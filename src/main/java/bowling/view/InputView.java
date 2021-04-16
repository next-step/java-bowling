package bowling.view;

import java.util.Scanner;

public class InputView {

    private static Scanner scanner = new Scanner(System.in);

    //플레이어 이름은(3 english letters)?: PJS
    public static String inputPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?:");
        return scanner.nextLine();
    }

    //1프레임 투구 : 10
    public static int inputPoint(int round) {
        System.out.print(round + "프레임 투구 : ");
        return scanner.nextInt();
    }
}
