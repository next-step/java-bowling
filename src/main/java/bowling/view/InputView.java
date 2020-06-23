package bowling.view;

import java.util.Scanner;

public class InputView {

    private final static Scanner scanner = new Scanner(System.in);

    public String requestPlayer() {
        System.out.print("플레이어 이름은(3 english letters)?: ");
        return scanner.nextLine().trim();
    }

    public int requestDownPin(int framePosition) {
        System.out.print(String.format("%d프레임 투구 :", framePosition + 1));
        return Integer.parseInt(scanner.nextLine().trim());
    }
}
