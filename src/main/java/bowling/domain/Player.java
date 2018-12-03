package bowling.domain;

import bowling.domain.frame.Frame;

import static bowling.utils.BowlingConstants.MAX_PLAYER_LENGTH;

public class Player {

    private String name;

    public Player(String name) {
        if(name.length() > MAX_PLAYER_LENGTH) {
            throw new IllegalArgumentException("사용자의 이름은 3글자를 넘길 수 없음");
        }
        this.name = name;
    }

    Frame rollBall(Pin pin, Frame frame) {
        return frame.rollBowlingBall(pin);
    }

    boolean isPlayerFinished(Frame frame) {
        return frame.isFinished()
                && frame.isLastFrame();
    }
    public String getName() {
        return this.name;
    }
}
