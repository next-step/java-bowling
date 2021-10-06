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

    @DisplayName("두 개의 프레임 스트라이크를 쳤을 때 공백 결과가 나타나는지 결과 확인")
    @Test
    void strikeScoreTest() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10);
        Frame secondFrame = firstFrame.createFrame(2);
        secondFrame.throwBowl(10);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("");
    }

    @DisplayName("세 개의 프레임 모두 스트라이크를 쳤을 때 30점이 나타나는지 결과 확인")
    @Test
    void strikeScoreTest2() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10);
        Frame secondFrame = firstFrame.createFrame(2);
        secondFrame.throwBowl(10);
        Frame thirdFrame = secondFrame.createFrame(2);
        thirdFrame.throwBowl(10);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("30");
    }

    @DisplayName("한 개의 프레임을 스패어 처리했을 때 공백 결과가 나타나는지 확인")
    @Test
    void spairScoreTest() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4);
        firstFrame.throwBowl(6);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("");
    }

    @DisplayName("한 개의 프레임을 스패어 처리하고 다음 프레임에 점수를 획득했을 때 점수 결과가 나타나는지 확인")
    @Test
    void spairScoreTest2() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4);
        firstFrame.throwBowl(6);
        Frame secondFrame = firstFrame.createFrame(2);
        secondFrame.throwBowl(5);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("15");
    }

    @DisplayName("한 개의 프레임에서 공을 한번만 던 졌을 때 공백 결과가 나타나는지 확인")
    @Test
    void firstBowlScoreTest2() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("");
    }

    @DisplayName("한 개의 프레임이 정상적으로 끝났을 때의 점수 결과 확인")
    @Test
    void lastBowlScoreTest() {
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4);
        firstFrame.throwBowl(5);

        Assertions.assertThat(firstFrame.getScore()).isEqualTo("9");
    }
}