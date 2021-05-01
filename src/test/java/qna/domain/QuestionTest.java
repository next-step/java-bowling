package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제가능여부")
    void enableDelete() {
        Assertions.assertThatThrownBy(() -> {
            Q1.enableDelete(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
