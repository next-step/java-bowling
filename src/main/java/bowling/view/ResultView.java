package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Player;
import bowling.domain.ScoreUI;
import bowling.domain.frame.Frames;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ResultView {

    private static final String FRAME_DELIMITER = "|";
    private static final String FRAME_SCORE_DEFAULT_PANEL = String.format("%-6s", "");

    private Player player;
    private Bowling bowling;
    private StringBuilder sbFrame = new StringBuilder();
    private List<String> resultString = new ArrayList<>();

    public ResultView(Player player, Bowling bowling) {
        this.player = player;
        this.bowling = bowling;
        setFramePanel();
        displayResult(0);
    }

    public void setFramePanel() {
        sbFrame.append(FRAME_DELIMITER + " NAME " + FRAME_DELIMITER);
        for(int i = 0; i <= Frames.BOWLING_GAME_FRAME; i++) {
            sbFrame.append("  " + String.format("%02d", (i + 1)) + "  " + FRAME_DELIMITER);
            resultString.add(FRAME_SCORE_DEFAULT_PANEL);
        }
    }

    public void displayResult(int frameCount) {

        resultString.set(frameCount, String.format("%-6s", " " + getScorePanel(frameCount)));
        for(int i = (frameCount + 1); i<= Frames.BOWLING_GAME_FRAME; i++) {
            resultString.set(i, FRAME_SCORE_DEFAULT_PANEL);
        }

        System.out.println(sbFrame.toString());
        System.out.print(FRAME_DELIMITER + " " +
                        String.format("%-5s", player.getName()) +
                        FRAME_DELIMITER);
        System.out.println(resultString.stream()
                                    .collect(Collectors
                                    .joining(FRAME_DELIMITER)) + FRAME_DELIMITER);
    }

    private String getScorePanel(int frameCount) {
        if (isSetScorePanel(frameCount)) {
            return getScoreUI(frameCount);
        }
        return resultString.get(frameCount).trim() + FRAME_DELIMITER + getScoreUI(frameCount);
    }

    private String getScoreUI(int frameCount) {
        return ScoreUI.frameOf(bowling.getFrameResult(frameCount));
    }

    private boolean isSetScorePanel(int frameCount) {
        return resultString.get(frameCount).equals(FRAME_SCORE_DEFAULT_PANEL);
    }

}