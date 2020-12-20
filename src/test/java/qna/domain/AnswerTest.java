package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswerTest {
    private Question Q1;
    private Answer A1;

    @BeforeEach
    public void setUp() {
        Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        A1 = new Answer(UserTest.JAVAJIGI, Q1, "Answers Contents1");
        Q1.addAnswer(A1);
    }

    @Test
    public void deleted_by_writer() throws CannotDeleteException {
        A1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

    @Test
    public void delete_by_other_Exception() {
        assertThatThrownBy(() -> A1.deleteBy(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }

    @Test
    public void delete_cascaded_from_question() throws CannotDeleteException {
        Q1.deleteBy(UserTest.JAVAJIGI);

        assertThat(A1.isDeleted()).isTrue();
    }

}
