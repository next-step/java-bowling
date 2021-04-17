package bowling.view;

import bowling.domain.BawlingGame;
import bowling.domain.Player;
import bowling.domain.frame.FinalFrame;
import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import java.util.List;
import java.util.stream.IntStream;
import org.apache.commons.lang3.StringUtils;

public class ResultView {

  private static final String HEADER = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
  private static final String DIVIDER = "|";


  private void printHeader() {
    System.out.println(HEADER);
  }

  private String printText(String text) {
    String str = StringUtils.center(text, 6);
    str += DIVIDER;
    return str;
  }

  public void printInit(Player player, BawlingGame bawlingGame) {
    printHeader();

    System.out.print(DIVIDER);
    System.out.print(printText(player.getName()));

    printFrame(bawlingGame.getFrames().getFrames());

  }
  public void printFrame(List<Frame> frames) {
    int bound = frames.size();
    StringBuilder builder = new StringBuilder();
    IntStream.range(0, bound)
        .mapToObj(i -> printDetailFrames(i, frames))
        .forEach(builder::append);
    System.out.print(builder);

    printRemainDevider(frames.size());

  }

  private void printRemainDevider(int size) {
    for (int i = size; i< 10; i++) {
      System.out.print(printText(""));
    }
  }

  private String printDetailFrames(int index, List<Frame> frames) {
    if (!(index == 9)&& !(frames.get(index) instanceof FinalFrame)) {
      return printText(printNormalFrame(frames.get(index)));
    }
    return printText(printFinalFrame(frames.get(index)));
  }

  private static String printNormalFrame(Frame frame) {
    NormalFrame normalFrame = (NormalFrame) frame;
    return normalFrame.getState().getString();
  }

  private static String printFinalFrame(Frame frame) {
    FinalFrame finalFrame = (FinalFrame) frame;
    return finalFrame.getState().getString();
  }



}
