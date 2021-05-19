package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class QuestionTest {
    public static final Question Q1 = new Question(1, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question(2, "title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제가능여부")
    void enableDelete() {
        Q1.addAnswer(new Answer(UserTest.SANJIGI, new Question("title3", "contents3"), "..."));

        Assertions.assertThatThrownBy(() -> {
            Q1.authorized(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("삭제히스토리")
    void deleteHistory() throws CannotDeleteException {
        List<DeleteHistory> delete = Q1.delete(UserTest.JAVAJIGI, Q1.getId());
        DeleteHistory deleteHistory = delete.get(0);
        assertThat(deleteHistory).isNotNull();
    }
}
