package bowling.view;

import bowling.domain.Frame;
import bowling.domain.Game;
import bowling.domain.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class OutputView {
    private static final String FRAME_SECTION = "| NAME |  01  |  02  |  03  |  04  |  05  |  06  |  07  |  08  |  09  |  10  |";
    private static final String SCORE_SECTION = "|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|%6s|\n";

    public void drawStart(Game game) {
        drawTitle();
        drawName(game);
    }

    public void drawPlay(Game game) {
        drawTitle();
        drawResult(game);
    }

    private void drawTitle() {
        System.out.println(FRAME_SECTION);
    }

    private void drawName(Game game) {
        List<String> scores = new ArrayList<>();
        Player player = game.getPlay();
        scores.add(player.getName());
        List<String> values = new ArrayList<>();
        printData(scores, values);
    }

    private void drawResult(Game game) {
        List<String> scores = new ArrayList<>();
        Player player = game.getPlay();
        scores.add(player.getName());
        List<String> values = new ArrayList<>();
        Frame frame = game.getFrame();
        values = player.getSigns(); //이전프레임성적
        values = getThisScore(scores, frame, values); //이번던진투구
        printData(scores, values);
    }

    private void printData(List<String> scores, List<String> values) {
        setDataForName(values);
        scores.addAll(values);
        System.out.printf(SCORE_SECTION, Arrays.asList(scores.stream().toArray(String[]::new)).toArray());
    }

    private List<String> setDataForName(List<String> values) {
        for (int i = 0, end = 10 - values.size(); i < end; i++) {
            values.add("");
        }
        return values;
    }

    private List<String> getThisScore(List<String> scores, Frame frame, List<String> values) {
        if (frame.getSignsSize() > 0) { //지금던진투구
            scores.addAll(values);
            values = Arrays.asList(frame).stream()
                    .map(n -> n.getSigns())
                    .collect(Collectors.toList());
        }
        return values;
    }
}
