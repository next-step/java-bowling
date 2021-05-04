package bowling;

import bowling.domain.Pinfall;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class PinfallTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertThat(Pinfall.create(8)).isEqualTo(Pinfall.create(8));
    }

    @Test
    @DisplayName("넘어진 핀의 개수가 10이상이면 Exception")
    void Given_11Pinfall_When_New_Then_Exception() {
        assertThatExceptionOfType(IllegalPinFallException.class)
                .isThrownBy(() -> Pinfall.create(11))
                .withMessage("넘어진 핀의 개수가 잘못되었습니다");
    }

    @Test
    @DisplayName("Pinall 더하기 테스트")
    void When_Add_Then_Added() {
        Pinfall pinfall = Pinfall.create(8);
        assertThat(pinfall.add(Pinfall.create(2))).isEqualTo(Pinfall.createStrike());
    }

    @Test
    @DisplayName("넘어진 핀이 10개이면 Strike")
    void Given_Strike_When_isStrike_Then_True() {
        Pinfall pinfall = Pinfall.createStrike();
        assertThat(pinfall.isStrike()).isTrue();
    }
}
