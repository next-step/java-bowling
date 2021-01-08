package bowling.domain.bowlinggame;

import bowling.domain.frame.Frame;
import bowling.domain.frame.LastFrame;
import bowling.domain.frame.NormalFrame;
import bowling.dto.BowlingGameResultsDTO;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameResults {

    private final static int NORMAL_FRAME_MARGIN = 7;
    private final static int LAST_FRAME_MARGIN = 11;
    private static final int TOTAL_FRAMES = 10;

    private final int currentFrame;
    private final List<Frame> frames;

    public BowlingGameResults(int currentFrame, List<Frame> frames) {
        this.currentFrame = currentFrame;
        this.frames = frames;
    }

    public BowlingGameResultsDTO makeDTO() {
        return new BowlingGameResultsDTO(currentFrame, makePitchForm(), makeAccumulatedForm());
    }

    private List<String> makeAccumulatedForm() {
        List<String> printContent = new ArrayList<>();

        int countsOfFixedScore = 0;
        int previousFrameScore = 0;
        for (Frame frame : frames) {
            if (frame.isEnd() && !frame.needAdditionalScore()) {
                String content = Integer.toString(frame.calculateScore() + previousFrameScore);
                countsOfFixedScore += 1;
                previousFrameScore += frame.calculateScore();

                if (frame instanceof LastFrame) {
                    fillMargin(content, LAST_FRAME_MARGIN, printContent);
                    break;
                }

                fillMargin(content, NORMAL_FRAME_MARGIN, printContent);
            }
        }

        for (int i = countsOfFixedScore; i < TOTAL_FRAMES; i++) {
            fillEmptyFrameContent(printContent, i);
        }

        return printContent;
    }

    private List<String> makePitchForm() {
        List<String> printContent = new ArrayList<>();

        for (int i = 0; i < frames.size(); i++) {
            transformFrame(printContent, frames.get(i));
        }

        for (int i = frames.size(); i < TOTAL_FRAMES; i++) {
            fillEmptyFrameContent(printContent, i);
        }

        return printContent;
    }

    private void transformFrame(List<String> content, Frame frame) {
        if (frame instanceof LastFrame) {
            content.add(interpretLastFrame((LastFrame) frame));
            return;
        }

        if (frame instanceof NormalFrame) {
            content.add(interpretNormalFrame((NormalFrame) frame));
        }
    }

    private void fillEmptyFrameContent(List<String> contents, int frameIndex) {
        if (frameIndex == TOTAL_FRAMES - 1) {
            contents.add("           ");
            return;
        }

        contents.add("       ");
    }

    private String interpretLastFrame(LastFrame lastFrame) {
        List<NormalFrame> frames = lastFrame.getFrames();
        String content = PinStatus.interpretPinsStatus(frames.get(0).getDownedPins());

        for (int i = 1; i < frames.size(); i++) {
            content = content.concat("|").concat(PinStatus.interpretPinsStatus(frames.get(i).getDownedPins()));
        }

        return fillMargin(LAST_FRAME_MARGIN, content);
    }

    private String interpretNormalFrame(NormalFrame normalFrame) {
        String content = PinStatus.interpretPinsStatus(normalFrame.getDownedPins());

        if (content.equals(" X ")) {
            int marginOnStrike = (NORMAL_FRAME_MARGIN - content.length()) / 2;
            return fillMargin(NORMAL_FRAME_MARGIN, fillMargin(marginOnStrike, "").concat(content));
        }

        return fillMargin(NORMAL_FRAME_MARGIN, content);
    }

    private void fillMargin(String content, int FrameMargin, List<String> printContent) {
        int marginOnScore = (FrameMargin - content.length()) / 2;
        String filledMarginContent = fillMargin(FrameMargin, fillMargin(marginOnScore, "").concat(content));
        printContent.add(filledMarginContent);
    }

    private String fillMargin(int contentSize, String content) {
        String marginFilledContent = content;
        for (int i = 0; i < contentSize - content.length(); i++) {
            marginFilledContent = marginFilledContent.concat(" ");
        }

        return marginFilledContent;
    }
}
