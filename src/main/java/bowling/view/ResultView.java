package bowling.view;

public class ResultView {

    private ResultView() {

    }

    public static void printFrameResult(FramesResult result) {
        System.out.println(result.frameNumberLine());
        System.out.println(result.frameSigns());
        System.out.println(result.frameScores() + System.lineSeparator());
    }
}
