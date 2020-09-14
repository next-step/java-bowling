package bowling.domain;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class ScoreBoard {

    private final List<Frame> frames;

    public static ScoreBoard from(String userName) {
        List<Frame> result = new LinkedList<>();
        Frame head = HeadFrame.from(userName);
        result.add(head);

        Frame next = head.makeNextFrame();
        while(true) {
            if(next.isFinalFrame()){
                result.add(next);
                break;
            }
            result.add(next);
            next = next.makeNextFrame();
        }

        return new ScoreBoard(result);
    }

    private ScoreBoard(List<Frame> frames) {
        this.frames = frames;
    }

    public String printableStatus() {
        String titleLine = frames.stream()
                .map(Frame::printableTitle)
                .collect(Collectors.joining());
        String valueLine = frames.stream()
                .map(Frame::printableValue)
                .collect(Collectors.joining());

        return titleLine + "\n" + valueLine;
    }
}
