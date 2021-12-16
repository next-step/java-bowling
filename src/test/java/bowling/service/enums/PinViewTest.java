package bowling.service.convert.enums;

import bowling.domain.frame.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PinViewTest {

    @DisplayName("Pin으로 pinview를 조회할 수 있다.")
    @ParameterizedTest
    @CsvSource(value = {"0:GUTTER","1:ONE","2:TWO","3:THREE","4:FOUR",
            "5:FIVE","6:SIX","7:SEVEN","8:EIGHT","9:NINE","10:STRIKE"}, delimiter = ':')
    void valueOfTest(int pin, String pinViewName) {
        assertThat(PinView.valueOf(Pin.from(pin))).isEqualTo(PinView.valueOf(pinViewName));
    }

    @DisplayName("각 핀뷰에 맞는 문자열을 반환한다..")
    @ParameterizedTest
    @CsvSource(value = {"0:-","1:1","2:2","3:3","4:4",
            "5:5","6:6","7:7","8:8","9:9","10:X"}, delimiter = ':')
    void getDescTest(int pin, String desc) {
        assertThat(PinView.valueOf(Pin.from(pin)).getDesc()).isEqualTo(desc);
    }
}
