package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("삭제 테스트")
    @Test
    public void deleteTest() {
        assertThat(Q1.deleteBy(Q1.getWriter()))
                .containsAnyOf(new DeleteHistory(ContentType.QUESTION, Q1.getId(), Q1.getWriter(), LocalDateTime.now()));
    }

    @DisplayName("삭제 예외처리 테스트")
    @Test
    public void deleteExceptionTest() {
        assertThatThrownBy(() -> Q2.deleteBy(Q1.getWriter()))
                .isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("삭제 히스토리 테스트")
    @Test
    public void deleteEqualDeleteHistoriesTest() {
        Question question = Question.of(Q1);
        question.addAnswer(AnswerTest.A1);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));

        assertThat(question.deleteBy(Q1.getWriter())).isEqualTo(deleteHistories);
    }
}
