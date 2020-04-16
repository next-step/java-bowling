package bowling.domain.point;

import bowling.domain.point.Points;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static bowling.domain.point.Ordinal.FIRST;
import static bowling.domain.point.Ordinal.SECOND;
import static org.assertj.core.api.Assertions.assertThat;

public class OrdinalTest {
    @DisplayName("각 프레임의 Points와 투구가 몇 번째인지 인자로 제공하면, 해당 투구의 포인트를 반환한다.")
    @ParameterizedTest
    @CsvSource(value = {"10:0", "9:1", "6:2", "0:0"}, delimiter = ':')
    void returnIntegerPointWhenInputIsOrdinal(int firstPoint, int secondPoint) {
        //given
        Points points = Points.of(firstPoint, secondPoint);

        //when
        int first = FIRST.getPoint(FIRST, points);
        int second = SECOND.getPoint(SECOND, points);

        //then
        assertThat(first).isEqualTo(firstPoint);
        assertThat(second).isEqualTo(secondPoint);
    }
}