package bowling;

import bowling.view.DosResultView;
import bowling.view.FakeInputView;
import bowling.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThatCode;

public class BowlingSolutionTest {
    @CsvSource(value = {
            "PJS|10,8,2,7,0,10,4,5,4,5,4,5,4,5,4,5,4,6,10", // 총 10프레임 19번의 투구 (스트라이크 -2, 마지막 스페어 + 1)
            "PJS|10,8,2,7,0,10,4,5,4,5,4,5,4,5,4,5,10,10" // 총 10프레임 18번의 투구 (스트라이크 -3, 마지막 스트라이크 + 1)
    }, delimiter = '|')
    @DisplayName("통합 테스트")
    @ParameterizedTest
    void run(String strName, String strTurnScores) {
        InputView fakeInputView = new FakeInputView(strName, toIntegerList(strTurnScores));

        assertThatCode(() ->
                new BowlingSolution(
                        fakeInputView, new DosResultView()
                ).run()
        ).doesNotThrowAnyException();
    }

    private List<Integer> toIntegerList(String strTurnScores) {
        return Arrays.stream(strTurnScores.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}