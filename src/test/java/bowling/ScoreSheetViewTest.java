package bowling;

import bowling.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ScoreSheetViewTest {

    @DisplayName("ScoreSheetReader 로 출력코드 작성이 용이한지 확인하기 위한 테스트코드")
    @Test
    void test(){
        ScoreSheet scoreSheet = new DefaultScoreSheet(new Player("NIO"));
        // 1 기본 (Miss)
        Frame frame = scoreSheet.nextFrame();
        frame.mark(8);
        frame.mark(1);

        // 2 스페어
        frame = scoreSheet.nextFrame();
        frame.mark(9);
        frame.mark(1);

        // 3 스트라이크
        frame = scoreSheet.nextFrame();
        frame.mark(10);

        // 4 Gutter
        frame = scoreSheet.nextFrame();
        frame.mark(8);
        frame.mark(0);

        // 5 Double Gutter
        frame = scoreSheet.nextFrame();
        frame.mark(0);
        frame.mark(0);

        // 6 Reverse Gutter
        frame = scoreSheet.nextFrame();
        frame.mark(0);
        frame.mark(8);

        // 7
        frame = scoreSheet.nextFrame();
        frame.mark(7);
        frame.mark(2);

        // 8
        frame = scoreSheet.nextFrame();
        frame.mark(9);
        frame.mark(0);

        // 9
        frame = scoreSheet.nextFrame();
        frame.mark(9);
        frame.mark(0);

        // 10
        frame = scoreSheet.nextFrame();
        frame.mark(9);
        frame.mark(1);
        frame.mark(10);

        printScoreSheet(scoreSheet.getReader());
    }

    private void printScoreSheet(ScoreSheetReader reader) {
        String playerName = reader.readPlayName();
        int nameSpan = Math.max(playerName.length() + 2, 8);

        System.out.print(String.format("| %1$" + nameSpan + "s | ", "NAME"));
        String frameNos = IntStream.range(0, 10)
                .mapToObj(idx -> {
                    if (idx == 9) {
                        return String.format("%1$4s ", String.valueOf(idx + 1));
                    }
                    return String.format("%1$2s ", String.valueOf(idx + 1));
                })
                .collect(Collectors.joining(" | "));
        System.out.print(frameNos);
        System.out.println(" |");

        System.out.print(String.format("|%1$" + nameSpan + "s  | ", playerName));
        StringBuilder scoreString = new StringBuilder();
        int prevTotalScore = 0;
        int currentTotalScore = 0;
        while (!reader.isEOF()) {
            FrameInfo frameInfo = reader.readFrameInfo();
            String marked = frameInfo.getSymbols()
                    .stream()
                    .collect(Collectors.joining("|"));
            int spanSize = 3;
            if (frameInfo.getFrameNo() == 10) {
                spanSize = spanSize + 2;
            }
            System.out.print(String.format("%1$" + spanSize + "s", marked));
            System.out.print(" | ");

            if( frameInfo.getScore() == FrameScore.unknown ) {
                IntStream.range(0, spanSize).forEach(idx -> scoreString.append(" "));
                scoreString.append(" | ");
            } else {
                currentTotalScore = prevTotalScore + frameInfo.getScore().getIntValue();
                scoreString.append(String.format("%1$" + spanSize + "d", currentTotalScore))
                        .append(" | ");
                prevTotalScore = currentTotalScore;
            }
        }

        System.out.println();
        System.out.print(String.format("|%1$" + nameSpan + "s  | %2$s", " ", scoreString.toString()));
        System.out.println();
    }
}
