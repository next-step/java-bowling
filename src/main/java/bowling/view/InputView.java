package bowling.view;

import java.util.Scanner;

public class InputView {
  private final Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public int initPlayersCount() {
    System.out.print("How many people?");
    return scanner.nextInt();
  }

  public String setupPlayer(int playerCount) {
    System.out.print(String.format("플레이어 %d의 이름은(3 english letters)?: ", playerCount));
    return scanner.next();
  }

  public int setupPins(int round) {
    System.out.print(String.format("%d프레임 투구 : ", round));
    return scanner.nextInt();
  }
}
