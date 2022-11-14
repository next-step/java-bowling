package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

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
        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    public void delete_다른_사람이_쓴_글() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        assertThat(answer.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
        assertThat(answer.isDeleted()).isTrue();
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() throws Exception {
        Answer answer2 = new Answer(12L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
        question.addAnswer(answer2);
        assertThat(question.isDeleted()).isFalse();
        assertThat(answer.isDeleted()).isFalse();
        assertThat(answer2.isDeleted()).isFalse();

        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
