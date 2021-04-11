package bowling;

import java.util.Scanner;

public class InputView {

    private final Scanner scanner = new Scanner(System.in);

    public String name() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine();
    }

    public int pinNumber(int thisFrame) {
        System.out.print(thisFrame + "프레임 투구 : ");
        return Integer.parseInt(scanner.nextLine());
    }
}
