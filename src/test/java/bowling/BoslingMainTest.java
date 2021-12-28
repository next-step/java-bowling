package bowling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class BoslingMainTest {

    @BeforeEach
    void init() {
        String input = "YGB\n" +
                "10\n" +
                "10\n" +
                "5 0\n" +
                "0 2\n" +
                "7 3\n" +
                "10\n" +
                "4 3\n" +
                "0 0\n" +
                "10\n" +
                "8 2 10\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
    }

    @DisplayName("통합 테스트 (볼링 점수판을 실행한다.)")
    @Test
    public void playBowling() {
        BowlingGame.play();
    }

}
