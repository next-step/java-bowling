package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Arrays;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.*;

@DisplayName("Answers 로직 테스트")
class AnswersTest {

    @DisplayName("다른 사람이 쓴 답변이 없다면 삭제 가능")
    @ParameterizedTest
    @MethodSource("source_check_deletable_shouldSucess")
    public void check_deletable_shouldSucess(Answers answers, User owner) {
        assertThat(answers.isAllOwner(owner)).isTrue();
    }

    public static Stream<Arguments> source_check_deletable_shouldSucess() {
        return Stream.of(
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A1)), UserTest.JAVAJIGI)
        );
    }

    @DisplayName("다른 사람이 쓴 답변이 있다면 삭제 불가능")
    @ParameterizedTest
    @MethodSource("source_check_undeletable_shouldSucess")
    public void check_undeletable_shouldSucess(Answers answers, User owner) {
        assertThat(answers.isAllOwner(owner)).isFalse();
    }

    public static Stream<Arguments> source_check_undeletable_shouldSucess() {
        return Stream.of(
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A1)), UserTest.SANJIGI)
        );
    }

    @DisplayName("답변 전체 삭제 성공")
    @ParameterizedTest
    @MethodSource("source_delete_allAnswers_shouldSucess")
    public void delete_allAnswers_shouldSucess(Answers answers) {
        answers.deleteAllAndGetHistory();
        for (Answer answer : answers.getAnswers()) {
            assertThat(answer.isDeleted()).isTrue();
        }
    }

    public static Stream<Arguments> source_delete_allAnswers_shouldSucess() {
        return Stream.of(
                Arguments.of(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)))
        );
    }
}
