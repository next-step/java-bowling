package bowling.domain.frame;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("프레임 넘버 테스트")
public class FrameNumberTest {
    @DisplayName("프레임 넘버 생성")
    @Test
    public void createFrameNumber() {
        FrameNumber frameNumber = FrameNumber.first();

        assertThat(frameNumber.getNumber()).isEqualTo(1);
    }

    @DisplayName("다음 프레임 넘버 생성")
    @Test
    public void nextFrameNumber() {
        FrameNumber frameNumber = FrameNumber.first().next();

        assertThat(frameNumber.getNumber()).isEqualTo(2);
    }

    @DisplayName("마지막 프레임 넘버 확인")
    @ParameterizedTest
    @CsvSource(value = {"9:false", "10:true"}, delimiter = ':')
    public void isLast(int number, boolean isLast) {
        FrameNumber frameNumber = FrameNumber.of(number);

        assertThat(frameNumber.isLast()).isEqualTo(isLast);
    }

    @DisplayName("프레임넘버 1에서 10사이")
    @ParameterizedTest
    @CsvSource(value = {"-1", "0", "11"})
    public void invalidNumber(int invalidNumber) {
        assertThatThrownBy(() -> {
            FrameNumber.of(invalidNumber);
        }).isInstanceOf(InvalidFrameNumberException.class);
    }
}