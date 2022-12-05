package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.DoNotHaveEnoughPointsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class LastFrameTest {
    @Test
    @DisplayName("Frame에서 공던질 기회가 없어지면 null을 반환한다.")
    void test1() {
        Frame frame = new LastFrame(1);
        frame = frame.bowl(Point.of(10));
        frame = frame.bowl(Point.of(10));
        frame = frame.bowl(Point.of(10));
        assertThat(frame).isNull();
    }

    @Test
    @DisplayName("공던질 기회가 있으면 자기 자신을 반환한다.")
    void test2() {
        Frame frame = new LastFrame(1);
        frame = frame.bowl(Point.of(10));
        frame = frame.bowl(Point.of(10));
        assertThat(frame).isEqualTo(frame);
    }

    @Test
    @DisplayName("공던질 기회가 있다면 합계 계산에서 예외를 반환한다.")
    void test3() {
        Frame frame = new LastFrame(1);
        assertThatThrownBy(frame::calculateTotalPoint)
                .isInstanceOf(DoNotHaveEnoughPointsException.class);
    }

    @Test
    @DisplayName("STRIKE TEST : 10,10,10을 맞췄다면 합계 계산에서 30을 반환한다.")
    void test4() {
        Frame frame = new LastFrame(1);
        frame = frame.bowl(Point.of(10));
        frame = frame.bowl(Point.of(10));
        frame.bowl(Point.of(10));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(30));
    }

    @Test
    @DisplayName("SPARE TEST : 3,7,10을 맞췄다면 합계 계산에서 20을 반환한다.")
    void test5() {
        Frame frame = new LastFrame(1);
        frame = frame.bowl(Point.of(3));
        frame = frame.bowl(Point.of(7));
        frame.bowl(Point.of(10));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(20));
    }

    @Test
    @DisplayName("MISS TEST : 3,4을 맞췄다면 합계 계산에서 7을 반환한다.")
    void test6() {
        Frame frame = new LastFrame(1);
        frame = frame.bowl(Point.of(3));
        frame.bowl(Point.of(4));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(7));
    }

    @Test
    @DisplayName("마지막 Frame이 10을 가지고 있을때, 넘어온 Score가 10 포인트를 가지고 1번의 추가점수 기회를 가진다면 추가점수를 더하여 20, 0의 Score가 나온다.")
    void test7() {
        Frame frame = new LastFrame(1);
        frame.bowl(Point.of(10));
        Score score = frame.calculateExtraPoint(new Score(Point.of(10), 1));
        assertThat(score).isEqualTo(new Score(Point.of(20), 0));
    }

    @Test
    @DisplayName("현재 마지막 Frame이 10을 가지고 있을때, 넘어온 Score가 10 포인트를 가지고 2번의 추가점수 기회를 가진다면 추가점수 기회를 전부 소모하지 못하여 오류가 나온다.")
    void test8() {
        Frame frame = new LastFrame(1);
        frame.bowl(Point.of(10));
        assertThatThrownBy(() -> {
            frame.calculateExtraPoint(new Score(Point.of(10), 2));
        }).isInstanceOf(DoNotHaveEnoughPointsException.class);
    }

}
