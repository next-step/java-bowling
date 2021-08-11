package bowling.domain.score.framescore;

import bowling.domain.frame.Frame;
import bowling.domain.score.TurnScore;
import bowling.domain.score.TurnScoreTest;
import bowling.domain.score.TurnScores;
import bowling.util.Pagination;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SpareTest {
    @DisplayName("합산 테스트")
    @Test
    void ofTest() {
        Frame frame = Frame.firstFrame();
        Frame nextFrame = Frame.firstFrame();

        frame.bowl(TurnScore.of(5));
        frame.bowl(TurnScore.of(5));
        nextFrame.bowl(TurnScore.of(5));

        Pagination<Frame> pagination = new Pagination<>(1, frame, Pagination.empty());
        pagination.newNextPage(nextFrame);

        int correctValue = 15;

        assertThat(
                Spare.of(pagination).value()
        ).isEqualTo(correctValue);
    }
}