package bowling;

import bowling.domain.Bowling;
import bowling.domain.ScoreUI.ScoreFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class BowlingTest {
    Bowling bowling = new Bowling();

    @DisplayName("각 프레임이 스트라이크이면 X")
    @Test
    void strikeTest() {
        bowling.addPlayerScore(10);
        ScoreFactory ui = bowling.getBowLingResult(0).getResultUI().get(0);
        assertThat(ui.getString()).isEqualTo("x");
    }

    @DisplayName("각 프레임이 스트라이크이면 X")
    @Test
    void spareTest() {
        bowling.addPlayerScore(9);
        bowling.addPlayerScore(1);
        List<ScoreFactory> ui = bowling.getBowLingResult(0).getResultUI();
        assertThat(ui.get(0).getString()).isEqualTo("9");
        assertThat(ui.get(1).getString()).isEqualTo("/");
    }

    @DisplayName("각 프레임이 미스이면 4|5")
    @Test
    void missTest() {
        bowling.addPlayerScore(4);
        bowling.addPlayerScore(5);
        List<ScoreFactory> ui = bowling.getBowLingResult(0).getResultUI();
        assertThat(ui.get(0).getString()).isEqualTo("4");
        assertThat(ui.get(1).getString()).isEqualTo("5");
    }

    @DisplayName("핀을 하나도 쓰러트리지 못한 상태. -")
    @Test
    void gutterTest() {
        bowling.addPlayerScore(0);
        List<ScoreFactory> ui = bowling.getBowLingResult(0).getResultUI();
        assertThat(ui.get(0).getString()).isEqualTo("-");
    }


}
