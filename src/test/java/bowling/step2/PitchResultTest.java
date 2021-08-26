package bowling.step2;

import bowling.step2.domain.PitchResult;
import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class PitchResultTest {

    @ParameterizedTest
    @CsvSource(value = {"NONE:10:STRIKE", "MISS:10:SPARE", "GUTTER:7:MISS"}, delimiter = ':')
    public void 결과_생성(PitchResult prePitchResult, int totalCount, PitchResult expected) {
        //given, when
        PitchResult resultOf = PitchResult.findResultOf(prePitchResult, totalCount);

        //then
        assertThat(resultOf).isEqualTo(expected);
    }
}
