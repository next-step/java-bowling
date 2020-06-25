package qna.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DisplayName("Question 로직 테스트")
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제 성공")
    @ParameterizedTest
    @MethodSource("source_delete_question_shouldSucess")
    public void delete_question_shouldSucess(Question q) {
        q.deleteAndGetHistory();
        assertThat(q.isDeleted()).isTrue();
    }

    public static Stream<Arguments> source_delete_question_shouldSucess() {
        return Stream.of(
                Arguments.of(Q1),
                Arguments.of(Q2)
        );
    }

    @DisplayName("Question 삭제 시에 삭제 이력이 생성된다")
    @ParameterizedTest
    @MethodSource("source_get_deleteHistory_shouldSucess")
    public void get_deleteHistory_shouldSucess(Question q) {
        DeleteHistory deleteHistory = q.deleteAndGetHistory();
        assertAll(
                () -> assertThat(deleteHistory.getContentType()).isEqualTo(ContentType.QUESTION),
                () -> assertThat(deleteHistory.getContentId()).isEqualTo(q.getId()),
                () -> assertThat(deleteHistory.getDeletedBy()).isEqualTo(q.getWriter())
        );
    }

    public static Stream<Arguments> source_get_deleteHistory_shouldSucess() {
        return Stream.of(
                Arguments.of(Q1)
        );
    }
}
