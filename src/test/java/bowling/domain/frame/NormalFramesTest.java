package bowling.domain.frame;

import bowling.domain.Score;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
public class NormalFramesTest {

    @Test
    public void NormalFrames를_만들_수_있다(){
        //given
        //when
        //then
        assertThat(NormalFrames.empty()).isEqualTo(NormalFrames.empty());
    }

    @Test
    public void NormalFrame을_추가할_수_있다(){
        //given
        NormalFrames normalFrames = NormalFrames.empty();
        //when
        normalFrames.add(NormalFrame.of(1, Score.first(0).withSecond(0), true));
        //then
        assertThat(normalFrames).isEqualTo(
                NormalFrames.empty().add(NormalFrame.of(1, Score.first(0).withSecond(0), true)));
    }
}
