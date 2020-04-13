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

    public void drawStart(Player player) {
        drawTitle();
        drawName(player);
    }

    public void drawPlay(Game game) {
        drawTitle();
        drawResult(game);
    }

    private void drawTitle() {
        System.out.println(FRAME_SECTION);
    }

    private void drawName(Player player) {
        List<String> scores = new ArrayList<>();
        scores.add(player.getName());
        List<String> values = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            values.add("");
        }
        scores.addAll(values);
        System.out.printf(SCORE_SECTION, Arrays.asList(scores.stream().toArray(String[]::new)).toArray());
    }

    private void drawResult(Game game) {
        List<String> scores = new ArrayList<>();
        Player player = game.getPlay();
        Frame frame = game.getFrame();
        scores.add(game.getPlay().getName());
        List<String> values = new ArrayList<>();
        if (frame.getScores().size() == 0 && player != null){
            values = player.getSigns();
        }else {
            values = player.getSigns();
            scores.addAll(values);
            values = Arrays.asList(frame).stream()
                    .map(n -> n.getSigns())
                    .collect(Collectors.toList());
        }
        for (int i = 0, end = 10 - values.size(); i < end; i++) {
            values.add("");
        }
        scores.addAll(values);
        System.out.printf(SCORE_SECTION, Arrays.asList(scores.stream().toArray(String[]::new)).toArray());
    }
}
