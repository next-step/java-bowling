package bowling.view;

import bowling.domain.BowlingGame;
import bowling.domain.Player;
import bowling.domain.Players;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {
  private static final String PLAYER_COUNT_QUESTION = "How many people?";
  private static final String PLAYER_NAME_QUESTION = "플레이어 %d의 이름은?(3 english letters): ";
  private static final String FRAME_BOWLING_MESSAGE = "%s's turn : ";

  private Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public Players acceptPlayers() {
    int playerCount = acceptPlayerCount();

    List<String> playerNames = new ArrayList<>();

    for (int i = 1; i <= playerCount; i++) {
      playerNames.add(acceptPlayer(i));
    }

    return new Players(playerNames);
  }

  private int acceptPlayerCount() {
    printWithNewLine(PLAYER_COUNT_QUESTION);
    return scanner.nextInt();
  }

  private String acceptPlayer(int playerIndex) {
    System.out.println(String.format(PLAYER_NAME_QUESTION, playerIndex));
    return scanner.next();
  }

  public int inputPinCount(String name) {
    printWithNewLine(String.format(FRAME_BOWLING_MESSAGE, name));
    return scanner.nextInt();
  }

  private void printWithNewLine(String sentence) {
    System.out.println();
    System.out.println(sentence);
  }
}
