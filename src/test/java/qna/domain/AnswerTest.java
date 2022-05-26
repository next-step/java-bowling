package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class AnswerTest {
    public static final Answer JAVAJIGI_ANSWER = new Answer(UserTest.JAVAJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents1");
    public static final Answer SANJIGI_ANSWER = new Answer(UserTest.SANJIGI, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents2");
    public static final Answer WU22E_ANSWER = new Answer(UserTest.WU22E, QuestionTest.JAVAJIGI_QUESTION, "Answers Contents3");

    @Test
    void delete_답변_작성자는_자신의_답변을_삭제할_수_있다() throws CannotDeleteException {
        JAVAJIGI_ANSWER.delete(UserTest.JAVAJIGI);
        assertThat(JAVAJIGI_ANSWER.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람의_답변을_삭제할_수_없다() {
        assertThatThrownBy(() -> JAVAJIGI_ANSWER.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void getDeleteHistory_삭제된_답변_이력을_가져온다() throws CannotDeleteException {
        JAVAJIGI_ANSWER.delete(UserTest.JAVAJIGI);
        assertThat(JAVAJIGI_ANSWER.getDeleteHistory()).isEqualTo(Optional.of(DeleteHistory.create(JAVAJIGI_ANSWER)));
    }
}
