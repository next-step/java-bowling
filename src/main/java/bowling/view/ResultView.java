package bowling.view;

public class ResultView {

    private ResultView() {

    }

    public static void printFrameResult(FramesResult result) {
        System.out.println(result.getFrameNumberLine());
        System.out.println(result.getFrameScores() + System.lineSeparator());
    }
}
