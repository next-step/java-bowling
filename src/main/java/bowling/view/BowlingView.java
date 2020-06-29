package bowling.view;

import bowling.model.BowlingGame;
import bowling.model.FrameDTO;
import java.util.List;

public class BowlingView {

  private final static String SCORE_BOARD_UPPER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private final static String BAR = "|";
  private final static String NEWLINE = System.lineSeparator();
  private final static int MAXLENGTH = 6;
  private final static String SPACE = " ";

  public static void printPlayerNameInputMsg() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
  }

  public static void printKnockDownNumInputMsg(int currentFrameNum) {
    System.out.print(currentFrameNum + "프레임 투구 : ");
  }

  public static void printBonusFrameInputMsg() {
    System.out.print("보너스 프레임 투구 : ");
  }

  public static void printScoreBoard(String playerName, List<FrameDTO> framesDTO) {
    System.out.println(getFrameMsgBy(playerName, framesDTO, framesDTO.size()));
  }

  public static void printScoreBoardWithBonus(String playerName, List<FrameDTO> frameDTOs) {
    StringBuilder sb = new StringBuilder();

    sb.append(getFrameMsgBy(playerName, frameDTOs, BowlingGame.MAX_NUMBER_OF_FRAMES - 1))
        .append(getLastFrameMsg(frameDTOs))
        .append(BAR);

    System.out.println(sb.toString());
  }

  private static String getFrameMsgBy(String playerName, List<FrameDTO> frameDTOs, int endpoint) {
    StringBuilder sb = new StringBuilder(SCORE_BOARD_UPPER).append(NEWLINE);

    sb.append(BAR)
        .append(wrappingWithSpaces(playerName))
        .append(BAR);

    frameDTOs.stream()
        .limit(endpoint)
        .forEach(frameDTO -> {
          sb.append(wrappingWithSpaces(frameDTO.toString()))
              .append(BAR);
        });

    return sb.toString();
  }

  private static String getLastFrameMsg(List<FrameDTO> frameDTOs) {
    StringBuilder sb = new StringBuilder(
        frameDTOs.get(BowlingGame.MAX_NUMBER_OF_FRAMES - 1).toString());

    frameDTOs.stream()
        .filter(FrameDTO::isBonus)
        .forEach(frameDTO -> {
          sb.append(BAR)
              .append(frameDTO.toString());
        });

    return wrappingWithSpaces(sb.toString());
  }

  private static String wrappingWithSpaces(String str) {
    StringBuilder sb = new StringBuilder();

    int space = MAXLENGTH - str.length();
    int leftSpace = space / 2;
    int rightSpace = space - leftSpace;

    for (int i = 0; i < leftSpace; i++) {
      sb.append(SPACE);
    }

    sb.append(str);

    for (int i = 0; i < rightSpace; i++) {
      sb.append(SPACE);
    }

    return sb.toString();
  }
}
