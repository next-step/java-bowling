package View;

import domain.Bowling;
import domain.Frame;

import java.util.Optional;
import java.util.stream.IntStream;

import static domain.Bowling.TOTAL_FRAME_COUNT;
import static domain.Frame.ONE;

public class PointResultFormatter implements Formatter<Bowling> {

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
        final String BLANK_FRAME = "    ";
        final String SCORE_CONNECTOR = " | ";

        Optional<Frame> maybeTargetFrame = Optional.ofNullable(bowling.getFrame(frameNumber));
        if(maybeTargetFrame.isPresent()) {
            Frame targetFrame = maybeTargetFrame.get();
            return targetFrame.getPoint() + SCORE_CONNECTOR;
        }
        return BLANK_FRAME + SCORE_CONNECTOR;
    }
}
