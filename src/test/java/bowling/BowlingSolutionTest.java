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
            "PJS,KYJ|10,5,4,8,1,2,2,8,1,1,1,10,10,4,4,5,5,4,4,5,5,4,4,5,5,4,4,5,5,4,4,5,5,4,4,6,4,10", // 총 10프레임 38번의 투구 (스트라이크 -2, 마지막 스페어 + 1)
            "PJS|10,8,2,8,1,10,4,5,4,5,4,5,4,5,4,5,10,5", // 총 10프레임 18번의 투구 (스트라이크 -3, 마지막 스트라이크 + 1)
            "PJS|10,8,2,8,1,10,4,5,4,5,4,5,4,5,4,5,4,5" // 총 10프레임 18번의 투구 (스트라이크 -3, 마지막 스트라이크 + 1)
    }, delimiter = '|')
    @DisplayName("통합 테스트")
    @ParameterizedTest
    void run(String strNames, String strTurnScores) {
        List<String> playerNames = toPlayerNames(strNames);
        InputView fakeInputView = new FakeInputView(playerNames, toTurnScores(strTurnScores));

        assertThatCode(() ->
                new BowlingSolution(
                        fakeInputView, new DosResultView()
                ).run()
        ).doesNotThrowAnyException();
    }

    private List<String> toPlayerNames(String strNames) {
        return Arrays.stream(strNames.split(","))
                .collect(Collectors.toList());
    }

    private List<Integer> toTurnScores(String strTurnScores) {
        return Arrays.stream(strTurnScores.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }
}