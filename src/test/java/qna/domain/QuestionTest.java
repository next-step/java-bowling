package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answerJavaJigi1;
    private Answer answerJavaJigi2;
    private Answer answerSanJigi;

    @BeforeEach
    void setUp() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answerJavaJigi1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answerJavaJigi2 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents2");
        answerSanJigi =  new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents3");
    }

    @Test
    public void isDeletedTrue() throws CannotDeleteException {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete() throws CannotDeleteException {
        question.addAnswer(answerJavaJigi1);
        question.addAnswer(answerJavaJigi2);
        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(deleteHistories).containsExactly(
                        new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answerJavaJigi1.getId(), answerJavaJigi1.getWriter(), LocalDateTime.now()),
                        new DeleteHistory(ContentType.ANSWER, answerJavaJigi2.getId(), answerJavaJigi2.getWriter(), LocalDateTime.now())
        );
    }

    @Test
    @DisplayName("작성자가 아니라면 삭제시도 시 예외발생")
    public void deleteFailedWhenWriterNotMatched() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("다른 사람이 쓴 답글이 있다면 Question 삭제불가")
    public void deleteFailed() {
        question.addAnswer(answerSanJigi);
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
