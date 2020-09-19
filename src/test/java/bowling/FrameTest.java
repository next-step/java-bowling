package bowling;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.BDDAssertions.then;

class FrameTest {

    @Test
    @DisplayName("다음 프레임 생성 및 인스턴스 반환 검증")
    void getNextFrame() {
        Frame frame = Frame.of();

        then(frame.getNextFrame()).isEqualTo(null);

        Frame next = frame.next();
        then(frame.getNextFrame()).isEqualTo(next);
    }

    @Test
    @DisplayName("마지막 프레임 여부 검증")
    void isLastFrame() {
        Frame frame = Frame.of();

        frame.last();
        then(frame.isLastFrame()).isFalse();

        Frame last = frame.getNextFrame();
        then(last.isLastFrame()).isTrue();
    }

    @Test
    @DisplayName("투구 검증")
    void bowl() {
        Frame frame = Frame.of();

        frame.bowl(Pin.ofMin());
        then(frame.isDone()).isFalse();

        frame.bowl(Pin.ofMin());
        then(frame.isDone()).isTrue();
    }

    @Test
    @DisplayName("스트라이크 투구 검증")
    void bowlStrike() {
        Frame frame = Frame.of();

        frame.bowl(Pin.ofMax());

        then(frame.isDone()).isTrue();
    }

    @Test
    @DisplayName("투구 종료 시 예외 처리 검증")
    void bowlThrownEnd() {
        Frame frame = Frame.of();

        frame.bowl(Pin.ofMin());
        frame.bowl(Pin.ofMin());

        assertThatThrownBy(() -> frame.bowl(Pin.of(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("투구가 모두 완료된 상태 입니다.");
    }

    @Test
    @DisplayName("투구 점수 초과 시 예외 처리 검증")
    void bowlThrownOver() {
        Frame frame = Frame.of();

        frame.bowl(Pin.of(9));

        assertThatThrownBy(() -> frame.bowl(Pin.of(2)))
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("첫 번째 투구와 두 번째 투구의 합이 범위를 초과했습니다.");
    }

    @Test
    @DisplayName("첫 번째 투구 점수 검증")
    void getFirstPin() {
        Frame frame = Frame.of();
        Pin expected = Pin.of(2);

        frame.bowl(expected);

        then(frame.getFirstPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("두 번째 투구 점수 검증")
    void getSecondPin() {
        Frame frame = Frame.of();
        Pin expected = Pin.of(2);

        frame.bowl(Pin.ofMin());
        frame.bowl(expected);

        then(frame.getSecondPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("보너스 투구 점수 검증")
    void getBonusPin() {
        Frame frame = Frame.of();
        frame.last();
        Frame last = frame.getNextFrame();
        Pin expected = Pin.of(2);

        last.bowl(Pin.ofMax());
        last.bowl(Pin.ofMax());
        last.bowl(expected);

        then(last.getBonusPin()).isEqualTo(expected);
    }

    @Test
    @DisplayName("첫 번째 투구 완료 여부 검증")
    void isFirstDone() {
        Frame frame = Frame.of();

        then(frame.isFirstDone()).isFalse();

        frame.bowl(Pin.ofMax());
        then(frame.isFirstDone()).isTrue();
    }

    @Test
    @DisplayName("두 번째 투구 완료 여부 검증")
    void isSecondDone() {
        Frame frame = Frame.of();

        frame.bowl(Pin.ofMin());
        then(frame.isSecondDone()).isFalse();

        frame.bowl(Pin.ofMax());
        then(frame.isSecondDone()).isTrue();
    }

    @Test
    @DisplayName("보너스 투구 완료 여부 검증")
    void isBonusDone() {
        Frame frame = Frame.of();
        frame.last();
        Frame last = frame.getNextFrame();

        last.bowl(Pin.ofMax());
        then(last.isBonusDone()).isFalse();

        last.bowl(Pin.ofMax());
        then(last.isBonusDone()).isFalse();

        last.bowl(Pin.ofMax());
        then(last.isBonusDone()).isTrue();
    }

    @Test
    @DisplayName("완료 상태 확인 검증")
    void isDone() {
        Frame frame = Frame.of();

        frame.bowl(Pin.ofMin());
        then(frame.isDone()).isFalse();

        frame.bowl(Pin.ofMax());
        then(frame.isDone()).isTrue();
    }
}
