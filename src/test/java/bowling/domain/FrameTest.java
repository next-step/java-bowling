package bowling.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FrameTest {
    @DisplayName("스트라이크의 경우 계속 진행여부 값은 false 를 반환한다.")
    @Test
    void isContinue_normal_strike() {
        Frame frame = new Frame(1);
        frame.pitch(10);
        assertThat(frame.isContinue()).isFalse();
    }

    @DisplayName("스페어 처리의 경우 계속 진행여부 값은 false 를 반환한다.")
    @Test
    void isContinue_normal_spare() {
        Frame frame = new Frame(1);
        frame.pitch(8);
        frame.pitch(2);
        assertThat(frame.isContinue()).isFalse();
    }

    @DisplayName("오픈의 경우 계속 진행여부 값은 false 를 반환한다.")
    @Test
    void isContinue_normal_open() {
        Frame frame = new Frame(1);
        frame.pitch(7);
        frame.pitch(2);
        assertThat(frame.isContinue()).isFalse();
    }

    @DisplayName("처음 투구한 경우 계속 진행여부 값은 true 를 반환한다.")
    @Test
    void isContinue_normal_one() {
        Frame frame = new Frame(1);
        frame.pitch(7);
        assertThat(frame.isContinue()).isTrue();
    }

    @DisplayName("마지막 프레임, 스트라이크의 경우 계속 진행여부 값은 true 를 반환한다.")
    @Test
    void isContinue_final_strike() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(0);
        assertThat(frame.isContinue()).isTrue();
    }

    @DisplayName("마지막 프레임, 스페어 처리의 경우 계속 진행여부 값은 true 를 반환한다.")
    @Test
    void isContinue_final_spare() {
        Frame frame = new Frame(10);
        frame.pitch(8);
        frame.pitch(2);
        assertThat(frame.isContinue()).isTrue();
    }

    @DisplayName("마지막 프레임, 오픈의 경우 계속 진행여부 값은 false 를 반환한다.")
    @Test
    void isContinue_final_open() {
        Frame frame = new Frame(10);
        frame.pitch(7);
        frame.pitch(2);
        assertThat(frame.isContinue()).isFalse();
    }

    @DisplayName("마지막 프레임, 처음 투구한 경우 계속 진행여부 값은 true 를 반환한다.")
    @Test
    void isContinue_final_one() {
        Frame frame = new Frame(10);
        frame.pitch(7);
        assertThat(frame.isContinue()).isTrue();
    }

    @DisplayName("마지막 프레임, 더블의 경우 계속 진행여부 값은 true 를 반환한다.")
    @Test
    void isContinue_final_double() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(10);
        assertThat(frame.isContinue()).isTrue();
    }

    @DisplayName("마지막 프레임, 세번 투구할 경우 계속 진행여부 값은 false 를 반환한다.")
    @Test
    void isContinue_final_three() {
        Frame frame = new Frame(10);
        frame.pitch(10);
        frame.pitch(0);
        frame.pitch(9);
        assertThat(frame.isContinue()).isFalse();
    }
}
