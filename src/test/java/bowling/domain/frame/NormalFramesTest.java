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

    @Test
    public void round에_맞는_Frame들을_가져올_수_있다() {
        //given
        NormalFrames normalFrames = NormalFrames.of(
                NormalFrame.of(1, NormalScore.first(0), 1),
                NormalFrame.of(1, NormalScore.first(9), 2),
                NormalFrame.of(2, NormalScore.first(10), 1),
                NormalFrame.of(3, NormalScore.first(3), 1),
                NormalFrame.of(3, NormalScore.first(4), 2),
                NormalFrame.of(4, NormalScore.first(5), 1),
                NormalFrame.of(4, NormalScore.first(5), 2)
        );

        //when
        //then
        assertThat(normalFrames.findByFrame(3)).isEqualTo(NormalFrames.of(
                NormalFrame.of(3, NormalScore.first(3), 1),
                NormalFrame.of(3, NormalScore.first(4), 2)
        ));

    }
}
