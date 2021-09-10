package bowling.view;

import java.util.Scanner;

public class InputView {
    private final Scanner scanner = new Scanner(System.in);

    public String extractUsername() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int extractFrameResult(int frameIndex) {
        System.out.println((frameIndex + 1) + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
