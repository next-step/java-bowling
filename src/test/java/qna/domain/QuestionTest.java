package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    Question question;

    @BeforeEach
    public void setUp() throws Exception {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_되지_않으면_deleteHistory_빈값() throws Exception {
        List<DeleteHistory> deleteHistories = question.deleteHistories();
        assertThat(deleteHistories).isEmpty();
    }

    @Test
    public void answer이_하나라도_delete_되지_않으면_deleteHistory_빈값() throws Exception {
        question.delete(UserTest.JAVAJIGI);
        question.addAnswer(new Answer(UserTest.SANJIGI, question, ""));
        List<DeleteHistory> deleteHistories = question.deleteHistories();

        assertThat(question.isDeleted()).isTrue();
        assertThat(deleteHistories).isEmpty();
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(question.deleteHistories().size()).isOne();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        question.addAnswer(new Answer(UserTest.SANJIGI, question, ""));

        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
