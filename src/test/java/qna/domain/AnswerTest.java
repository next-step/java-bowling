package qna.domain;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import qna.CannotDeleteException;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class AnswerTest {
    public static final Answer A1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
    public static final Answer A2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");

    @Test
    @DisplayName("다른사람의 답변이 있으면 삭제할수 없다.")
    public void checkLoginUser() {
        Assertions.assertThrows(CannotDeleteException.class, () -> {
            A1.checkLoginUser(UserTest.SANJIGI);

            fail("삭제권한 에러가 발생해야 한다.");
        });
    }

    @Test
    @DisplayName("답변을 삭제하면 상태값이 바뀐다.")
    public void setDelete() {
        A1.setDeleted(true);
        A2.setDeleted(true);

        Assertions.assertAll(
                () -> assertThat(A1.isDeleted()).isTrue(),
                () -> assertThat(A2.isDeleted()).isTrue()
        );
    }

}
