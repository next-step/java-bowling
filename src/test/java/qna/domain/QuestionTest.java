package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

/**
 * * 답변이 없는 경우 삭제가 가능하다. v
 */
public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
    private Question question;
    private Answer answer;


    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);
    }

    @Test
    public void delete_성공() throws Exception {

        assertThat(question.isDeleted()).isFalse();

        List<DeleteHistory> deleteHistories = question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        verifyDeleteHistories(deleteHistories);
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


    private void verifyDeleteHistories(List<DeleteHistory> deleteHistories) {
        assertThat(deleteHistories).containsExactly(
                new DeleteHistory(ContentType.QUESTION, question.getId(), question.getWriter(), LocalDateTime.now()),
                new DeleteHistory(ContentType.ANSWER, answer.getId(), answer.getWriter(), LocalDateTime.now()));
    }


}
