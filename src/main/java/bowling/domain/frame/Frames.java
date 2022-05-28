package bowling.domain.frame;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Frames {
    private static final String BASIC_SCORE_FORM = "  %s";
    private static final String NOT_EXIST_SCORE = "      |";
    private static final String FIRST_SPACE = "|      |";
    private static final String BAR = " ";
    private static final int LAST_FRAME_INDEX = 10;

    private final List<Frame> frames;
    private Frame current;

    public Frames() {
        this.frames = createFrames();
    }

    public int bowl(int pins) {
        current = current.bowl(pins);
        return current.number();
    }

    private List<Frame> createFrames() {
        List<Frame> newFrames = new LinkedList<>();
        NormalFrame frame = NormalFrame.first();
        newFrames.add(frame);

        for (int index = 1; index <= 8; index++) {
            frame = frame.next();
            newFrames.add(frame);
        }
        newFrames.add(frame.last());

        current = newFrames.get(0);
        return newFrames;
    }

    public boolean isGameEnd() {
        return current.isGameEnd();
    }

    public String mark() {
        return frames.stream()
                .map(Frame::mark)
                .collect(Collectors.joining()) + "\n" + scores();
    }

    private String scores() {
        return score(new StringBuilder(FIRST_SPACE), 0, 0);
    }

    private String score(StringBuilder result, int frameNumber, int score) {
        if (frameNumber == LAST_FRAME_INDEX) {
            return result.toString();
        }

        Frame frame = frames.get(frameNumber);
        if (frame.canCalculateCurrentScore()) {
            score += frame.score().score();
            String format = String.format(BASIC_SCORE_FORM, score);
            result.append(format)
                    .append(BAR.repeat(6 - format.length()))
                    .append("|");
        } else {
            result.append(NOT_EXIST_SCORE);
        }
        return score(result, frameNumber + 1, score);
    }
}
