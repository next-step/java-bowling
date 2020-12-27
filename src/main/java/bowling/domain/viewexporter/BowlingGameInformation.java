package bowling.domain.viewexporter;

import bowling.domain.frame.Frame;
import bowling.dto.BowlingGameInformationDTO;

import java.util.ArrayList;
import java.util.List;

public class BowlingGameInformation {

    private static final int CONTENT_SIZE_IN_NORMAL_FRAME = 7;

    private static final int NUM_FRAMES_PER_GAME = 10;
    private static final int NUM_NORMAL_FRAMES_PER_GAME = NUM_FRAMES_PER_GAME - 1;

    private static final String NO_CONTENT_IN_NORMAL_FRAME = "       ";
    private static final String NO_CONTENT_IN_LAST_FRAME = "           ";

    private final List<Frame> frames;
    private final int currentFrame;

    public BowlingGameInformation(List<Frame> frames, int currentFrame) {
        this.frames = frames;
        this.currentFrame = currentFrame;
    }

    public int getStage() {
        return currentFrame;
    }

    public List<String> getContents() {
        List<String> contents = new ArrayList<>();

        for (Frame frame : frames) {
            contents.add(fillMargin(frame.printStatus()));
        }

        for (int i = 0; i < NUM_NORMAL_FRAMES_PER_GAME - frames.size(); i++) {
            contents.add(NO_CONTENT_IN_NORMAL_FRAME);
        }

        if (frames.size() != NUM_FRAMES_PER_GAME) {
            contents.add(NO_CONTENT_IN_LAST_FRAME);
        }

        return contents;
    }

    private String fillMargin(String content) {
        if (content.length() == CONTENT_SIZE_IN_NORMAL_FRAME) {
            return content;
        }

        StringBuilder builder = new StringBuilder();

        char marginCharacter = ' ';
        int leftMarginSize = (CONTENT_SIZE_IN_NORMAL_FRAME - content.length()) / 2;
        int rightMarginSize = CONTENT_SIZE_IN_NORMAL_FRAME - leftMarginSize - content.length();

        for (int i = 0; i < leftMarginSize; i++) {
            builder.append(marginCharacter);
        }
        builder.append(content);
        for (int i = 0; i < rightMarginSize; i++) {
            builder.append(marginCharacter);
        }

        return builder.toString();
    }

    public BowlingGameInformationDTO createBowlingGameInformationDTO() {
        return new BowlingGameInformationDTO(getContents(), getStage());
    }
}
