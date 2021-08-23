package bowling.view;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ResultViewTest {
    @Test
    void 두자릿수_테스트() {
        System.out.printf("%02d", 10);
    }

    @Test
    void 프레임테스트() {
        ResultView.printHeader();
    }

}