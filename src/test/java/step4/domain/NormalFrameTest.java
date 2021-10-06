package step4.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.state.FirstBowl;
import step4.domain.state.LastBowl;
import step4.domain.state.Spare;
import step4.domain.state.Strike;

class NormalFrameTest {
    @DisplayName("Ready state -> Strike state 상태 변경 확인")
    @Test
    void strikeTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(10);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new Strike());
    }

    @DisplayName("Ready state -> FirstBowl state 상태 변경 확인")
    @Test
    void firstBowlTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new FirstBowl(4));
    }

    @DisplayName("FirstBowl state -> LastBowl state 상태 변경 확인")
    @Test
    void lastBowlTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4);
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new LastBowl(4, 4));
    }

    @DisplayName("FirstBowl state -> Spair state 상태 변경 확인")
    @Test
    void spairTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4);
        normalFrame.throwBowl(6);
        Assertions.assertThat(normalFrame.state()).isEqualTo(new Spare(4, 6));
    }

    @DisplayName("볼링 공을 한번 던 졌을 때 FirstBowl 상태에 대한 점수 확인")
    @Test
    void LastBowlScoreTest() {
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4);
        normalFrame.throwBowl(4);
        Assertions.assertThat(normalFrame.getScore()).isEqualTo("8");
    }

    @DisplayName("두개의 프레임 스트라이크를 쳤을 때 공백 결과가 나타나는지 결과 확인")
    @Test
    void StrikeScoreTest() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10);
        Frame secondFrame = firstFrame.createFrame(2);
        secondFrame.throwBowl(10);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("");
    }

    @DisplayName("세개의 프레임 모두 스트라이크를 쳤을 때 30점이 나타나는지 결과 확인")
    @Test
    void StrikeScoreTest2() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10);
        Frame secondFrame = firstFrame.createFrame(2);
        secondFrame.throwBowl(10);
        Frame thirdFrame = secondFrame.createFrame(2);
        thirdFrame.throwBowl(10);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("30");
    }
}