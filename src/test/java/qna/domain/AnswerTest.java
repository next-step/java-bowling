package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class AnswerTest {

    private static Stream<Arguments> provideForDeleteAnswer() {
        return Stream.of(
                Arguments.of(
                        new Answer(1L, UserTest.JAVAJIGI, QuestionTest.Q1, "answer1 by javajigi"),
                        new DeleteHistory(ContentType.ANSWER, 1L, UserTest.JAVAJIGI, LocalDateTime.now())
                ),
                Arguments.of(
                        new Answer(77L, UserTest.SANJIGI, QuestionTest.Q2, "answer77 by sanjigi"),
                        new DeleteHistory(ContentType.ANSWER, 77L, UserTest.SANJIGI, LocalDateTime.now())
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideForDeleteAnswer")
    @DisplayName("answer 삭제하기")
    void delete(Answer answer, DeleteHistory expected) {
        // when
        DeleteHistory result = answer.delete();

        // then
        assertThat(result).isEqualTo(expected);
        assertThat(answer.isDeleted()).isTrue();

    }

}
