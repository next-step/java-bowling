package bowling.View;

import java.util.Scanner;

public class InputView {
    private Scanner scanner = new Scanner(System.in);

    public String requestPlayerName() {
        System.out.println("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int requestPitch(int frameCount) {
        System.out.print(frameCount + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
