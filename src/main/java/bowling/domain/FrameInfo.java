package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FrameInfo {

    static FrameInfo of(int frameNo, List<PinMarkSign> pinMarkSigns, FrameScore score) {
        return of(frameNo, pinMarkSigns, score.getValue());
    }

    static FrameInfo of(int frameNo, List<PinMarkSign> pinMarkSigns, int score) {
        return new FrameInfo(
                frameNo,
                pinMarkSigns.stream()
                        .map(PinMarkSign::toString)
                        .collect(Collectors.toList()),
                score);
    }

    static FrameInfo blank(int frameNo) {
        return new FrameInfo(frameNo, Arrays.asList(), 0);
    }

    private int frameNo;
    private List<String> pinMarkSigns;
    private int score;

    private FrameInfo(int frameNo, List<String> pinMarkSigns, int score) {
        this.frameNo = frameNo;
        this.pinMarkSigns = pinMarkSigns;
        this.score = score;
    }

    public int getFrameNo() {
        return frameNo;
    }

    public List<String> getPinMarkSigns() {
        return pinMarkSigns;
    }

    public int getScore() {
        return score;
    }
}
