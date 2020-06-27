package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import qna.CannotDeleteException;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("Answers 로직 테스트")
class AnswersTest {

    @DisplayName("다른 사람이 쓴 답변이 없다면 삭제 가능")
    @ParameterizedTest
    @MethodSource("source_delete_allAnswers_shouldSuccess")
    public void delete_allAnswers_shouldSuccess(Answers answers, User owner) throws CannotDeleteException {
        answers.delete(owner);
        for (Answer answer : answers.getAnswers()) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    public static Stream<Arguments> source_delete_allAnswers_shouldSuccess() {
        return Stream.of(
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A1)), UserTest.JAVAJIGI),
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A2, AnswerTest.A2)), UserTest.SANJIGI)
        );
    }

    @DisplayName("다른 사람이 쓴 답변이 있다면 삭제 시에 예외 발생")
    @ParameterizedTest
    @MethodSource("source_delete_anotherOwner_shouldFail")
    public void delete_anotherOwner_shouldFail(Answers answers, User owner) {
        assertThat(answers.isAllOwner(owner)).isFalse();
        assertThatThrownBy(() -> {
            answers.delete(owner);
        }).isInstanceOf(CannotDeleteException.class);
    }

    public static Stream<Arguments> source_delete_anotherOwner_shouldFail() {
        return Stream.of(
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A1)), UserTest.SANJIGI),
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A2)), UserTest.JAVAJIGI)
        );
    }
}
