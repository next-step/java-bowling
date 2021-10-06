package step4.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.state.FirstBowl;
import step4.domain.state.LastBowl;
import step4.domain.state.Spair;
import step4.domain.state.Strike;

class NormalFrameTest {
    @DisplayName("Ready state -> Strike state 상태 변경 확인")
    @Test
    void strikeTest() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBowl(10);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new Strike());
    }

    @DisplayName("Ready state -> FirstBowl state 상태 변경 확인")
    @Test
    void firstBowlTest() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new FirstBowl(4));
    }

    @DisplayName("FirstBowl state -> LastBowl state 상태 변경 확인")
    @Test
    void lastBowlTest() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBowl(4);
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new LastBowl(8));
    }

    @DisplayName("FirstBowl state -> Spair state 상태 변경 확인")
    @Test
    void spairTest() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBowl(4);
        normalFrame.throwBowl(6);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new Spair());
    }

    @DisplayName("볼링 공을 한번 던 졌을 때 FirstBowl 상태에 대한 점수 확인")
    @Test
    void firstBowlScoreTest() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.getScore()).isEqualTo(4);
    }
}