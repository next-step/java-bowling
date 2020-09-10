package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void deleteByUser() throws CannotDeleteException {
        Question question = new Question("title", "content").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, question, "content");
        question.addAnswer(answer);

        List<DeleteHistory> deleteHistories = question.deleteByUser(UserTest.JAVAJIGI);
        assertThat(deleteHistories)
                .contains(new DeleteHistory(ContentType.QUESTION, question.getId(), UserTest.JAVAJIGI, LocalDateTime.now()))
                .contains(new DeleteHistory(ContentType.ANSWER, answer.getId(), UserTest.JAVAJIGI, LocalDateTime.now()));
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNull() {
        assertThatNullPointerException().isThrownBy(() -> Q1.deleteByUser(null));
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenNotOwner() {
        assertThatThrownBy(() -> Q1.deleteByUser(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleteByUser(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void deleteByUser_ShouldThrow_WhenAnswerNotOwner() {
        Question question = new Question("title", "content").writeBy(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "content"));
        assertThatThrownBy(() -> question.deleteByUser(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
