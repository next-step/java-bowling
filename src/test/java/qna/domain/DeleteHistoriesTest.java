package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteHistoriesTest {

    public static final DeleteHistories HISTORIES = DeleteHistories.of(Arrays.asList(DeleteHistoryTest.D1_Q, DeleteHistoryTest.D2_A));

    @DisplayName("Histories 생성자 테스트")
    @ParameterizedTest
    @MethodSource("provideHistories")
    void historyConstructorTest(final DeleteHistory d1, final DeleteHistory d2, final DeleteHistories expected) {
        DeleteHistories deleteHistories = DeleteHistories.of(Arrays.asList(d1, d2));
        assertThat(deleteHistories).isEqualTo(expected);
    }

    private static Stream<Arguments> provideHistories() {
        return Stream.of(
                Arguments.of(DeleteHistoryTest.D1_Q, DeleteHistoryTest.D2_A, HISTORIES)
        );
    }
}
