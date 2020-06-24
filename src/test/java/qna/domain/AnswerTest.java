package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @DisplayName("답변 삭제 성공")
    @ParameterizedTest
    @MethodSource("source_delete_answer_shouldSucess")
    public void delete_answer_shouldSucess(Answer answer) {
        answer.deleteAndGetHistory();
        assertThat(answer.isDeleted()).isTrue();
    }

    public static Stream<Arguments> source_delete_answer_shouldSucess() {
        return Stream.of(
                Arguments.of(AnswerTest.A1),
                Arguments.of(AnswerTest.A2)
        );
    }

    @DisplayName("Answer 삭제 시에 삭제 이력이 생성된다")
    @ParameterizedTest
    @MethodSource("source_get_deleteHistory_shouldSucess")
    public void get_deleteHistory_shouldSucess(Answer answer) {
        DeleteHistory deleteHistory = answer.deleteAndGetHistory();
        assertAll(
                () -> assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.ANSWER),
                () -> assertThat(deleteHistory.getContentId()).isEqualTo(answer.getId()),
                () -> assertThat(deleteHistory.getDeletedBy()).isEqualTo(answer.getWriter())
        );
    }

    public static Stream<Arguments> source_get_deleteHistory_shouldSucess() {
        return Stream.of(
                Arguments.of(A1)
        );
    }
}
