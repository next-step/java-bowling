package bowling.domain;

import bowling.util.ErrorMessage;
import bowling.view.BowlingView;

public class Print {

    private static final int MAX_FRAME = 10;

    private Frames frames;
    private Name name;


    public Print(final Frames frames, final Name name) {
        checkNull(frames, name);

        this.frames = frames;
        this.name = name;
    }

    public void printScore() {
        StringBuilder stringBuilder = new StringBuilder();
        BowlingView bowlingView = new BowlingView(name);
        for (int i = 0; i < MAX_FRAME; i++) {
            bowlingView.repeatFrameChance(i, frames.getFrameList().get(i), stringBuilder);
            System.out.println();
        }
    }

    private void checkNull(final Frames frames, final Name name) {
        if (frames == null || name == null) {
            throw new NullPointerException(ErrorMessage.getCheckNull());
        }
    }
}
