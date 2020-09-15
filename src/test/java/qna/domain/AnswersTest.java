package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswersTest {

    @Test
    void create() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        assertThat(answers).isEqualTo(new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2)));
    }

    @Test
    void isOwner() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A3));

        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isTrue();
    }

    @Test
    void isOwner_differentWriter() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));

        assertThat(answers.isOwner(UserTest.JAVAJIGI)).isFalse();
    }

    @Test
    void delete() throws CannotDeleteException {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A3));
        DeleteHistories deleteHistories = new DeleteHistories();

        answers.delete(UserTest.JAVAJIGI, deleteHistories);

        assertThat(AnswerTest.A1.isDeleted()).isTrue();
        assertThat(AnswerTest.A3.isDeleted()).isTrue();
        assertThat(deleteHistories.getDeleteHistories()).hasSize(2);
    }

    @Test
    void delete_invalidOwner() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2, AnswerTest.A3));
        DeleteHistories deleteHistories = new DeleteHistories();

        assertThatThrownBy(() -> answers.delete(UserTest.JAVAJIGI, deleteHistories))
                .isInstanceOf(CannotDeleteException.class);
    }
}
