package bowling.domain.state;

import bowling.domain.Pin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("FirstBowl 테스트")
class FirstBowlTest {

    @ParameterizedTest(name = "{0}개가 쓰러진 상태에서 {1}개를 쓰러뜨린 경우 스패어다.")
    @CsvSource({"0,10", "1,9", "9,1"})
    void firstToSpare(int first, int second) {

        final FirstBowl firstBowl = new FirstBowl(new Pin(first));

        final State result = firstBowl.bowl(new Pin(second));

        assertThat(result).isInstanceOf(Spare.class);
    }

    @ParameterizedTest(name = "{0}개가 쓰러진 상태에서 {1}개를 쓰러뜨린 경우 미스다.")
    @CsvSource({"0,0", "0,1", "9,0", "1,8"})
    void firstToMiss(int first, int second) {

        final FirstBowl firstBowl = new FirstBowl(new Pin(first));

        final State result = firstBowl.bowl(new Pin(second));

        assertThat(result).isInstanceOf(Miss.class);
    }
}
