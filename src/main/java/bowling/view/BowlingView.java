package bowling.view;

import static bowling.model.Symbols.*;

import bowling.model.BowlingGame;
import bowling.model.FrameDTO;
import java.util.List;
import java.util.Optional;

public class BowlingView {

  private final static String SCORE_BOARD_UPPER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private final static int MAXLENGTH_PER_FRAME = 6;

  public static void printPlayerNameInputMsg() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
  }

  public static void printKnockDownNumInputMsg(int currentFrameNumber) {
    System.out.print(currentFrameNumber + "프레임 투구 : ");
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
    StringBuilder sb = new StringBuilder(SCORE_BOARD_UPPER).append(System.lineSeparator());

    sb.append(BAR)
        .append(wrappingWithSpaces(playerName))
        .append(BAR);

    frameDTOs.stream()
        .limit(endpoint)
        .forEach(frameDTO -> {
          sb.append(wrappingWithSpaces(frameDTO.getFrameResult()))
              .append(BAR);
        });

    return sb.toString();
  }

  private static String getLastFrameMsg(List<FrameDTO> frameDTOs) {
    StringBuilder sb = new StringBuilder(
        frameDTOs.get(BowlingGame.MAX_NUMBER_OF_FRAMES - 1).getFrameResult());

    frameDTOs.stream()
        .filter(FrameDTO::isBonusFrame)
        .forEach(frameDTO -> {
          sb.append(BAR)
              .append(frameDTO.getFrameResult());
        });

    return wrappingWithSpaces(sb.toString());
  }

  public static void printScores(List<Integer> scores) {
    StringBuilder sb = new StringBuilder();

    sb.append(BAR)
        .append(wrappingWithSpaces(BLANK.toString()))
        .append(BAR);

    int[] scoreHolder = new int[]{0};

    scores.forEach(score -> {
      scoreHolder[0] += score;

      sb.append(wrappingWithSpaces(String.valueOf(scoreHolder[0])))
          .append(BAR);
    });

    sb.append(System.lineSeparator());

    System.out.println(sb);
  }

  private static String wrappingWithSpaces(String str) {
    StringBuilder sb = new StringBuilder();

    if (str.equals("0")) {
      str = BLANK.toString();
    }

    int space = MAXLENGTH_PER_FRAME - str.length();
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
