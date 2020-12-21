package bowling.domain;

import bowling.domain.frame.FinalFrame;
import bowling.domain.state.Pins;
import bowling.domain.state.Spare;
import bowling.domain.state.State;
import bowling.domain.state.Strike;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

import static org.assertj.core.api.Assertions.assertThat;


class FinalFrameTest {

    private FinalFrame finalFrame;

    @BeforeEach
    void setUp() {
        finalFrame = new FinalFrame();
    }

    @Test
    @DisplayName("게임 종료 확인")
    void isGameEnd_empty() {
        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 확인 - 한번 던짐")
    void isGameEnd_1pitch() {
        finalFrame.bowl(1);
        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 확인 - MISS")
    void isGameEnd_miss() {
        finalFrame.bowl(4);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스페어 종료x")
    void isGameEnd_spare() {
        finalFrame.bowl(1);
        finalFrame.bowl(9);

        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스페어 종료o")
    void isGameEnd_spareComplete() {
        finalFrame.bowl(1);
        finalFrame.bowl(9);
        finalFrame.bowl(1);

        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스트라이크 두번 던짐")
    void isGameEnd_strike2times() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스트라이크 세번 던짐")
    void isGameEnd_strike3times() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("막판 스트라이크 1회")
    void bowl_1strike() {
        finalFrame.bowl(10);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        FinalFrame finalFrameExpected = new FinalFrame(states);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }

    @Test
    @DisplayName("막판 스트라이크 2회")
    void bowl_2strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        FinalFrame finalFrameExpected = new FinalFrame(states);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }

    @Test
    @DisplayName("막판 스트라이크 3회")
    void bowl_3strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        FinalFrame finalFrameExpected = new FinalFrame(states);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }

    @Test
    @DisplayName("막판 스페어")
    void bowl_spare() {
        finalFrame.bowl(4);
        finalFrame.bowl(6);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Spare(new Pins(0, Arrays.asList(4, 6))));
        FinalFrame finalFrameExpected = new FinalFrame(states);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }


    @Test
    @DisplayName("미스 점수 확인")
    void getScore_miss() {
        finalFrame.bowl(4);
        finalFrame.bowl(5);

        Score score = finalFrame.getScore();

        assertThat(score).isEqualTo(new Score(9, 0));
    }

    @Test
    @DisplayName("스트라이크 점수 확인")
    void getScore() {
        finalFrame.bowl(10);

        Score score = finalFrame.getScore();

        assertThat(score).isEqualTo(new Score(10, 2));
    }

    @Test
    @DisplayName("2스트라이크 점수 확인")
    void getScore_2strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        Score score = finalFrame.getScore();

        assertThat(score).isEqualTo(new Score(20, 1));
    }

    @Test
    @DisplayName("3스트라이크 점수 확인")
    void getScore_3strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        Score score = finalFrame.getScore();

        assertThat(score).isEqualTo(new Score(30, 0));
    }

    @Test
    @DisplayName("스페어 점수 확인")
    void getScore_spare() {
        finalFrame.bowl(4);
        finalFrame.bowl(6);

        Score score = finalFrame.getScore();

        assertThat(score).isEqualTo(new Score(10, 1));
    }

    @Test
    @DisplayName("다음 차례까지 점수 더하기")
    void addScore_1next() {
        finalFrame.bowl(4);
        Score lastScore = new Score(1, 1);

        Score score = finalFrame.addScore(lastScore);

        assertThat(score).isEqualTo(new Score(5, 0));
    }

    @Test
    @DisplayName("다다음 차례까지 점수 더하기")
    void addScore_2next() {
        finalFrame.bowl(4);
        finalFrame.bowl(5);
        Score lastScore = new Score(1, 2);

        Score score = finalFrame.addScore(lastScore);

        assertThat(score).isEqualTo(new Score(10, 0));
    }
}
