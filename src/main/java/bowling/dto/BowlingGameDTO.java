package bowling.dto;

import bowling.domain.*;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameDTO {

    private final NormalFrames normalFrames;
    private final LastFrame lastFrame;

    public BowlingGameDTO(NormalFrames normalFrames, LastFrame lastFrame) {
        this.normalFrames = normalFrames;
        this.lastFrame = lastFrame;
    }

    public int getCurrentWorkingFrame() {
        if (normalFrames.size() == 0) {
            return 1;
        }

        int latestIndex = normalFrames.getFrames().size() - 1;
        if (normalFrames.getFrames().get(latestIndex).isEnd()) {
            return normalFrames.getFrames().size() + 1;
        }

        return normalFrames.getFrames().size();
    }

    public List<String> getNormalFrames() {
        List<String> outPutFrames = new ArrayList<>();

        for (NormalFrame normalFrame : normalFrames.getFrames()) {
            outPutFrames.add(extractOutputForm(normalFrame));
        }

        for (int i = 0; i < 9 - normalFrames.getFrames().size(); i++) {
            outPutFrames.add("       ");
        }

        return outPutFrames;
    }

    private String extractOutputForm(NormalFrame normalFrame) {
        List<DownedPin> tries = normalFrame.getTries();

        if (normalFrame.getFrameStatus() == FrameStatus.STRIKE) {
            return "   X   ";
        }

        if (normalFrame.getFrameStatus() == FrameStatus.SPARE) {
            return " " + exchangeGutterMark(tries.get(0)) + " | / ";
        }

        if (normalFrame.getTries().size() == 1) {
            return " " + exchangeGutterMark(tries.get(0)) + " |   ";
        }

        return " " + exchangeGutterMark(tries.get(0)) + " | "
                + exchangeGutterMark(tries.get(1)) + " ";
    }

    private String exchangeGutterMark(DownedPin downedPin) {
        if (downedPin.isGutter()) {
            return "-";
        }
        return Integer.toString(downedPin.getNumDownedPin());
    }

    public String getLastFrame() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < 3; i++) {
            builder.append(" ").append(exchangeTryToPrint(i, lastFrame)).append(" |");
        }

        return builder.toString();
    }

    private String exchangeTryToPrint(int index, LastFrame lastFrame) {
        if (index >= lastFrame.getTries().size()) {
            return " ";
        }

        if (lastFrame.isStrike(index)) {
            return "X";
        }

        if (lastFrame.isSpare(index)) {
            return "/";
        }

        if (lastFrame.isGutter(index)) {
            return "-";
        }

        return Integer.toString(lastFrame.getTries().get(index).getNumDownedPin());
    }
}
