package bowling.step2.view.input;

import bowling.step2.domain.Player;
import bowling.step2.dto.CountOfFallenPinsDTO;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "null 은 입력할 수 없습니다. 다시 입력해주세요.";
    private static final String EXCEPTION_MESSAGE = "올바른 입력 값이 아닙니다. 다시 입력해 주세요.";
    private static final String COUNT_OF_PLAYER_INPUT_MESSAGE = "How many people?";
    
    public static Player inputPlayer() {
        try {
            System.out.print("\n플레이어 이름은(3 english letters)?: ");
            return new Player(checkNull(SCANNER.nextLine()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return inputPlayer();
        }
    }
    
    public static CountOfFallenPinsDTO countOfFallenPins(int countOfFrame) {
        try {
            System.out.printf("\n%d프레임 투구 : ", countOfFrame);
            return new CountOfFallenPinsDTO(checkNull(SCANNER.nextLine()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return countOfFallenPins(countOfFrame);
        }
    }
    
    public static int inputCountOfPlayer() {
        try {
            System.out.println(COUNT_OF_PLAYER_INPUT_MESSAGE);
            return inputCountOfPlayer(SCANNER.nextLine());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return inputCountOfPlayer();
        }
        
    }
    
    public static int inputCountOfPlayer(String countOfPlayer) {
        return Integer.parseInt(checkCountOfPlayerForm(checkNull(countOfPlayer)));
    }
    
    private static String checkCountOfPlayerForm(final String countOfPlayer) {
        final Matcher matcher = Pattern.compile("[1-9]").matcher(countOfPlayer);
        if (!matcher.matches()) {
            throw new IllegalArgumentException(EXCEPTION_MESSAGE);
        }
        
        return countOfPlayer;
    }
    
    private static String checkNull(String input) {
        if (input == null) {
            throw new NullPointerException(NULL_POINTER_EXCEPTION_MESSAGE);
        }
        
        return input;
    }
}
