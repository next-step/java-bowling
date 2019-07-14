package View;

import domain.Bowling;
import domain.Frame;

import java.util.Optional;
import java.util.stream.IntStream;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static domain.Frame.ONE;

public class PointResultFormatter implements Formatter<Bowling> {
    final String BLANK_FRAME = "    ";
    final String SCORE_CONNECTOR = " | ";

    @Override
    public String format(Bowling bowling) {
        StringBuilder builder = new StringBuilder();
        IntStream.rangeClosed(ONE, TOTAL_FRAME_COUNT)
                .boxed()
                .map(frameNumber -> getFramePoint(bowling, frameNumber))
                .forEach(builder::append);
        return builder.toString();
    }

    private String getFramePoint(Bowling bowling, int frameNumber) {

        Optional<Frame> maybeTargetFrame = Optional.ofNullable(bowling.getFrame(frameNumber));
        if (maybeTargetFrame.isPresent()) {
            Frame targetFrame = maybeTargetFrame.orElse(null);
            return targetFrame.getPoint() + getConnector(frameNumber, bowling.isBowlingEnd());
        }
        return BLANK_FRAME + SCORE_CONNECTOR;
    }

    private String getConnector(int frameNumber, boolean bowlingEnd) {
        if (frameNumber == TOTAL_FRAME_COUNT && bowlingEnd) {
            return "|";
        }
        return SCORE_CONNECTOR;
    }
}
