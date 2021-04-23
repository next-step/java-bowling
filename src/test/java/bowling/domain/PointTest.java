package bowling.domain;

import bowling.domain.point.Point;
import bowling.exception.CustomException;
import bowling.exception.ErrorCode;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PointTest {

    private Point pre;
    private Point cur;

    @BeforeEach
    void setup() {
        pre = new Point();
        cur = new Point();
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10})
    @DisplayName("첫번째 점수는 0~10점으로 생성 가능")
    void firstPointInBoxedRange0to10(int input) {
        cur.throwBall(input);
        assertThat(cur.point()).isEqualTo(input);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -2, 11, 13, 44, -65, 106, -297, 0x7fffffff})
    @DisplayName("점수로 음수나 10이 넘는 숫자를 입력하면 INVALID_POINT를 던짐")
    void pointNotInRangeThrowsException(int input) {
        CustomException customException = assertThrows(CustomException.class, () -> cur.throwBall(input));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:false", "1:false", "2:false", "3:false", "4:false", "5:false",
            "6:false", "7:false", "8:false", "9:false", "10:true"}, delimiter = ':')
    @DisplayName("첫번째 점수의 스트라이크처리 여부를 판단 가능")
    void verifiesFirstPointStrikeOrNot(int input, boolean didStrike) {
        cur.throwBall(input);
        assertThat(cur.striked()).isEqualTo(didStrike);
    }

    @ParameterizedTest
    @CsvSource(value = {"0:2", "1:4", "2:8", "3:7", "4:4", "5:3",
            "6:3", "7:3", "8:0", "9:1", "10:0"}, delimiter = ':')
    @DisplayName("두번째 점수와 첫번째 점수의 합이 10이하이고 0이상일때 점수 생성 가능")
    void verifiesSecondPoint(int curPoint, int prePoint) {
        pre.throwBall(prePoint);
        cur.throwBall(curPoint);
        assertThat(cur.point()).isEqualTo(curPoint);
    }

    @ParameterizedTest
    @CsvSource(value = {"-1:2", "-1:4", "-2:8", "-3:7", "14:4", "35:3"}, delimiter = ':')
    @DisplayName("두번째 점수로 0~10이 아닌 수를 입력하면 INVALID_POINT를 던짐")
    void secondPointNotInRangeThrowsException(int curPoint, int prePoint) {
        pre.throwBall(prePoint);
        CustomException customException = assertThrows(CustomException.class, () -> cur.throwBall(pre, curPoint));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT);
    }

    @ParameterizedTest
    @CsvSource(value = {"9:2", "8:4", "10:8", "4:7", "9:4", "8:3"}, delimiter = ':')
    @DisplayName("두 점수의 합이 10을 넘으면 INVALID_POINT_SUM을 던짐")
    void pointSumOver10ThrowsException(int curPoint, int prePoint) {
        pre.throwBall(prePoint);
        CustomException customException = assertThrows(CustomException.class, () -> cur.throwBall(pre, curPoint));
        assertThat(customException.errorCode()).isEqualTo(ErrorCode.INVALID_POINT_SUM);
    }

    @ParameterizedTest
    @CsvSource(value = {"1:9", "2:8", "3:7", "4:6", "5:5",
            "6:4", "7:3", "8:2", "9:1"}, delimiter = ':')
    @DisplayName("스페어처리를 했는지 판단이 가능함")
    void pointSumIsSpare(int curPoint, int prePoint) {
        pre.throwBall(prePoint);
        cur.throwBall(curPoint);
        assertThat(cur.spared(pre)).isTrue();
    }
}
