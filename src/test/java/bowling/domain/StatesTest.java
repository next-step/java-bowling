package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StatesTest {

    @DisplayName("볼링 점수 상태 (스트라이크, 스페어, 미스, 거터)에 따라 Score 객체 생성")
    @Test
    void createScore_strike() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(10);
        States states = normalFrame.getStates();
        Score score = states.createScore();
        assertThat(score).isEqualTo(new Score(10, 2));
    }

    @DisplayName("볼링 점수 상태 (스트라이크, 스페어, 미스, 거터)에 따라 Score 객체 생성")
    @Test
    void createScore_spare() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(9);
        normalFrame.delivery(1);
        States states = normalFrame.getStates();
        Score score = states.createScore();
        assertThat(score).isEqualTo(new Score(10, 1));
    }

    @DisplayName("볼링 점수 상태 (스트라이크, 스페어, 미스, 거터)에 따라 Score 객체 생성")
    @Test
    void createScore_miss() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.delivery(3);
        normalFrame.delivery(3);
        States states = normalFrame.getStates();
        Score score = states.createScore();
        assertThat(score).isEqualTo(new Score(6, 0));
    }
}