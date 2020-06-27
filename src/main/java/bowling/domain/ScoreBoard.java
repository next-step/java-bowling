package bowling.domain;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ScoreBoard {
//    private Frames frames;
//    private int currentIndex;
//
//    private ScoreBoard(Frames frames, int currentIndex) {
//        this.frames = frames;
//        this.currentIndex = currentIndex;
//    }
//
//    public static ScoreBoard init() {
//        List<Frame> frames = Stream.generate(() -> Frame.init())
//                .limit(9)
//                .collect(Collectors.toList());
//        frames.add(FinalFrame.init());
//
//        return new ScoreBoard(Frames.init(frames), 0);
//    }
//
//    public int size() {
//        return frames.size();
//    }
//
//    public int getCurrentIndex() {
//        return currentIndex;
//    }
//
//    public boolean isLast() {
//        return currentIndex == 10;
//    }
}
