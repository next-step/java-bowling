package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import qna.CannotDeleteException;


import java.time.LocalDateTime;
import java.util.Collections;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswersTest {

    @DisplayName("Answers deleteAnswers() 메소드 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswersForDelete")
    void deleteAnswersTest(final Answer answer, final DeleteHistories expected) throws CannotDeleteException {


        Answers answers = new Answers();
        answers.addToAnswer(answer);
        DeleteHistories deleteHistories = answers.deleteAnswers(UserTest.JAVAJIGI);

        assertThat(deleteHistories).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnswersForDelete() {
        return Stream.of(
                Arguments.of(
                        AnswerTest.A1,
                        DeleteHistories.of(Collections.singletonList(DeleteHistory.of(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now())))
                )
        );
    }

    @DisplayName("Answers hasOtherOwnerAnswersTest() 메소드 테스트")
    @ParameterizedTest
    @MethodSource("provideAnswersForHasOtherOwnerAnswersTest")
    void hasOtherOwnerAnswersTest(final Answer answer, final User user, final boolean expected) {

        Answers answers = new Answers();
        answers.addToAnswer(answer);

        boolean result = answers.hasOtherOwnerAnswers(user);
        assertThat(result).isEqualTo(expected);
    }

    private static Stream<Arguments> provideAnswersForHasOtherOwnerAnswersTest() {
        return Stream.of(
                Arguments.of(AnswerTest.A1, UserTest.JAVAJIGI, false),
                Arguments.of(AnswerTest.A2, UserTest.JAVAJIGI, true)
        );
    }
}
