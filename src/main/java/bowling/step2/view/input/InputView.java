package bowling.step2.view.input;

import bowling.step2.domain.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    
    public static Player inputPlayer() {
        try {
            System.out.print("플레이어 이름은(3 english letters)?: ");
            return new Player(SCANNER.nextLine());
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayer();
        }
    }
}
