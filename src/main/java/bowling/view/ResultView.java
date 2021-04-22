package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Name;

import java.util.List;
import java.util.stream.IntStream;

public class ResultView {
    private static final String NAME = "NAME";
    private static final String BAR = "|";

    public void printResult(Name name, List<Frame> frames) {
        printScoreBoard(frames.size());
        System.out.print(BAR);

        System.out.print(addBlankForName(name.name()));
        frames.stream()
                .map(Frame::toString)
                .forEach((frame) -> System.out.print(BAR + frame));

        System.out.println(BAR);
    }


    private void printScoreBoard(int framesSize) {
        System.out.print(BAR);

        System.out.print(addBlankForName(NAME));
        IntStream.range(0, framesSize)
                .forEach((i) -> System.out.printf(BAR + "  %02d  ", i + 1));

        System.out.println(BAR);
    }

    private String addBlankForName(String name) {
        if (name.equals(NAME)) {
            return " " + NAME + " ";
        }

        return "  " + name + " ";
    }
}
