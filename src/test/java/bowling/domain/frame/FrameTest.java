package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.ScoreType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    void strike_and_play_then_throw_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.roll(10);
        assertThatThrownBy(() -> normalFrame.roll(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void third_play_then_exception() {
        Frame normalFrame = Frame.first();

        normalFrame.roll(8);
        normalFrame.roll(1);

        assertThatThrownBy(() -> normalFrame.roll(1))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("한번치면(스트라이크 아님) 점수가 없다.")
    void one_turn_has_none_score() {
        Frame normalFrame = Frame.first();
        normalFrame.roll(8);

        assertThat(normalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    @DisplayName("strike면 두번 던지기 전이면 점수가 없다.")
    void strike_score_empty() {
        Frame normalFrame = Frame.first();
        normalFrame.roll(10);
        normalFrame.next();

        assertThat(normalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    @DisplayName("strike면 두번 던진 후에 점수가 있다.")
    void strike_score() {
        Frame normalFrame = Frame.first();
        normalFrame.roll(10);

        Frame normalFrame2 = normalFrame.next();
        normalFrame2.roll(3);
        normalFrame2.roll(3);

        assertThat(normalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.NORMAL);
    }

    @Test
    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    void miss() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(1);
        finalFrame.roll(2);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore().getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    void spare() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(1);
        finalFrame.roll(9);
        finalFrame.roll(9);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore().getValue()).isEqualTo(19);
    }

    @Test
    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    void strike() {
        Frame finalFrame = Frame.first().last();

        finalFrame.roll(10);
        finalFrame.roll(9);
        finalFrame.roll(9);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore().getValue()).isEqualTo(28);
    }

    @Test
    void empty_score() {
        Frame finalFrame = Frame.first().last();
        assertThat(finalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_not_finished() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(1);

        assertThat(finalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_strike() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(10);

        assertThat(finalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_spare() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(1);
        finalFrame.roll(9);

        assertThat(finalFrame.frameScore().getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void miss_score() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(1);
        finalFrame.roll(2);

        assertThat(finalFrame.frameScore().getValue()).isEqualTo(3);
    }

    @Test
    void three_strike() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(10);
        finalFrame.roll(10);
        finalFrame.roll(10);

        assertThat(finalFrame.frameScore().getValue()).isEqualTo(30);
    }

    @Test
    void spare_strike() {
        Frame finalFrame = Frame.first().last();
        finalFrame.roll(4);
        finalFrame.roll(6);
        finalFrame.roll(10);

        assertThat(finalFrame.frameScore().getValue()).isEqualTo(20);
    }
}
