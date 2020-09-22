package bowling.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Frames {

    private static final int SIZE_ROUND_GAP = 1;
    private static final int FINAL_FRAME_NO = 10;
    private static final String EMPTY_HISTORY = "";

    private final List<Frame> frames;

    public Frames() {
        frames = new ArrayList<>();
    }

    public int getRound() {
        return frames.size() + SIZE_ROUND_GAP;
    }

    public void swing(int score) {
        // TODO 로직 작성
    }

    private Frame lastFrame() {

        if (frames.isEmpty()) {
            frames.add(FrameFactory.first());
        }

        return frames.get(frames.size() - 1);
    }

    private void addFrame() {
        // TODO 로직 작성
    }

    private boolean isFinalFrame() {
        return frames.size() >= FINAL_FRAME_NO;
    }

    public List<String> getSwingHistory() {

        Stream<String> framesStream = frames.stream()
                                            .map(Frame::swingHistoryToString);

        Stream<String> emptyStream = Stream.generate(() -> EMPTY_HISTORY)
                                           .limit(FINAL_FRAME_NO - frames.size());

        return Stream.concat(framesStream, emptyStream)
                     .collect(toList());
    }
}
