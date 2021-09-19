package bowling.domain.frame;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

@SuppressWarnings("NonAsciiCharacters")
class FrameTypeTest {

    @Test
    public void size가_2이면_FrameType은_Normal이다() {
        //given
        //when
        //then
        assertThat(FrameType.from(2)).isEqualTo(FrameType.NORMAL);
    }

    @Test
    public void size가_3이면_FrameType은_Final이다() {
        //given
        //when
        //then
        assertThat(FrameType.from(3)).isEqualTo(FrameType.FINAL);

    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 1, 4, 10})
    public void size가_2_또는_3_이아니면_FrameType은_None이다(int value) {
        //given
        //when
        //then
        assertThat(FrameType.from(value)).isEqualTo(FrameType.NONE);
    }


}