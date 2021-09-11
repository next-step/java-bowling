package bowling.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SuppressWarnings("NonAsciiCharacters")
class FrameTest {

    @Test
    public void 첫번째_Frame을_생성할_수_있다() {
        //given
        //when
        //then
        assertThat(Frame.start(10)).isEqualTo(Frame.start(10));
    }

    @Test
    public void Frame의_첫시도에서_스트라이크를_치면_다음_Frame으로_넘어간다(){
        //given
        Frame frame = Frame.start(10);
        //when
        frame = frame.nextTurn(9);
        //then
        assertThat(frame).isEqualTo(Frame.of(2, 9, 0, false));
    }

    @Test
    public void Frame에서_첫시도에서_스트라이크가_아니면_다음시도를_한다(){
        //given
        Frame frame = Frame.start(9);
        //when
        frame = frame.nextTurn(1);
        //then
        assertThat(frame).isEqualTo(Frame.of(1, 9, 1, true));
    }

    @Test
    public void Frame에서_두번째_시도에서_다음시도를하면_다음Frame이_된다(){
        //given
        Frame frame = Frame.start(9).nextTurn(0);
        //when
        frame = frame.nextTurn(3);
        //then
        assertThat(frame).isEqualTo(Frame.of(2, 3, 0, false));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 12, 13})
    public void Frame의_점수는_0에서_10점까지의_점수만을_가질수_있다(int score) {
        //given
        //when
        //then
        assertThatThrownBy(() -> Frame.start(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("잘못된 점수를 입력하였습니다.");
    }

    @ParameterizedTest
    @ValueSource(ints = {2, 3, 4})
    public void Frame의_두번째_시도후_합산이_10점을_넘으면_익셉션이_발생한다(int score){
        //given
        Frame frame = Frame.start(9);
        //when
        //then
        assertThatThrownBy(() -> frame.nextTurn(score))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 프레임의 합계는 10점을 넘을 수 없습니다.");
    }
}
