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
        normalFrames.add(NormalFrame.of(1, NormalScore.from(0), true));
        //then
        assertThat(normalFrames).isEqualTo(
                NormalFrames.empty().add(NormalFrame.of(1, NormalScore.from(0), true)));
    }

    @Test
    public void round에_맞는_Frame들을_가져올_수_있다() {
        //given
        NormalFrames normalFrames = NormalFrames.of(
                NormalFrame.of(1, NormalScore.from(0), false),
                NormalFrame.of(1, NormalScore.from(9), true),
                NormalFrame.of(2, NormalScore.from(10), false),
                NormalFrame.of(3, NormalScore.from(3), false),
                NormalFrame.of(3, NormalScore.from(4), true),
                NormalFrame.of(4, NormalScore.from(5), false),
                NormalFrame.of(4, NormalScore.from(5), true)
        );

        //when
        //then
        assertThat(normalFrames.findByFrame(3)).isEqualTo(NormalFrames.of(
                NormalFrame.of(3, NormalScore.from(3), false),
                NormalFrame.of(3, NormalScore.from(4), true)
        ));

    }
}
