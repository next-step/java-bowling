package bowling.domain.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.TurnScore;
import bowling.util.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {
    private Pagination<Frame> pagination;

    @BeforeEach
    public void setUp() {
        Frame frame = NormalFrame.firstFrame();
        Frame nextFrame = NormalFrame.firstFrame();

        frame.bowl(TurnScore.of(10));

        pagination = new Pagination<>(1, frame, Pagination.empty());
        pagination.newNextPage(nextFrame);
    }

    @DisplayName("Strike의 isCompleted는 항상 True다.")
    @Test
    void isCompleted() {
        assertThat(Strike.of(pagination).isCompleted())
                .isTrue();
    }

    @DisplayName("Strike는 다음 프레임이 완료 되어야 출력 한다. - 다음 프레임 미 완료")
    @Test
    void isNotShowScoreValueTest() {
        Frame nextFrame = pagination.next().currentElement();
        nextFrame.bowl(TurnScore.of(5));

        assertThat(
                Strike.of(pagination).isShowScoreValue()
        ).isFalse();
    }

    @DisplayName("Strike는 다음 프레임이 완료 되어야 출력 한다. - 다음 프레임 완료")
    @Test
    void isShowScoreValueTest() {
        Frame nextFrame = pagination.next().currentElement();
        nextFrame.bowl(TurnScore.of(5));
        nextFrame.bowl(TurnScore.of(5));

        assertThat(
                Strike.of(pagination).isShowScoreValue()
        ).isTrue();
    }
}