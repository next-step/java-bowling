package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class DeleteHistoryTest {

    public static final DeleteHistory D1_Q = DeleteHistory.of(ContentType.QUESTION, 1L, UserTest.SANJIGI, LocalDateTime.now());
    public static final DeleteHistory D2_A = DeleteHistory.of(ContentType.ANSWER, 2L, UserTest.JAVAJIGI, LocalDateTime.now());

    @DisplayName("History 생성자 테스트")
    @ParameterizedTest
    @MethodSource("provideHistory")
    void historyConstructorTest(final ContentType contentType, final long no, final User user, final DeleteHistory expected) {
        DeleteHistory deleteHistory = DeleteHistory.of(contentType, no, user, LocalDateTime.now());
        assertThat(deleteHistory).isEqualTo(expected);
    }

    private static Stream<Arguments> provideHistory() {
        return Stream.of(
                Arguments.of(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, DeleteHistory.of(ContentType.QUESTION, 1L, UserTest.JAVAJIGI, LocalDateTime.now())),
                Arguments.of(ContentType.ANSWER, 2L, UserTest.SANJIGI, DeleteHistory.of(ContentType.ANSWER, 2L, UserTest.SANJIGI, LocalDateTime.now()))
        );
    }

}
