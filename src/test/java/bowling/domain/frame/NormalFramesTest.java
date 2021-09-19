package bowling.domain.frame;

import bowling.domain.score.NormalScore;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class NormalFramesTest {

    @Test
    public void NormalFrames를_만들_수_있다() {
        //given
        //when
        //then
        assertThat(NormalFrames.empty()).isEqualTo(NormalFrames.empty());
    }

    @Test
    public void NormalFrame을_추가할_수_있다() {
        //given
        NormalFrames normalFrames = NormalFrames.empty();
        //when
        normalFrames.add(NormalFrame.of(1, NormalScore.first(0), 2));
        //then
        assertThat(normalFrames).isEqualTo(
                NormalFrames.empty().add(NormalFrame.of(1, NormalScore.first(0), 2)));
    }

}
