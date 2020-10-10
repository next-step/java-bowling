package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.exception.CannotDeleteException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class AnswersTest {

    @DisplayName("답변 생성하기")
    @Test
    void create() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers).isEqualTo(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }

    @DisplayName("답변 삭제하기")
    @Test
    void delete() throws CannotDeleteException {
        // given
        Answers answers = Answers.of(Arrays.asList(AnswerTest.A1, AnswerTest.A3));
        List<DeleteHistory> deleteHistories = new ArrayList<>();

        // when
        answers.delete(UserTest.JAVAJIGI, deleteHistories);

        // then
        assertThat(deleteHistories).isEqualTo(deleteHistories);
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
    }

    @DisplayName("답변 삭제 예외 테스트")
    @Test
    void shouldExceptionWriterNotEqualsLoginUser() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2, AnswerTest.A3));
        List<DeleteHistory> deleteHistories = new ArrayList<>();
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> answers.delete(UserTest.JAVAJIGI, deleteHistories))
                .withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
