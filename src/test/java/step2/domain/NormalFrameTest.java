package step2.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import step2.exceptions.BowlingPintNumberError;

import static org.assertj.core.api.Assertions.*;

class NormalFrameTest {
    @DisplayName("knockdown 메서드 검사")
    @Test
    void knockDown() {
        NormalFrame actual = new NormalFrame();
        actual.knockDown(4);
        NormalFrame expected = new NormalFrame();
        expected.knockDown(4);
        assertThat(actual).isEqualTo(expected);
    }

    @DisplayName("isPossible 메서드 검사: 볼링핀을 10개 넘게 쓰러뜨린 경우 오류 확인")
    @Test
    void overTenScoreErrorTest() {
        NormalFrame normalFrame = new NormalFrame();
        assertThatThrownBy(() -> {
            normalFrame.knockDown(11);
        }).isInstanceOf(BowlingPintNumberError.class);
    }

    @DisplayName("isPossible 메서드 검사: 볼링핀을 10개 넘게 쓰러뜨린 경우 오류 확인2")
    @Test
    void overTenScoreErrorTest2() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(5);
        assertThatThrownBy(() -> {
            normalFrame.knockDown(6);
        }).isInstanceOf(BowlingPintNumberError.class);
    }

    @DisplayName("볼링핀을 4개 쓰러뜨리면, frame의 점수는 4점")
    @Test
    void score() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(4);
        int actual = normalFrame.score();
        assertThat(actual).isEqualTo(4);
    }

    @DisplayName("볼링핀을 9개 쓰러뜨리면, frame의 점수는 9점")
    @Test
    void score2() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(4);
        normalFrame.knockDown(5);
        int actual = normalFrame.score();
        assertThat(actual).isEqualTo(9);
    }

    @DisplayName("10점을 쓰러뜨린 경우라면 finish 결과는 true")
    @Test
    void finish() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(10);
        assertThat(normalFrame.isFinish()).isTrue();
    }

    @DisplayName("9점을 쓰러뜨린 경우라면 finish 결과는 false")
    @Test
    void finish2() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(9);
        assertThat(normalFrame.isFinish()).isFalse();
    }

    @DisplayName("NormalFrame에서 2번의 turn을 사용했다면 finsh 결과는 true")
    @Test
    void finish3() {
        NormalFrame normalFrame = new NormalFrame();
        normalFrame.knockDown(8);
        normalFrame.knockDown(1);
        assertThat(normalFrame.isFinish()).isTrue();
    }




}