package bowling.dto;

import bowling.domain.frame.*;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameDTO {

    private static final int NUM_FRAMES_PER_GAME = 10;
    private static final int NUM_NORMAL_FRAMES_PER_GAME = NUM_FRAMES_PER_GAME - 1;
    private static final String NO_CONTENT_IN_NORMAL_FRAME = "       ";
    private static final String NO_CONTENT_IN_LAST_FRAME = "           ";

    private final List<Frame> frames;

    public BowlingGameDTO(List<Frame> frames) {
        this.frames = frames;
    }

    public List<String> getContents() {
        List<String> contents = new ArrayList<>();

        for (int i = 0; i < frames.size(); i++) {
            extractFromFrame(i, frames.get(i), contents);
        }

        for (int i = 0; i < NUM_NORMAL_FRAMES_PER_GAME - frames.size(); i++) {
            contents.add(NO_CONTENT_IN_NORMAL_FRAME);
        }

        if (frames.size() != NUM_FRAMES_PER_GAME) {
            contents.add(NO_CONTENT_IN_LAST_FRAME);
        }

        return contents;
    }

    private void extractFromFrame(int index, Frame frame, List<String> contents) {
        if (index == NUM_FRAMES_PER_GAME - 1) {
            contents.addAll(extractLastFrame((LastFrame) frame));
            return;
        }

        contents.add(extractNormalFrame((NormalFrame) frame));
    }

    private String extractNormalFrame(NormalFrame frame) {
        List<DownedPin> pins = frame.getTries();

        if (frame.getFrameStatus() == FrameStatus.STRIKE) {
            return "   X   ";
        }

        if (frame.getFrameStatus() == FrameStatus.SPARE) {
            return " " + pins.get(0).getNumDownedPin() + " | / ";
        }

        return " " + convertNoContent(0, pins) + " | " + convertNoContent(1, pins) + " ";
    }

    private String convertNoContent(int index, List<DownedPin> pins) {
        if (index >= pins.size()) {
            return " ";
        }
        return Integer.toString(pins.get(index).getNumDownedPin());
    }

    private List<String> extractLastFrame(LastFrame lastFrame) {
        List<NormalFrame> frames = lastFrame.getFrames();
        List<String> contents = new ArrayList<>();

        for (NormalFrame frame : frames) {
            contents.add(changeFrameInLastFrame(frame));
        }

        for (int i = 0; i < 3 - getCountsOfPitchesInLastFrame(lastFrame); i++) {
            contents.add("   ");
        }

        return contents;
    }

    private int getCountsOfPitchesInLastFrame(LastFrame lastFrame) {
        return lastFrame.getFrames().stream()
                .map(NormalFrame::getNumThrown)
                .reduce(0, Integer::sum);
    }

    private String changeFrameInLastFrame(NormalFrame frame) {
        List<DownedPin> downedPins = frame.getTries();

        if (frame.getFrameStatus() == FrameStatus.STRIKE) {
            return " X ";
        }

        if (frame.getFrameStatus() == FrameStatus.SPARE) {
            return " " + chargeGutterMark(downedPins.get(0).getNumDownedPin()) + " | / ";
        }

        String result = "";
        result = result.concat(" " + chargeGutterMark(downedPins.get(0).getNumDownedPin()) + " ");
        for (int i = 1; i < downedPins.size(); i++) {
            result = result.concat("| " + chargeGutterMark(downedPins.get(i).getNumDownedPin()) + " ");
        }

        return result;
    }

    private String chargeGutterMark(int number) {
        if (number == 0) {
            return "-";
        }
        return Integer.toString(number);
    }
}
