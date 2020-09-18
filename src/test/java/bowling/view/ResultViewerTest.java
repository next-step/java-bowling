package bowling.view;

import bowling.domain.Game;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class ResultViewerTest {
    public static final String USERNAME = "kyd";

    @Test
    void getSumScores() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        resultViewer.record(game.hit(9));
        resultViewer.record(game.hit(1));

        List<Integer> scores = resultViewer.getScores();

        assertThat(scores.get(0)).isEqualTo(10);
    }

    @Test
    void getSumScores_all_strike() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        for (int index = 0; index < 12; index++) {
            resultViewer.record(game.hit(10));
        }

        List<Integer> scores = resultViewer.getScores();

        assertThat(scores.get(0)).isEqualTo(30);
        assertThat(scores.get(1)).isEqualTo(60);
        assertThat(scores.get(2)).isEqualTo(90);
        assertThat(scores.get(3)).isEqualTo(120);
        assertThat(scores.get(4)).isEqualTo(150);
        assertThat(scores.get(5)).isEqualTo(180);
        assertThat(scores.get(6)).isEqualTo(210);
        assertThat(scores.get(7)).isEqualTo(240);
        assertThat(scores.get(8)).isEqualTo(270);
        assertThat(scores.get(9)).isEqualTo(300);
    }

    @Test
    void getSumScores_lastSpare() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        for (int index = 0; index < 12; index++) {
            if (index == 9) {
                resultViewer.record(game.hit(9));
            } else if (index == 10) {
                resultViewer.record(game.hit(1));
            } else {
                resultViewer.record(game.hit(10));
            }
        }

        List<Integer> scores = resultViewer.getScores();

        assertThat(scores.get(0)).isEqualTo(30);
        assertThat(scores.get(1)).isEqualTo(60);
        assertThat(scores.get(2)).isEqualTo(90);
        assertThat(scores.get(3)).isEqualTo(120);
        assertThat(scores.get(4)).isEqualTo(150);
        assertThat(scores.get(5)).isEqualTo(180);
        assertThat(scores.get(6)).isEqualTo(210);
        assertThat(scores.get(7)).isEqualTo(239);
        assertThat(scores.get(8)).isEqualTo(259);
        assertThat(scores.get(9)).isEqualTo(279);
    }

    @Test
    void getStatus() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        resultViewer.record(game.hit(9));
        resultViewer.record(game.hit(1));

        List<List<String>> status = resultViewer.getStatus();

        assertThat(status.get(0)).isEqualTo(Arrays.asList("9", "/"));
    }

    @Test
    void getStatus_all_strike() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        for (int index = 0; index < 12; index++) {
            resultViewer.record(game.hit(10));
        }

        List<List<String>> status = resultViewer.getStatus();

        assertThat(status.get(0)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(1)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(2)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(3)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(4)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(5)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(6)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(7)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(8)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(9)).isEqualTo(Arrays.asList("X", "X", "X"));
    }

    @Test
    void getStatus_lastSpare() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        for (int index = 0; index < 12; index++) {
            if (index == 9) {
                resultViewer.record(game.hit(9));
            } else if (index == 10) {
                resultViewer.record(game.hit(1));
            } else {
                resultViewer.record(game.hit(10));
            }
        }

        List<List<String>> status = resultViewer.getStatus();

        assertThat(status.get(0)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(1)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(2)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(3)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(4)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(5)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(6)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(7)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(8)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(9)).isEqualTo(Arrays.asList("9", "/", "X"));
    }

    @Test
    void getStatus_sample() {
        Game game = Game.start(USERNAME);

        ResultViewer resultViewer = new ResultViewer(game);

        resultViewer.record(game.hit(1));
        resultViewer.record(game.hit(2));
        resultViewer.record(game.hit(9));
        resultViewer.record(game.hit(1));
        resultViewer.record(game.hit(2));
        resultViewer.record(game.hit(3));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(9));
        resultViewer.record(game.hit(1));
        resultViewer.record(game.hit(10));
        resultViewer.record(game.hit(9));
        resultViewer.record(game.hit(1));

        List<List<String>> status = resultViewer.getStatus();

        assertThat(status.get(0)).isEqualTo(Arrays.asList("1", "2"));
        assertThat(status.get(1)).isEqualTo(Arrays.asList("9", "/"));
        assertThat(status.get(2)).isEqualTo(Arrays.asList("2", "3"));
        assertThat(status.get(3)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(4)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(5)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(6)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(7)).isEqualTo(Arrays.asList("X"));
        assertThat(status.get(8)).isEqualTo(Arrays.asList("9", "/"));
        assertThat(status.get(9)).isEqualTo(Arrays.asList("X", "9", "/"));
    }
}
