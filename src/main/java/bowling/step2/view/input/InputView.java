package bowling.step2.view.input;

import bowling.step2.domain.Player;
import bowling.step2.domain.PlayersDTO;
import bowling.step2.dto.CountOfFallenPinsDTO;
import bowling.step2.dto.PlayerDTO;

import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class InputView {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String NULL_POINTER_EXCEPTION_MESSAGE = "null 은 입력할 수 없습니다. 다시 입력해주세요.";
    private static final String EXCEPTION_MESSAGE = "올바른 입력 값이 아닙니다. 다시 입력해 주세요.";
    private static final String COUNT_OF_PLAYER_INPUT_MESSAGE = "How many people?";
    private static final String PLAYER_INPUT_FORM = "\n플레이어 %d의 이름은(3 english letters)?: ";
    private static final String COUNT_OF_FALLEN_PINS_INPUT_FORM = "\n%s's turn : ";
    private static final String COUNT_OF_PLAYER_INPUT_FORM = "[1-9]";
    
    public static int inputCountOfPlayer() {
        try {
            System.out.println(COUNT_OF_PLAYER_INPUT_MESSAGE);
            return inputCountOfPlayer(SCANNER.nextLine());
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return inputCountOfPlayer();
        }
    }
    
    public static PlayersDTO inputPlayers(final int countOfPlayer) {
        return new PlayersDTO(getPlayers(countOfPlayer));
    }
    
    private static List<Player> getPlayers(final int countOfPlayer) {
        return IntStream.range(0, countOfPlayer)
                .mapToObj(InputView::inputPlayer)
                .collect(Collectors.toList());
    }
    
    private static Player inputPlayer(int orderNumberOfPlayer) {
        try {
            System.out.printf(PLAYER_INPUT_FORM, orderNumberOfPlayer + 1);
            return new Player(checkNull(SCANNER.nextLine()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return inputPlayer(orderNumberOfPlayer);
        }
    }
    
    public static CountOfFallenPinsDTO countOfFallenPins(PlayerDTO playerDTO) {
        try {
            System.out.printf(COUNT_OF_FALLEN_PINS_INPUT_FORM, playerDTO.getPlayerName());
            return new CountOfFallenPinsDTO(checkNull(SCANNER.nextLine()));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            return countOfFallenPins(playerDTO);
        }
    }
    
    public static int inputCountOfPlayer(String countOfPlayer) {
        return Integer.parseInt(checkCountOfPlayerForm(checkNull(countOfPlayer)));
    }
    
    private static String checkCountOfPlayerForm(final String countOfPlayer) {
        final Matcher matcher = Pattern.compile(COUNT_OF_PLAYER_INPUT_FORM).matcher(countOfPlayer);
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
