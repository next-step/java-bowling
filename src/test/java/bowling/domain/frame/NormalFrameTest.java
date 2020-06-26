package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.ScoreType;
import java.util.Arrays;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NormalFrameTest {

    @Test
    void play_then_strike() {
        Frame normalFrame = Frame.first();
        normalFrame.play(10);

        FrameResult expect = new FrameResult(Arrays.asList(10), Optional.ofNullable(ScoreType.STRIKE), Optional.empty());

        assertThat(normalFrame.getFrameResult()).isEqualTo(expect);
    }

    @DisplayName("strike후에 플레이 하면 예외 발생한다.")
    @Test
    void strike_and_play_then_throw_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.play(10);
        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void two_play() {
        Frame normalFrame = Frame.first();
        normalFrame.play(8);
        normalFrame.play(1);

        FrameResult expect = new FrameResult(Arrays.asList(8,1), Optional.ofNullable(ScoreType.MISS), Optional.of(9));

        assertThat(normalFrame.getFrameResult()).isEqualTo(expect);
    }

    @DisplayName("3번의 플레이를 하면 예외가 발생한다.")
    @Test
    void third_play_then_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.play(8);
        normalFrame.play(1);

        assertThatThrownBy(() -> normalFrame.play(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("한번치면(스트라이크 아님) 점수가 없다.")
    @Test
    void one_turn_has_none_score(){
        Frame normalFrame = Frame.first();
        normalFrame.play(8);

        assertThat(normalFrame.getScore()).isEqualTo(Optional.empty());
    }

    @DisplayName("miss면 점수가 바로 계산된다.")
    @Test
    void miss_score(){
        Frame normalFrame = Frame.first();
        normalFrame.play(8);
        normalFrame.play(1);

        assertThat(normalFrame.getScore()).isEqualTo(Optional.of(9));
    }

    @DisplayName("strike면 두번 투구 전이면 점수가 없다.")
    @Test
    void strike_score_empty(){
        Frame normalFrame = Frame.first();
        normalFrame.play(10);
        normalFrame.next();

        assertThat(normalFrame.getScore()).isEqualTo(Optional.empty());
    }

    @DisplayName("strike면 두번 투구후에 점수가 있다.")
    @Test
    void strike_score(){
        Frame normalFrame = Frame.first();
        normalFrame.play(10);

        Frame normalFrame2 = normalFrame.next();
        normalFrame2.play(3);
        normalFrame2.play(3);

        assertThat(normalFrame.getScore().isPresent()).isEqualTo(true);
    }
}
