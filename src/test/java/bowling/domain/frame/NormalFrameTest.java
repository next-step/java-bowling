package bowling.domain.frame;

import bowling.domain.Point;
import bowling.domain.Score;
import bowling.exception.DoNotHaveEnoughPointsException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class NormalFrameTest {

    @Test
    @DisplayName("Ready 상태에서 한번에 10개를 쓰러트리면 다음 프레임으로 넘어간다.")
    void test1() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(10));
        assertThat(frame).isNotEqualTo(nextFrame);
        assertThat(nextFrame.getOrder()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ready 상태에서 총 10개를 쓰러트리면 다음 프레임으로 넘어간다.")
    void test2() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(5));
        nextFrame = nextFrame.bowl(Point.of(5));
        assertThat(frame).isNotEqualTo(nextFrame);
        assertThat(nextFrame.getOrder()).isEqualTo(2);
    }

    @Test
    @DisplayName("Ready 상태에서 10개 이하를 쓰러트리면 현재 프레임에 남는다.")
    void test3() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(5));
        assertThat(frame).isEqualTo(nextFrame);
        assertThat(nextFrame.getOrder()).isEqualTo(1);
    }

    @Test
    @DisplayName("10개를 전부 못 쓰러트려도 한 프레임당 2번의 기회가 지나가면 다음 프레임으로 넘어간다.")
    void test4() {
        Frame frame = new NormalFrame(1);
        Point two = Point.of(2);

        Frame nextFrame = frame.bowl(two);
        nextFrame = nextFrame.bowl(two);

        assertThat(frame).isNotEqualTo(nextFrame);
        assertThat(nextFrame.getOrder()).isEqualTo(2);
    }

    @Test
    @DisplayName("Strike를 9번 치면 LastFrame에 도착한다.")
    void test5() {
        Frame frame = new NormalFrame(1);
        Point ten = Point.of(10);

        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);
        frame = frame.bowl(ten);

        assertThat(frame).isInstanceOf(LastFrame.class);
        assertThat(frame.getOrder()).isEqualTo(10);
    }

    @Test
    @DisplayName("10개를 전부 못 쓰러트려도 한 프레임당 2번의 기회가 지나가면 최종적으로 LastFrame에 도착한다.")
    void test6() {
        Frame frame = new NormalFrame(1);
        Point two = Point.of(2);

        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);
        frame = frame.bowl(two);

        assertThat(frame).isInstanceOf(LastFrame.class);
        assertThat(frame.getOrder()).isEqualTo(10);
    }

    @Test
    @DisplayName("STRIKE 테스트 : 10, 3, 4를 차례로 입력 받을 경우 첫번째 프레임의 점수는 17")
    void test7() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(10));
        nextFrame = nextFrame.bowl(Point.of(3));
        nextFrame.bowl(Point.of(4));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(17));
    }

    @Test
    @DisplayName("SPARE 테스트 : 3, 7, 4를 차례로 입력 받을 경우 첫번째 프레임의 점수는 14")
    void test8() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(3));
        nextFrame = nextFrame.bowl(Point.of(7));
        nextFrame.bowl(Point.of(4));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(14));
    }

    @Test
    @DisplayName("MISS 테스트 : 3, 4를 차례로 입력 받을 경우 첫번째 프레임의 점수는 7")
    void test9() {
        Frame frame = new NormalFrame(1);
        Frame nextFrame = frame.bowl(Point.of(3));
        nextFrame.bowl(Point.of(4));
        assertThat(frame.calculateTotalPoint()).isEqualTo(Point.of(7));
    }

    @Test
    @DisplayName("현재 마지막 Frame이 10을 가지고 있을때, 넘어온 Score가 10 포인트를 가지고 1번의 추가점수 기회를 가진다면 추가점수를 더하여 20, 0의 Score가 나온다.")
    void test10() {
        Frame frame = new NormalFrame(1);
        frame.bowl(Point.of(10));
        Score score = frame.calculateExtraPoint(new Score(Point.of(10), 1));
        assertThat(score).isEqualTo(new Score(Point.of(20), 0));
    }

    @Test
    @DisplayName("현재 마지막 Frame이 10을 가지고 있을때, 넘어온 Score가 10 포인트를 가지고 2번의 추가점수 기회를 가진다면 추가점수 기회를 전부 소모하지 못하여 오류가 나온다.")
    void test11() {
        Frame frame = new NormalFrame(1);
        frame.bowl(Point.of(10));
        assertThatThrownBy(() -> {
            frame.calculateExtraPoint(new Score(Point.of(10), 2));
        }).isInstanceOf(DoNotHaveEnoughPointsException.class);
    }

}
