package bowling.view;

import java.util.Scanner;

public class InsertView {

  private static final String MSG_INSERT_NAME = "플레이어 %d의 이름은(3 english letters)? : ";
  private static final String MSG_BALL_COUNT = "'s turn : ";
  private static final String MSG_HOW_MANY_PEOPLE = "How many people ? ";

  private static final Scanner scanner = new Scanner(System.in);

  public static String inputName(int playerCount) {
    System.out.printf(MSG_INSERT_NAME, playerCount);
    return scanner.nextLine();
  }

  public static int throwBall(String name) {
    System.out.print(name + MSG_BALL_COUNT);
    return scanner.nextInt();
  }

  public static int playerCount() {
    System.out.print(MSG_HOW_MANY_PEOPLE);
    return Integer.parseInt(scanner.nextLine());
  }
}
