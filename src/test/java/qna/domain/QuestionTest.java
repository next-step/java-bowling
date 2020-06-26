package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @DisplayName("Question 삭제 테스트")
    @Test
    public void deleteQuestionTest() {
        assertThatCode(() -> Q1.deleteByUser(UserTest.JAVAJIGI, LocalDateTime.now())).doesNotThrowAnyException();
    }

    @DisplayName("Question 삭제 테스트 - 삭제 불가")
    @Test
    public void cannotDeleteQuestionTest() {
        assertThatThrownBy(() -> Q2.deleteByUser(UserTest.JAVAJIGI, LocalDateTime.now())).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("Question 삭제 테스트 - 삭제 이력 테스트")
    @Test
    public void deleteQuestionCheckDeleteHistoryTest() throws CannotDeleteException {
        Question question = Question.create(Q1);
        question.addAnswer(AnswerTest.A1);

        List<DeleteHistory> deleteHistories = new ArrayList<>();
        deleteHistories.add(new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()));
        deleteHistories.add(new DeleteHistory(ContentType.ANSWER, AnswerTest.A1.getId(), AnswerTest.A1.getWriter(), LocalDateTime.now()));

        assertThat(question.deleteByUser(UserTest.JAVAJIGI, LocalDateTime.now())).isEqualTo(deleteHistories);
    }
}
