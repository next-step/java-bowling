package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(11L, "title2", "contents2").writeBy(UserTest.SANJIGI);


    @Test
    void delete_성공() throws CannotDeleteException {
        Q2.delete(UserTest.SANJIGI, Q2.getId());
        assertThat(Q2.isDeleted()).isTrue();
    }

    @Test
    void delete_답변_중_다른사람이_쓴_댓글이_있을_때() throws CannotDeleteException {
        Answer answer = AnswerTest.A2;
        Q1.addAnswer(answer);
        assertThatThrownBy(() -> Q1.delete(UserTest.JAVAJIGI, Q1.getId())).isInstanceOf(CannotDeleteException.class);
    }


    @Test
    void delete_댓글_작성자도_같을_때() throws CannotDeleteException {
        Answer answer = AnswerTest.A1;
        Q1.addAnswer(answer);
        Q1.delete(UserTest.JAVAJIGI, Q1.getId());
        assertThat(answer.isDeleted()).isTrue();
    }
}
