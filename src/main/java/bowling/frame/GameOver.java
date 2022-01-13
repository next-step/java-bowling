package bowling.frame;

import bowling.state.Throwing;

public class GameOver {

    private int count;

    public void increaseBowlCount() {
        this.count++;
    }

    public boolean isEndGame(Throwing state) {
        if (count == LastFrame.FRAME_MAX_BOWL) {
            return true;
        }
        return count == LastFrame.FRAME_MIN_BOWL && state.isMiss();
    }
}
