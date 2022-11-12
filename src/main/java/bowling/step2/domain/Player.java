package bowling.step2.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Player {
    static final String NAME_BLANK_EXCEPTION = "플레이어 이름이 입력되어야 합니다.";
    static final String NAME_LENGTH_EXCEPTION = "플레이어 이름은 3글자를 넘을 수 없습니다.";
    static final String NAME_ENGLISH_EXCEPTION = "플레이어 이름은 영어만 사용 가능합니다.";

    private final String name;
    private int currentFrameNum;
    private final Map<Integer, Frame> frameMap = new HashMap<>();


    public Player(String name, int startIndex, int LastIndex) {
        validate(name);
        for (int i = startIndex; i <= LastIndex; i++) {
            this.frameMap.put(i, getFrame(i, LastIndex));
        }
        this.name = name;
        this.currentFrameNum = startIndex;
    }

    private Frame getFrame(int i, int LastIndex) {
        if (i == LastIndex) {
            return new FinalFrame();
        }
        return new NormalFrame();
    }

    private void validate(String name) {
        if (name.isBlank()) {
            throw new IllegalArgumentException(NAME_BLANK_EXCEPTION);
        }

        if (name.length() > 3) {
            throw new IllegalArgumentException(NAME_LENGTH_EXCEPTION);
        }

        if (!Pattern.matches("^[a-zA-Z]*$", name)) {
            throw new IllegalArgumentException(NAME_ENGLISH_EXCEPTION);
        }
    }

    public void addScore(String score) {
        Frame frame = this.frameMap.get(this.currentFrameNum);
        frame.add(score);
    }

    public boolean isEndedOneFrame(int frameNum) {
        if (frameNum == this.currentFrameNum &&
                this.frameMap.get(frameNum).isEndedOneFrame()) {
            this.currentFrameNum++;
            return true;
        }
        return false;
    }

    public boolean isFinalFrame(int frameNum) {
        return this.frameMap.get(frameNum).isFinalFrame();
    }

    public Map<Integer, Frame> scoreMap() {
        return new HashMap<>(frameMap);
    }

    public int currentFrameNum() {
        return currentFrameNum;
    }

    public String name() {
        return name;
    }

}
