package bowling.domain;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;

import java.util.ArrayList;
import java.util.List;

import static bowling.Constants.FIRST_FRAME_NUMBER;
import static bowling.Constants.MAX_FRAME_NUMBER;

public class Player {
    public static final String PLAYER_NAME_ERROR = "플레이어의 이름은 3글자로 입력해주세요.";
    private static final int PLAYER_NAME_LENGTH = 3;
    private String name;
    private List<Frame> frames;

    public Player(String name) {
        assertPlayerName(name);
        this.name = name;
        this.frames = new ArrayList<>();

        frames.add(NormalFrame.create(FIRST_FRAME_NUMBER));
    }

    public void play(int felledPin) {
        Frame frame = getCurrentFrame();
        frame.play(felledPin);

        if(!frame.isLastFrame() && frame.isEndedFrame()) {
            Frame nextFrame = frame.getNext();

            if(frame != null) {
                frames.add(nextFrame);
            }
        }
    }

    public boolean isEnd() {
        return frames.size() == MAX_FRAME_NUMBER;
    }

    public Frame getCurrentFrame() {
        return frames.get(frames.size() - 1);
    }

    public int getCurrentFrameNumber() {
        return frames.size();
    }

    public String getName() {
        return name;
    }

    public List<Frame> getFrames() {
        return frames;
    }

    private void assertPlayerName(String name) {
        if(name.length() != PLAYER_NAME_LENGTH) {
            throw new IllegalArgumentException(PLAYER_NAME_ERROR);
        }
    }
}
