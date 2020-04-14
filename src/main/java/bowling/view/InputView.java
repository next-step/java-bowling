package bowling.view;

import java.util.Scanner;

public class InputView {
    private static final String NAME_QUESTION = "플레이어 이름은(3 english letters)? : ";
    private static final String PIN_QUESTION = "프레임 투구 : ";
    private Scanner scanner;

    public String askName() {
        System.out.print(NAME_QUESTION);
        scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public int askNumberOfPin(int currentFrame){
        System.out.println(currentFrame + PIN_QUESTION);
        scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
