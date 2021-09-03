package bowling.domain.frame;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class NormalFrameTest {

    @Test
    @DisplayName("객체 생성 - 스트라이크")
    void construct_strike() throws Exception {
        //given
        NormalFrame normalFrame = new NormalFrame(10);

        //when


        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(10));

    }

    @Test
    @DisplayName("객체 생성 - 스트라이크 아닌 경우")
    void construct_non_strike() throws Exception {
        //given
        NormalFrame normalFrame = new NormalFrame(5, 5);

        //when


        //then
        assertThat(normalFrame).isEqualTo(new NormalFrame(5, 5));

    }

    @ParameterizedTest(name = "객체 생성 실패 {index} [{arguments}]")
    @CsvSource(value = {
            "10,1",//초구 스트라이크 이후 투구
            "11,1",//초구 쓰러트린 핀 수 초과
            "1,10",//2구 쓰러트린 핀 수 초과
            "-1,0"//쓰러트린 핀 수 마이너스
    })
    @DisplayName("객체 생성 실패")
    void construct_exception(int first, int second) throws Exception {
        //given

        //when
        ThrowableAssert.ThrowingCallable actual = () -> new NormalFrame(first, second);


        //then
        assertThatThrownBy(actual).isInstanceOf(PitchingException.class);

    }




}
