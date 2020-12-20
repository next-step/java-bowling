package bowling.domain.frame;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import bowling.domain.score.ScoreType;
import bowling.domain.score.Scores;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FrameTest {

    @Test
    void strike_and_play_then_throw_exception() {
        Frame normalFrame = Frame.of(FrameNumber.create(0), new NormalPins());
        Scores scores = Scores.create();

        normalFrame.roll(10, scores);
        assertThatThrownBy(() -> normalFrame.roll(1, scores))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    void third_play_then_exception() {
        Frame normalFrame = Frame.of(FrameNumber.create(0), new NormalPins());
        Scores scores = Scores.create();

        normalFrame.roll(8, scores);
        normalFrame.roll(1, scores);

        assertThatThrownBy(() -> normalFrame.roll(1, scores))
            .isInstanceOf(IllegalStateException.class);
    }

    @Test
    @DisplayName("한번치면(스트라이크 아님) 점수가 없다.")
    void one_turn_has_none_score() {
        Frame normalFrame = Frame.of(FrameNumber.create(0), new NormalPins());
        Scores scores = Scores.create();
        normalFrame.roll(8, scores);

        assertThat(normalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    @DisplayName("strike면 두번 던지기 전이면 점수가 없다.")
    void strike_score_empty() {
        Frame normalFrame = Frame.of(FrameNumber.create(0), new NormalPins());
        Frame normalFrame2 = Frame.of(FrameNumber.create(1), new NormalPins());
        Scores scores = Scores.create();
        normalFrame.roll(10, scores);
        normalFrame2.roll(1, scores);

        assertThat(normalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    @DisplayName("strike면 두번 던진 후에 점수가 있다.")
    void strike_score() {
        Frame normalFrame = Frame.of(FrameNumber.create(0), new NormalPins());
        Scores scores = Scores.create();
        normalFrame.roll(10, scores);

        Frame normalFrame2 = Frame.of(FrameNumber.create(1), new NormalPins());
        normalFrame2.roll(3, scores);
        normalFrame2.roll(3, scores);

        assertThat(normalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.NORMAL);
    }

    @Test
    @DisplayName("miss는 두번의 라운드를 진행할수있다.")
    void miss() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(1, scores);
        finalFrame.roll(2, scores);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(3);
    }

    @Test
    @DisplayName("spare는 세번의 라운드를 진행할수있다.")
    void spare() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(1, scores);
        finalFrame.roll(9, scores);
        finalFrame.roll(9, scores);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(19);
    }

    @Test
    @DisplayName("strike는 세번의 라운드를 진행할수있다.")
    void strike() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();

        finalFrame.roll(10, scores);
        finalFrame.roll(9, scores);
        finalFrame.roll(9, scores);

        assertThat(finalFrame.hasTurn()).isEqualTo(false);
        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(28);
    }

    @Test
    void empty_score() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        assertThat(finalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_not_finished() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(1, scores);

        assertThat(finalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_strike() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(10, scores);

        assertThat(finalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void empty_score_spare() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(1, scores);
        finalFrame.roll(9, scores);

        assertThat(finalFrame.frameScore(scores).getScoreType()).isEqualTo(ScoreType.READY);
    }

    @Test
    void miss_score() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(1, scores);
        finalFrame.roll(2, scores);

        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(3);
    }

    @Test
    void three_strike() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(10, scores);
        finalFrame.roll(10, scores);
        finalFrame.roll(10, scores);

        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(30);
    }

    @Test
    void spare_strike() {
        Frame finalFrame = Frame.of(FrameNumber.create(9), FinalFramePins.create());
        Scores scores = Scores.create();
        finalFrame.roll(4, scores);
        finalFrame.roll(6, scores);
        finalFrame.roll(10, scores);

        assertThat(finalFrame.frameScore(scores).getValue()).isEqualTo(20);
    }
}
