package bowling.domain;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FrameInfo {

    static FrameInfo of(int frameNo, List<PinMarkSymbol> pinMarkSymbols, int score) {
        return of(frameNo, pinMarkSymbols, FrameScore.of(score));
    }

    static FrameInfo of(int frameNo, List<PinMarkSymbol> pinMarkSymbols, FrameScore score) {
        return new FrameInfo(
                frameNo,
                pinMarkSymbols.stream()
                        .map(PinMarkSymbol::toString)
                        .collect(Collectors.toList()),
                score);
    }

    static FrameInfo blank(int frameNo) {
        return new FrameInfo(frameNo, Arrays.asList(), FrameScore.unknown);
    }

    private int frameNo;
    private List<String> pinMarkSigns;
    private FrameScore score;

    private FrameInfo(int frameNo, List<String> pinMarkSigns, FrameScore score) {
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

    public FrameScore getScore() {
        return score;
    }
}
