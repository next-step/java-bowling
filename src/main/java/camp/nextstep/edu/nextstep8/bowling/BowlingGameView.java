package camp.nextstep.edu.nextstep8.bowling;

public class BowlingGameView {
    private static final String HEADER_NAME = "NAME";
    private static final String BASE_FORMAT = "|%5s\t";
    private static final String PIPE = "|";
    private static final int MAX_LOOP_COUNT = 10;

    public static void showDashboard(String player, ScoreBoard scoreBoard) {
        int loopTimes = getLoopTimes(scoreBoard.getFrameCount());
        makeResult(index -> String.format("%02d", index), HEADER_NAME, loopTimes);
        makeResult(index -> scoreBoard.getFrameResult(index), player, loopTimes);
        System.out.println();
    }

    private static int getLoopTimes(int frameCount) {
        return frameCount > MAX_LOOP_COUNT ?
                frameCount :
                MAX_LOOP_COUNT;
    }

    private static void makeResult(TextRenderer renderer, String title, int loopTimes) {
        StringBuilder output = new StringBuilder();
        output.append(wrapFormat(title));
        for(int i = 1; i <= loopTimes; i++) {
            output.append(wrapFormat(renderer.render(i)));
        }
        output.append(PIPE);
        System.out.println(output.toString());
    }

    private static String wrapFormat(String value) {
        return String.format(BASE_FORMAT, value);
    }
}
