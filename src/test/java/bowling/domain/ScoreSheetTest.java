package bowling.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

abstract class ScoreSheetTest {
    ScoreSheet scoreSheet;

    @DisplayName("현재 프래임이 끝나지 않은 상태에서 다음 프래임을 진행하려고 하면 exception 을 던진다")
    @Test
    void failToNextFrame(){
        Frame current = scoreSheet.nextFrame();
        assertThatThrownBy( () -> scoreSheet.nextFrame())
                .isInstanceOf(IllegalStateException.class);
    }

    @DisplayName("10개의 프레임까지 진행할 수 있다")
    @Test
    void isAllMarked1(){
        IntStream.range(0, 10)
                .forEach(idx -> {
                    Frame frame = scoreSheet.nextFrame();
                    while( !frame.isEnd() ) {
                        frame.mark(getCountOfFailDownPins());
                    }
                });
        assertThat(scoreSheet.isAllMarked()).isTrue();
        assertThat(scoreSheet.nextFrame()).isNull();
    }

    @DisplayName("남아 있는 프래임이 있는지 확인 할 수 있다")
    @Test
    void isAllMarked2(){
        IntStream.range(0, 5)
                .forEach(idx -> {
                    Frame frame = scoreSheet.nextFrame();
                    while( !frame.isEnd() ) {
                        frame.mark(getCountOfFailDownPins());
                    }
                });
        assertThat(scoreSheet.isAllMarked()).isFalse();
        assertThat(scoreSheet.nextFrame())
                .isNotNull()
                .hasFieldOrPropertyWithValue("frameNo", 6);

    }

    private int getCountOfFailDownPins(){
        Random random = new Random();
        return random.nextInt(6);
    }

    @DisplayName("볼링스코어시트를 출력할 수 있다")
    @Test
    void printScoreSheet(){
        IntStream.range(0, 8)
                .forEach(idx -> {
                    Frame frame = scoreSheet.nextFrame();
                    while( !frame.isEnd() ) {
                        frame.mark(getCountOfFailDownPins());
                    }
                });

        ScoreSheetReader reader = scoreSheet.getReader();
        String playerName = reader.readPlayName();
        int nameSpan = Math.max(playerName.length() + 2, 8);

        System.out.print(String.format("| %1$" + nameSpan + "s | ", "NAME"));
        String frameNos = IntStream.range(0, 10)
                .mapToObj(idx -> {
                    if( idx == 9 ){
                        return String.format("%1$4s ", String.valueOf(idx+1));
                    }
                    return String.format("%1$2s ", String.valueOf(idx+1));
                })
                .collect(Collectors.joining(" | "));
        System.out.print(frameNos);
        System.out.println(" |");

        System.out.print(String.format("|%1$" + nameSpan + "s  | ", playerName));
        while(!reader.isEOF()){
            FrameData frameData = reader.readFrameData();
            String marked = frameData.getPinMarks()
                    .stream()
                    .map( pinMark -> String.valueOf(pinMark.getCountOfFallDownPins()))
                    .collect(Collectors.joining("|"));
            int spanSize = 3;
            if( frameData.getFrameNo() == 10 ){
                spanSize = spanSize+2;
            }
            System.out.print(String.format("%1$" + spanSize + "s", marked));
            System.out.print(" | ");
        }

    }

}

class DefaultScoreSheetTest extends ScoreSheetTest {

    @BeforeEach
    void setUp(){
        scoreSheet = new DefaultScoreSheet(new Player("nio"));
    }

}