package bowling.view;

import bowling.domain.Frames;
import bowling.domain.Player;
import bowling.domain.frame.Frame;
import bowling.domain.state.State;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

public class ResultView {

  private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String DIVIDER = "|";

  public void printResult(Player player, Frames frames) {
    printHeader();

    System.out.print(DIVIDER);
    printText(player.getName());

    printFrames(frames.getFrames());
    System.out.print(System.lineSeparator());
  }

  private void printHeader() {
    System.out.println(HEADER);
  }

  private void printText(String str) {
    System.out.print(StringUtils.center(str, 6));
    System.out.print(DIVIDER);
  }


  private void printFrames(List<Frame> frames) {
    String str = "";
    int lastIndex = frames.size() - 1;
    for (int i = 0; i <= lastIndex; i++) {
      State state = frames.get(i).getState();
      str += addState(state, i, lastIndex);

      if (state.isEnd() || i == lastIndex) {
        printText(str);
        str = "";
      }
    }

    printRemainDivider(10 - frames.get(lastIndex).getPlayCount());

  }

  private void printRemainDivider(int size) {
    for (int i = 0; i < size; i++) {
      printText("");
    }
  }

  private String addState(State state, int currentIndex, int lastIndex) {
    String str = state.getString();
    if (!state.isEnd() && currentIndex != lastIndex) {
      str += DIVIDER;
    }
    return str;
  }
}
