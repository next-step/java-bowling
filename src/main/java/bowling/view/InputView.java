package bowling.view;

import java.util.Scanner;

public class InputView {

    private static final Scanner scanner = new Scanner(System.in);

    public static String inputName(int order) {
        System.out.print(String.format("플레이어 %s 의 이름은(3 english letters)?:", order));
        return scanner.nextLine();
    }

    public static int inputBowl(String playerName) {
        System.out.print(String.format("%s `s 프레임 투구 : ", playerName));
        return Integer.parseInt(scanner.nextLine());
    }

    public static int inputPeopleCount() {
        System.out.print("How many people? ");
        return Integer.parseInt(scanner.nextLine());
    }
}
