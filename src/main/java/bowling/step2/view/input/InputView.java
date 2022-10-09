package bowling.step2.view.input;

import bowling.step2.domain.Player;

import java.util.Scanner;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "null 은 입력할 수 없습니다. 다시 입력해주세요.";
    
    public static Player inputPlayer() {
        try {
            System.out.print("플레이어 이름은(3 english letters)?: ");
            return new Player(checkNull(SCANNER.nextLine()));
        }catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputPlayer();
        }
    }
    
    private static String checkNull(String input) {
        if (input == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        
        return input;
    }
}
