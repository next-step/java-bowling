package qna.domain;

import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question JAVAJIGI_QUESTION = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question SANJIGI_QUESTION = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    void delete_질문_작성자는_자신의_질문을_삭제할_수_있다() throws CannotDeleteException {
        JAVAJIGI_QUESTION.delete(UserTest.JAVAJIGI);
        assertThat(JAVAJIGI_QUESTION.isDeleted()).isTrue();
    }

    @Test
    void delete_다른_사람의_질문을_삭제할_수_없다() {
        assertThatThrownBy(() -> JAVAJIGI_QUESTION.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
