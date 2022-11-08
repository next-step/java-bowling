package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    public void delete_성공() throws Exception {
        Q1.delete(UserTest.JAVAJIGI);
        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            Q1.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        Q1.addAnswer(AnswerTest.A1);
        Q1.delete(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(AnswerTest.A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_실패_질문자_답변자_다름() throws Exception {
        Q1.addAnswer(AnswerTest.A2);

        assertThatThrownBy(() -> {
            Q1.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
