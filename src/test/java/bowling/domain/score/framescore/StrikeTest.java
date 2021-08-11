package bowling.domain.score.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScore;
import bowling.util.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StrikeTest {
    @DisplayName("합산 테스트")
    @Test
    void ofTest() {
        Frame frame = Frame.firstFrame();
        Frame nextFrame = Frame.firstFrame();

        frame.bowl(TurnScore.of(10));
        nextFrame.bowl(TurnScore.of(5));
        nextFrame.bowl(TurnScore.of(5));

        Pagination<Frame> pagination = new Pagination<>(1, frame, Pagination.empty());
        pagination.newNextPage(nextFrame);

        int correctValue = 20;

        assertThat(
                Strike.of(pagination).value()
        ).isEqualTo(correctValue);
    }
}