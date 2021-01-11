package bowling.view;

public class BowlingView {

    private static String SITUATION_CONTENT = "프레임 투구 : ";
    private final static int MAX_FRAME = 10;

    private BowlingView() {

    }

    public static void printBasicFrame() {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append("| NAME |");
        for (int i = 0; i < MAX_FRAME - 1; i++) {
            stringBuilder.append("  0" + i + "  |");
        }
        stringBuilder.append("  " + MAX_FRAME + "  |");

        System.out.println(stringBuilder);
    }

    public static void printSituationContent(int frameIndex, int countOfPin){
        System.out.println(frameIndex + SITUATION_CONTENT + countOfPin);
    }

    public static void repeatFrameBlank(int count) {
        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < count; i++) {
            stringBuilder.append("      |");
        }

        System.out.println(stringBuilder);
    }
}
