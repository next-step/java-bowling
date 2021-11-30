package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    public static final DeleteHistory DELETE_HISTORY_Q1 = new DeleteHistory(ContentType.ANSWER, null, UserTest.JAVAJIGI, LocalDateTime.now());
    public static final DeleteHistory DELETE_HISTORY_Q2 = new DeleteHistory(ContentType.ANSWER, null, UserTest.SANJIGI, LocalDateTime.now());

    @DisplayName("Deletion of Question is succeeded when user is author")
    @Test
    void testDeleteSuccess() throws CannotDeleteException {
        assertThat(Q1.delete(UserTest.JAVAJIGI)).isEqualTo(DELETE_HISTORY_Q1);
        assertThat(Q1.delete(UserTest.SANJIGI)).isEqualTo(DELETE_HISTORY_Q2);
    }

    @DisplayName("Deletion of Question is succeeded when user is not author")
    @Test
    void testDeleteSuccess() {
        assertThatThrownBy(() -> Q1.delete(UserTest.SANJIGI)).isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.delete(UserTest.JAVAJIGI)).isInstanceOf(CannotDeleteException.class);
    }
}
