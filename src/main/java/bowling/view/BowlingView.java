package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Name;

public class BowlingView {

    private static String SITUATION_CONTENT = "프레임 투구 : ";
    private final static int MAX_FRAME = 10;
    private Name name;
    private StringBuilder nameBuilder = new StringBuilder();

    public BowlingView(Name name) {
        this.name = name;
        this.nameBuilder.append("|  " + name.getName() + " |");
    }

    public void repeatFrameChance(final int frameIndex, final Frame frame, StringBuilder stringBuilder) {
        for (int i = 0; i < frame.getPins().getChances().size(); i++) {
            printSituationContent(frameIndex + 1,
                    frame.getPins().getChances().get(i).getCountOfPin()
            );
            printBasicFrame();

            System.out.print(nameBuilder.toString());
            System.out.print(stringBuilder);
            System.out.print(appendChance(i, frame));

            repeatFrameBlank(MAX_FRAME - frameIndex - 1);
        }

        stringBuilder.append(appendChance(frame.getPins().getChances().size() - 1, frame));
    }

    public String appendChance(final int chanceIndex, final Frame frame) {
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


    private void printBasicFrame() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("| NAME |");
        for (int i = 0; i < MAX_FRAME - 1; i++) {
            stringBuilder.append("  0" + i + "  |");
        }
        stringBuilder.append("  " + MAX_FRAME + "  |");

        System.out.println(stringBuilder);
    }

    private void printSituationContent(int frameIndex, int countOfPin){
        System.out.println(frameIndex + SITUATION_CONTENT + countOfPin);
    }

    private void repeatFrameBlank(int count) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            stringBuilder.append("      |");
        }

        System.out.println(stringBuilder);
    }
}
