package bowling.view;

import java.util.Scanner;

public class InputView {

    private Scanner scanner;

    public InputView() {
        this.scanner = new Scanner(System.in);
    }

    public String inputPlayerName() {
        System.out.print("플레이어 이름은(3 english letters)? : ");
        return scanner.nextLine();
    }

    public String inputFrameShoot(String frameIndex) {
        System.out.printf("%s 프레임 투구 : ", frameIndex);
        return scanner.nextLine();
    }
}
