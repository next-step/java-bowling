package bowling.domain.pin;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PinStatusTest {

    @ParameterizedTest
    @CsvSource({
            "10,0,STRIKE", "10,5,STRIKE", "10,10,STRIKE",
            "0,10,SPARE", "5,5,SPARE", "9,1,SPARE",
            "0,0,MISS", "5,4,MISS", "9,0,MISS"})
    void plus(int firstNo, int secondNo, PinStatus status) {
        assertThat(PinStatus.plus(PinNo.of(firstNo), PinNo.of(secondNo)))
                .isSameAs(status);
    }
}
