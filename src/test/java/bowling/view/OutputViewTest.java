package bowling.view;

import bowling.domain.Bowling;
import bowling.domain.Name;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("로컬 확인용")
class OutputViewTest {

    @Test
    void 생성() {
        Bowling bowling = new Bowling("kim");
        OutputView outputView = new OutputView();
        //1
        bowlingAndPrint(bowling, outputView, 5);
        bowlingAndPrint(bowling, outputView, 5);
        //2
        bowlingAndPrint(bowling, outputView, 2);
        bowlingAndPrint(bowling, outputView, 3);
        //3
        bowlingAndPrint(bowling, outputView, 5);
        bowlingAndPrint(bowling, outputView, 5);
        //4
        bowlingAndPrint(bowling, outputView, 10);
        //5
        bowlingAndPrint(bowling, outputView, 5);
        bowlingAndPrint(bowling, outputView, 3);
        //6
        bowlingAndPrint(bowling, outputView, 0);
        bowlingAndPrint(bowling, outputView, 10);
        //7
        bowlingAndPrint(bowling, outputView, 0);
        bowlingAndPrint(bowling, outputView, 10);
        //8
        bowlingAndPrint(bowling, outputView, 10);
        //9
        bowlingAndPrint(bowling, outputView, 10);
        //10
        bowlingAndPrint(bowling, outputView, 5);
        bowlingAndPrint(bowling, outputView, 5);
        bowlingAndPrint(bowling, outputView, 10);
    }

    private void bowlingAndPrint(Bowling bowling, OutputView outputView, int count) {
        bowling.bowl(count);
        outputView.print(bowling);
    }
}
