package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BowlingTest {
    @Test
    @DisplayName("Class 생성 테스트")
    void When_New_Then_Created() {
        assertDoesNotThrow(() -> new Bowling());
    }

    @Test
    @DisplayName("초기 FrameNumber는 1 테스트")
    void Initial_FrameNumber_Is_One() {
        assertThat(new Bowling().frameNumber()).isEqualTo(new FrameNumber(1));
    }

    @Test
    @DisplayName("Strike를 던졌을 때 현재 프레임번호가 증가하는지 테스트")
    void Given_Strike_When_Roll_Then_FrameNumberIncreased() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(10));
        assertThat(bowling.frameNumber()).isEqualTo(new FrameNumber(2));
    }

    @Test
    @DisplayName("여러번 던졌을 때 해당 프레임의 결과가 업데이트 되는지 테스트")
    void Given_Roll_When_Result_Then_FrameResultUpdated() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(9));
        assertThat(bowling.result().result(new FrameNumber(1)).pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.NINE));

        bowling.roll(new Pinfall(1));
        assertThat(bowling.result().result(new FrameNumber(1)).pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE));

        bowling.roll(new Pinfall(10));
        assertThat(bowling.result().result(new FrameNumber(1)).pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.NINE, PointSymbol.SPARE));
        assertThat(bowling.result().result(new FrameNumber(2)).pointSymbols()).isEqualTo(new PointSymbols(PointSymbol.STRIKE));
    }

    @Test
    @DisplayName("10번 프레임까지 던졌을 때 볼링게임이 끝났는지 테스트")
    void Given_10thFrame_When_isDone_Then_True() {
        Frame frame10th = new FinalFrame(new Pinfall(1), new Pinfall(3));
        Bowling bowling = new Bowling(frame10th);
        assertThat(bowling.isDone()).isTrue();
    }

    @Test
    @DisplayName("9번째 프레임에서 공을 굴렸을 때 다음 프레임에서 보너스 공을 굴릴 수 있는지 테스트")
    void Given_9thFrameRoll_When_Result_Then_ValidResult() {
        Frame frame9th = new NormalFrame(new FrameNumber(9), new Pinfall(1));
        Bowling bowling = new Bowling(frame9th);
        bowling.roll(new Pinfall(2));

        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));

        assertThat(bowling.result().result(new FrameNumber(10)).pointSymbols()).isEqualTo(new PointSymbols(Arrays.asList(PointSymbol.STRIKE, PointSymbol.STRIKE, PointSymbol.STRIKE)));
    }

    @Test
    @DisplayName("9번째 프레임이 스트라이크이고, 10번째 프레임에서 9->1->9를 쓰러트렸을 때 Exception 안 남")
    void Given_9hFrameIsStrike_When_Roll9And1And9_Then_NoException() {
        Frame frame9th = new NormalFrame(new FrameNumber(9));
        Bowling bowling = new Bowling(frame9th);
        bowling.roll(new Pinfall(10));

        bowling.roll(new Pinfall(9));
        bowling.roll(new Pinfall(1));

        assertDoesNotThrow(() -> bowling.roll(new Pinfall(9)));
    }

    @Test
    @DisplayName("1번Frame이 끝났을 때 점수 계산 테스트")
    void Given_EndOfFirstFrame_When_Score_Then_Score() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(1));
        bowling.roll(new Pinfall(2));

        assertThat(bowling.result().result(new FrameNumber(1)).score()).isEqualTo(Score.create(3));
    }

    @Test
    @DisplayName("2번 Frame이 끝났을 때 점수 계산 테스트")
    void Given_EndOfSecondFrame_When_Score_Then_Score() {
        Bowling bowling = new Bowling();
        bowling.roll(new Pinfall(1));
        bowling.roll(new Pinfall(2));

        bowling.roll(new Pinfall(3));
        bowling.roll(new Pinfall(4));

        assertThat(bowling.result().result(new FrameNumber(2)).score()).isEqualTo(Score.create(10));
    }

    @Test
    @DisplayName("트리플일 때 첫 번째 프레임의 점수가 30점 테스트")
    void Given_Triple_When_FirstFrameScore_Then_30() {
        Bowling bowling = new Bowling();

        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));
        bowling.roll(new Pinfall(10));

        assertThat(bowling.result().result(new FrameNumber(1)).score()).isEqualTo(Score.create(30));
    }

    @Test
    @DisplayName("퍼펙트일 때 10번 프레임의 누적 점수는 300점")
    void Given_Perfect_When_FirstFrameScore_Then_300() {
        Bowling bowling = new Bowling();

        for (int i = 0; i < 12; i++) {
            bowling.roll(new Pinfall(10));
        }

        assertThat(bowling.result().result(new FrameNumber(10)).score()).isEqualTo(Score.create(300));
    }
}
