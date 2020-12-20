package bowling.domain;

import bowling.domain.interfaces.State;
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
    @DisplayName("게임 종료 확인 - MISS")
    void isGameEnd_miss() {
        finalFrame.bowl(4);
        finalFrame.bowl(5);
        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 확인 - 한번 던짐")
    void isGameEnd_3times() {
        finalFrame.bowl(1);
        assertThat(finalFrame.isGameEnd()).isFalse();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스페어 한번 더 던짐")
    void isGameEnd_spare() {
        finalFrame.bowl(1);
        finalFrame.bowl(9);

        assertThat(finalFrame.isGameEnd()).isFalse();

        finalFrame.bowl(1);

        assertThat(finalFrame.isGameEnd()).isTrue();
    }

    @Test
    @DisplayName("게임 종료 확인 - 스트라이크 세번 던짐")
    void isGameEnd_strike3times() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        assertThat(finalFrame.isGameEnd()).isFalse();

        finalFrame.bowl(10);
        assertThat(finalFrame.isGameEnd()).isTrue();
    }


    @Test
    @DisplayName("막판 스트라이크 1회")
    void bowl_1strike() {
        finalFrame.bowl(10);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new FirstPitch());
        FinalFrame finalFrameExpected = new FinalFrame(states, 1);
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
        states.add(new FirstPitch());
        FinalFrame finalFrameExpected = new FinalFrame(states, 2);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }

    @Test
    @DisplayName("막판 스트라이크 3회 - 새로운 state 추가 x")
    void bowl_3strike() {
        finalFrame.bowl(10);
        finalFrame.bowl(10);
        finalFrame.bowl(10);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        states.add(new Strike(new Pins(0, Collections.singletonList(10))));
        FinalFrame finalFrameExpected = new FinalFrame(states, 3);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }

    @Test
    @DisplayName("막판 스페어 - state 한번 더 추가")
    void bowl_spare() {
        finalFrame.bowl(4);
        finalFrame.bowl(6);

        LinkedList<State> states = new LinkedList<>();
        states.add(new Spare(new Pins(0, Arrays.asList(4, 6))));
        states.add(new FirstPitch());
        FinalFrame finalFrameExpected = new FinalFrame(states, 2);
        assertThat(finalFrame).isEqualTo(finalFrameExpected);
    }
}