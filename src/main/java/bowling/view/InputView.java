package bowling.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputView {

  private final Scanner scanner;

  public InputView() {
    scanner = new Scanner(System.in);
  }

  public int requestNumberOfPlayers() {
    System.out.print("How many people? ");

    return scanner.nextInt();
  }

  public List<String> requestNames(int numberOfPlayers) {
    List<String> names = new ArrayList<>();
    for (int i = 0; i < numberOfPlayers; i++) {
      System.out.print("플레이어 이름은(3 english letters)?: ");
      names.add(scanner.next());
    }

    return names;
  }

  public int requestPins(int number) {
    System.out.printf("\n%d프레임 투구 : ", number);
    return scanner.nextInt();
  }

  public int requestPinsOf(String name) {
    System.out.printf("\n%s's turn : ", name);
    return scanner.nextInt();
  }
}
