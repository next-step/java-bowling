package bowling.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public InputView(Scanner scanner) {
        this.scanner = scanner;
    }

    public String inputPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?");
        return scanner.nextLine();
    }

    public int inputFramePinCount(int indexOfFrame) {
        System.out.println();
        System.out.println(indexOfFrame + " 프레임 투구");
        int pinCount = scanner.nextInt();
        scanner.nextLine();
        return pinCount;
    }
}
