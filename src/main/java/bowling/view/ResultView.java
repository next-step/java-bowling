package bowling.view;

public final class ResultView {

    private static final int START_FRAME_NUMBER = 1;
    private static final int LAST_FRAME_NUMBER = 10;

    private static final String PLAYER_NAME = "NAME";
    private static final String VERTICAL_BAR = "|";

    private static final StringBuilder stringBuilder = new StringBuilder();
    public void printHead() {
        appendName(PLAYER_NAME);

        for (int frameNumber = START_FRAME_NUMBER; frameNumber <= LAST_FRAME_NUMBER; frameNumber++) {
            appendFrameNumber(frameNumber);
        }

        printContents();
    }

    private void appendFrameNumber(int frameNumber) {
        stringBuilder.append(String.format("%7s", frameNumber)).append(VERTICAL_BAR);
    }

    private void appendName(String playerName) {
        stringBuilder.append(VERTICAL_BAR).append(String.format("%7s", playerName)).append(VERTICAL_BAR);
    }

    private void printContents() {
        System.out.println(stringBuilder);
        clear();
    }

    private void clear() {
        stringBuilder.setLength(0);
    }

}
