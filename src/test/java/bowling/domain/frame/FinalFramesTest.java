package bowling.domain.frame;

import bowling.domain.score.FinalScore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class FinalFramesTest {

    @Test
    public void FinalFrames를_만들_수_있다() {
        //given
        //when
        //then
        assertThat(FinalFrames.empty()).isEqualTo(FinalFrames.empty());
    }

    @Test
    public void FinalFrame을_추가할_수_있다() {
        //given
        FinalFrame finalFrame = FinalFrame.of(FinalScore.from(10), true, false, false, false);
        //when
        FinalFrames finalFrames = FinalFrames.empty().add(finalFrame);
        //then
        assertThat(finalFrames).isEqualTo(
                FinalFrames.empty().add(finalFrame)
        );

    }
}
