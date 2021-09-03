package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SinglePitchingTest {

    @ParameterizedTest(name = "객체 생성 정상 범위 {index} [{arguments}]")
    @ValueSource(ints = {0, 10})
    @DisplayName("객체 생성 성공")
    void construct(int fallenPin) {
        //given
        SinglePitching singlePitching = new SinglePitching(fallenPin);

        //when


        //then
        assertThat(singlePitching).isEqualTo(new SinglePitching(fallenPin));

    }

    @ParameterizedTest(name = "유효범위 벗어남 {index} [{arguments}]")
    @ValueSource(ints = {-1, 11})
    @DisplayName("객체 생성 실패 - 유효범위 벗어남")
    void construct_exception(int fallenPin) {
        //given

        //when

        //then
        assertThatThrownBy(() -> new SinglePitching(fallenPin)).isInstanceOf(SinglePitchingException.class)
                .hasMessage("1회 투구 점수 범위를 벗어났습니다. 유효범위 0 ~ 10. 현재 점수 : " + fallenPin);

    }

    @ParameterizedTest(name = "스트라이크 여부 {index} [{arguments}]")
    @CsvSource(value = {
            "9,false",
            "10,true"
    })
    @DisplayName("스트라이크 여부")
    void strike(int fallenPin, boolean expected) {
        //given
        SinglePitching singlePitching = new SinglePitching(fallenPin);

        //when
        boolean actual = singlePitching.isStrike();

        //then
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest(name = "스페어 여부 {index} [{arguments}]")
    @CsvSource(value = {
            "9,1,true",
            "1,9,true",
            "0,10,true",
            "10,0,false",
            "0,0,false"
    })
    @DisplayName("스페어 여부")
    void spare(int first, int second, boolean expected) {
        //given
        SinglePitching singlePitching = new SinglePitching(second);

        //when
        boolean actual = singlePitching.isSpare(new SinglePitching(first));

        //then
        assertThat(actual).isEqualTo(expected);

    }

    @ParameterizedTest(name = "투구 합산 {index} [{arguments}]")
    @CsvSource(value = {
            "9,1,10",
            "1,9,10",
            "0,10,10",
            "10,0,10",
            "0,0,0"
    })
    @DisplayName("두번의 투구 합산")
    void sum(int first, int second, int expected) {
        //given
        SinglePitching singlePitching = new SinglePitching(first);

        //when
        int actual = singlePitching.sum(new SinglePitching(second));

        //then
        assertThat(actual).isEqualTo(expected);
    }

}
