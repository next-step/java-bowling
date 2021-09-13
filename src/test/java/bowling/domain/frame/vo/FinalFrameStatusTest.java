package bowling.domain.frame.vo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class FinalFrameStatusTest {

    @Test
    public void FinalFrameStatus를_만들_수_있다() {
        //given
        //when
        //then
        assertThat(FinalFrameStatus.of(true, false))
                .isEqualTo(FinalFrameStatus.of(true, false));
    }

    @Test
    public void _3번째_시도를_할_수_있는_동시에_끝날_수_없다() {
        //given
        //when
        //then
        assertThatThrownBy(() -> FinalFrameStatus.of(true, true))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("아직 시도가 남았기 때문에 끝낼 수 없습니다.");
    }

}