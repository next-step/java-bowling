package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("질문을 삭제할 권한이 없으면 Exception이 발생한다.")
    void validateDelete() {
        assertThatThrownBy(() -> {
            Q1.validateDelete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

}
