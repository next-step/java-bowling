package bowling.domain;

import bowling.util.ErrorMessage;
import bowling.view.BowlingView;

public class Print {

    private static final int MAX_FRAME = 10;

    private Frames frames;
    private Name name;

    private StringBuilder nameBuilder = new StringBuilder();

    public Print(final Frames frames, final Name name) {
        checkNull(frames, name);

        this.frames = frames;
        this.name = name;
        this.nameBuilder.append("|  " + name.getName() + " |");
    }

    public void printScore() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < MAX_FRAME; i++) {
            repeatFrameChance(i, frames.getFrameList().get(i), stringBuilder);
            System.out.println();
        }
    }

    private void repeatFrameChance(final int frameIndex, final Frame frame, StringBuilder stringBuilder) {
        for (int i = 0; i < frame.getPins().getChances().size(); i++) {
            BowlingView.printSituationContent(frameIndex + 1,
                    frame.getPins().getChances().get(i).getCountOfPin()
            );
            BowlingView.printBasicFrame();

            System.out.print(nameBuilder.toString());
            System.out.print(stringBuilder);
            System.out.print(appendChance(i, frame));

            BowlingView.repeatFrameBlank(MAX_FRAME - frameIndex - 1);
        }

        stringBuilder.append(appendChance(frame.getPins().getChances().size() - 1, frame));
    }

    private String appendChance(final int chanceIndex, final Frame frame) {
        StringBuilder stringBuilder = new StringBuilder();

        if (chanceIndex == 0) {
            return "   " + frame.getScore().getFirstScore() + "  |";
        }

        stringBuilder.append(frame.getScore().getFirstScore());
        for (int i = 1; i <= chanceIndex; i++) {
            stringBuilder.append("|" + frame.getScore().getNextScore(i));
        }

        return "  " + stringBuilder.toString() + " |";
    }

    private void checkNull(final Frames frames, final Name name) {
        if (frames == null || name == null) {
            throw new NullPointerException(ErrorMessage.getCheckNull());
        }
    }
}
