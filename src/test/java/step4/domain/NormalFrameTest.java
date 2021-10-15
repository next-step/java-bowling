package step4.domain;

import static org.assertj.core.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step4.domain.state.FirstBowl;
import step4.domain.state.LastBowl;
import step4.domain.state.Spare;
import step4.domain.state.Strike;
import step4.exception.NeedAdditionalFrameException;

class NormalFrameTest {
    @DisplayName("Ready state -> Strike state 상태 변경 확인")
    @Test
    void strikeTest() {
        Frames frames = new Frames();
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(10, frames);
        assertThat(normalFrame.state()).isEqualTo(new Strike());
    }

    @DisplayName("Ready state -> FirstBowl state 상태 변경 확인")
    @Test
    void firstBowlTest() {
        Frames frames = new Frames();
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4, frames);
        assertThat(normalFrame.state()).isEqualTo(new FirstBowl(4));
    }

    @DisplayName("FirstBowl state -> LastBowl state 상태 변경 확인")
    @Test
    void lastBowlTest() {
        Frames frames = new Frames();
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4, frames);
        normalFrame.throwBowl(4, frames);
        assertThat(normalFrame.state()).isEqualTo(new LastBowl(4, 4));
    }

    @DisplayName("FirstBowl state -> Spair state 상태 변경 확인")
    @Test
    void spairTest() {
        Frames frames = new Frames();
        NormalFrame normalFrame = new NormalFrame(1);
        normalFrame.throwBowl(4, frames);
        normalFrame.throwBowl(6, frames);
        assertThat(normalFrame.state()).isEqualTo(new Spare(4, 6));
    }

    @DisplayName("두 개의 프레임 스트라이크를 쳤을 때 공백 결과가 나타나는지 결과 확인")
    @Test
    void strikeScoreTest() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);

        assertThatThrownBy(() -> {frames.ofFirst().getScore();}).isInstanceOf(NeedAdditionalFrameException.class);
    }

    @DisplayName("세 개의 프레임 모두 스트라이크를 쳤을 때 30점이 나타나는지 결과 확인")
    @Test
    void strikeScoreTest2() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);

        assertThat(firstFrame.getScore().getScore()).isEqualTo(30);
    }

    @DisplayName("두개의 프레임 스트라이크 나머지 1개의 프레임을 스트라이크 못 쳤을 때 결과 확인")
    @Test
    void strikeScoreTest3() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);
        frames.ofLast().throwBowl(4, frames);

        Assertions.assertThat(firstFrame.getScore().getScore()).isEqualTo(24);
    }

    @DisplayName("두개의 프레임 스트라이크 나머지 1개의 프레임을 스트라이크 못 쳤을 때 결과 확인")
    @Test
    void strikeScoreTest4() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10, frames);

        frames.ofLast().throwBowl(10, frames);

        frames.ofLast().throwBowl(4, frames);
        frames.ofLast().throwBowl(5, frames);

        assertThat(firstFrame.getScore().getScore()).isEqualTo(24);
    }

    @DisplayName("세 개의 프레임 모두 스트라이크를 쳤을 때 두번째 프레임 오류 확인")
    @Test
    void strikeScoreTest5() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);
        frames.ofLast().throwBowl(10, frames);

        assertThatThrownBy(() -> {
            frames.frames().get(1).getScore();}).isInstanceOf(NeedAdditionalFrameException.class);
    }


    @DisplayName("한 개의 프레임을 스패어 처리했을 때 공백 결과가 나타나는지 확인")
    @Test
    void spairScoreTest() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4, frames);
        firstFrame.throwBowl(6, frames);

        assertThatThrownBy(() -> {firstFrame.getScore();}).isInstanceOf(NeedAdditionalFrameException.class);
    }

    @DisplayName("한 개의 프레임을 스패어 처리하고 다음 프레임에 점수를 획득했을 때 점수 결과가 나타나는지 확인")
    @Test
    void spairScoreTest2() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4, frames);
        firstFrame.throwBowl(6, frames);
        frames.ofLast().throwBowl(5, frames);

        assertThat(firstFrame.getScore().getScore()).isEqualTo(15);
    }

    @DisplayName("한 개의 프레임에서 공을 한번만 던 졌을 때 공백 결과가 나타나는지 확인")
    @Test
    void firstBowlScoreTest2() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4, frames);

        assertThatThrownBy(() -> {firstFrame.getScore();}).isInstanceOf(NeedAdditionalFrameException.class);
    }

    @DisplayName("한 개의 프레임이 정상적으로 끝났을 때의 점수 결과 확인")
    @Test
    void lastBowlScoreTest() {
        Frames frames = new Frames();
        Frame firstFrame = new NormalFrame(1);
        firstFrame.throwBowl(4, frames);
        firstFrame.throwBowl(5, frames);

        assertThat(firstFrame.getScore().getScore()).isEqualTo(9);
    }


}