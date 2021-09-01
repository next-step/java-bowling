package qna.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Question question2;
    private Answer answer;

    @Before
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        question2 = new Question(2L, "title2", "contents2").writeBy(UserTest.SANJIGI);
        question2.addAnswer(answer);
    }

    @Test
    public void delete() throws CannotDeleteException {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @DisplayName("다른 유저가 작성한 경우 삭제 실패")
    @Test
    public void delete_fail() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("다른 유저가 작성한 답변이 있는 경우 삭제 실패")
    @Test
    public void delete_fail2() {
        assertThatThrownBy(() -> question2.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
