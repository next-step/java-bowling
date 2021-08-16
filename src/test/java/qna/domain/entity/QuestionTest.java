package qna.domain.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static qna.domain.testdata.TestData.JAVAJIGI;
import static qna.domain.testdata.TestData.SANJIGI;

import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import qna.domain.exception.CannotDeleteException;

@DisplayName("질문")
public class QuestionTest {


    public static Stream<Question> javajigiQuestion() {
        return Stream.of(
            new Question("title1", "contents1").writeBy(JAVAJIGI)
        );
    }

    @DisplayName("[성공] 삭제")
    @ParameterizedTest
    @MethodSource("javajigiQuestion")
    public void delete(final Question question) {
        // given

        // when
        final List<DeleteHistory> deleteHistories = question.delete(JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).hasSize(1);
    }

    @DisplayName("[성공] 삭제 - 질문자 답변자 같음")
    @ParameterizedTest
    @MethodSource("javajigiQuestion")
    public void delete_sameAnswerWriter(final Question question) {
        // given
        question.addAnswer(new Answer(question.getWriter(), question, "Answers Contents1"));

        // when
        final List<DeleteHistory> deleteHistories = question.delete(JAVAJIGI);

        // then
        assertThat(question.isDeleted()).isTrue();
        assertThat(question.getAnswers().stream().allMatch(Answer::isDeleted)).isTrue();
        assertThat(deleteHistories).hasSize(2);
    }

    @DisplayName("[실패] delete - 다른 사람의 글 삭제")
    @ParameterizedTest
    @MethodSource("javajigiQuestion")
    public void delete_differentQuestionWriter(final Question question) {
        // given

        // when
        assertThrows(CannotDeleteException.class, () -> question.delete(SANJIGI));

        // then
        assertThat(question.isDeleted()).isFalse();
    }

    @DisplayName("[실패] delete - 질문자 답변자 다름")
    @ParameterizedTest
    @MethodSource("javajigiQuestion")
    public void delete_differentAnswerWriter(final Question question) {
        // given
        question.addAnswer(new Answer(SANJIGI, question, "Answers Contents1"));

        // when
        assertThrows(CannotDeleteException.class, () -> question.delete(JAVAJIGI));

        // then
        assertThat(question.isDeleted()).isFalse();
    }
}
