package bowling.output;

import bowling.Frame;

import java.util.List;
import java.util.StringJoiner;

public class ResultView {
    private String name;

    public ResultView(String question) {
        name = question;
    }

    public void print(List<Frame> frames) {
        StringJoiner stringJoiner = new StringJoiner("  |  ", "| ", " |");
        stringJoiner.add("NAME");
        for (int i = 1; i <= 10; i++) {
            stringJoiner.add(String.format("%02d", i));
        }
        System.out.println(stringJoiner.toString());

        StringJoiner stringJoiner2 = new StringJoiner(" | ", "| ", " |");
        stringJoiner2.add(String.format("%5s",name));

        for (int i = 0; i < frames.size(); i++) {
            stringJoiner2.add(String.format("%4s", frames.get(i).score()));
        }

        System.out.println(stringJoiner2.toString());

    }
}
