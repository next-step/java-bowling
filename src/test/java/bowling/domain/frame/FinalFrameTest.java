package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FinalFrameTest {

    @DisplayName("첫번째 투구가 스트라이크일때 두번째 투구는 스트라이크가 아니어도 세번째 투구 가능하다")
    @Test
    void isEnd_first_strike() {
        // given
        Frame frame = FinalFrame.newInstance();

        // when
        frame.play(10);
        frame.play(5);

        // then
        assertThat(frame.isEnd()).isFalse();
    }

    @DisplayName("두번째 투구가 스페어이면 세번째 투구 가능하다")
    @Test
    void isEnd_second_spare() {
        // given
        Frame frame = FinalFrame.newInstance();

        // when
        frame.play(5);
        frame.play(5);

        // then
        assertThat(frame.isEnd()).isFalse();
    }

    @DisplayName("두번째 투구에서 스페어처리를 못하면 세번째 투구 불가능하다")
    @Test
    void isEnd_impossible_bonus() {
        // given
        Frame frame = FinalFrame.newInstance();

        // when
        frame.play(2);
        frame.play(3);

        // then
        assertThat(frame.isEnd()).isTrue();
    }

    @DisplayName("보너스 투구가 가능할때 마지막 프레임에서 핀을 30개 초과 쓰러뜨리면 예외가 발생한다")
    @Test
    void valid_max_pin() {
        // given
        Frame frame = FinalFrame.newInstance();

        // when
        frame.play(10);
        frame.play(10);

        // then
        assertThatThrownBy(() -> {
            frame.play(11);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("마지막 프레임은 핀을 최대 30개까지 쓰러뜨릴 수 있습니다.");
    }
}