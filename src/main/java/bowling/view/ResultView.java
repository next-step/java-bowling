package bowling.view;

import bowling.domain.FrameResult;
import bowling.domain.GameInformation;
import bowling.domain.state.State;
import java.util.List;
import java.util.Map.Entry;
import java.util.stream.Collectors;
import org.apache.commons.lang3.StringUtils;

public class ResultView {

  private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String DIVIDER = "|";

  public void printResult2(GameInformation gameInformation) {
    printHeader();

    System.out.print(DIVIDER);
    printText(gameInformation.getPlayer().getName());

    printFrameResult(gameInformation.getFrameResult());
    System.out.print(System.lineSeparator());

  }

  private void printFrameResult(FrameResult frameResult) {
    for (Entry<Integer, List<State>> result : frameResult.getFrames().entrySet()) {
      printText(printStates(result.getValue()));
    }
  }

  private String printStates(List<State> states) {
    return states.stream()
        .map(State::getString)
        .collect(Collectors.joining(DIVIDER));
  }

  private void printHeader() {
    System.out.println(HEADER);
  }

  private void printText(String str) {
    System.out.print(StringUtils.center(str, 6));
    System.out.print(DIVIDER);
  }

}
