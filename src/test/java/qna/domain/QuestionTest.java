package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    private Question Q1;
    private Answer A1;
    private Answer A2;

    @BeforeEach
    public void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        A2 = new Answer(UserTest.SANJIGI, Q1, "Answers Contents2");
    }


    @Test
    public void delete_by_writer() throws CannotDeleteException {
        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(Q1.isDeleted()).isFalse();
    }

    @Test
    public void delete_with_answers_by_writer() throws CannotDeleteException {
        Q1.addAnswer(A1);
        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(Q1.isDeleted()).isTrue();
        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_with_answers_by_other_Exception() {
        Q1.addAnswer(A2);
        assertThatThrownBy(() -> Q1.deleteBy(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThat(Q1.isDeleted()).isFalse();
    }
}
