package bowling.domain.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.frame.NormalFrame;
import bowling.domain.score.TurnScore;
import bowling.util.Pagination;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {
    private Pagination<Frame> pagination;

    @BeforeEach
    public void setUp() {
        Frame frame = NormalFrame.firstFrame();
        Frame nextFrame = NormalFrame.firstFrame();

        frame.bowl(TurnScore.of(5));
        frame.bowl(TurnScore.of(5));

        pagination = new Pagination<>(1, frame, Pagination.empty());
        pagination.newNextPage(nextFrame);
    }

    @DisplayName("Spare의 isCompleted는 항상 True다.")
    @Test
    void isCompleted() {
        assertThat(Strike.of(pagination).isCompleted())
                .isTrue();
    }

    @DisplayName("Spare는 다음 프레임의 첫 턴이 완료 되어야 출력 한다. - 다음 프레임 첫 턴 미완료")
    @Test
    void isNotShowScoreValueTest() {
        pagination.next().currentElement();

        assertThat(
                Spare.of(pagination).isShowScoreValue()
        ).isFalse();
    }

    @DisplayName("Spare는 다음 프레임의 첫 턴이 완료 되어야 출력 한다. - 다음 프레임 첫 턴 완료")
    @Test
    void isShowScoreValueTest() {
        Frame nextFrame = pagination.next().currentElement();
        nextFrame.bowl(TurnScore.of(5));

        assertThat(
                Spare.of(pagination).isShowScoreValue()
        ).isTrue();
    }
}