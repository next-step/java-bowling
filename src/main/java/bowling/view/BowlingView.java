package bowling.view;

import static bowling.model.Symbols.*;

import bowling.model.BowlingGame;
import bowling.model.FrameDTO;
import bowling.model.Score;
import java.util.List;

public class BowlingView {

  private final static String SCORE_BOARD_UPPER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private final static int MAXLENGTH_PER_FRAME = 6;

  public static void printPlayerNameInputMsg() {
    System.out.print("플레이어 이름은(3 english letters)?: ");
  }

  public static void printKnockDownNumInputMsg(int currentFrameNumber) {
    System.out.print(getKnockDownNumInputMsg(currentFrameNumber) + "프레임 투구 : ");
  }

  private static String getKnockDownNumInputMsg(int currentFrameNumber) {
    if (10 < currentFrameNumber) {
      return "보너스 ";
    }

    return String.valueOf(currentFrameNumber);
  }

  public static void printBonusFrameInputMsg() {
    System.out.print("보너스 프레임 투구 : ");
  }

  public static void printScoreBoard(String playerName, List<FrameDTO> framesDTO) {
    System.out.println(getFrameMsgBy(playerName, framesDTO, framesDTO.size()));
  }

  private static String getFrameMsgBy(String playerName, List<FrameDTO> frameDTOs, int endpoint) {
    StringBuilder sb = new StringBuilder(SCORE_BOARD_UPPER).append(System.lineSeparator());

    sb.append(BAR)
        .append(wrappingWithSpaces(playerName))
        .append(BAR);

    frameDTOs.stream()
        .limit(BowlingGame.MAX_NUMBER_OF_FRAMES - 1)
        .forEach(frameDTO -> {
          sb.append(wrappingWithSpaces(frameDTO.getFrameResult()))
              .append(BAR);
        });

    if (10 <= endpoint) {
      sb.append(getLastFrameMsg(frameDTOs))
          .append(BAR);
    }

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

  public static void printScores(List<Score> scores) {
    StringBuilder sb = new StringBuilder();

    sb.append(BAR)
        .append(wrappingWithSpaces(BLANK.toString()))
        .append(BAR);

    Score scoreHolder = new Score(0);

    scores.forEach(score -> {
      scoreHolder.add(score);

      sb.append(wrappingWithSpaces(scoreHolder.toString()))
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
