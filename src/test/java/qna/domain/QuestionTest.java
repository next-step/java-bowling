package qna.domain;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("삭제 요청한 유저가 질문글 작성 유저가 아닐 경우 Exception이 발생해야 한다.")
    void failByLoginAndQuestionIdDismatchTest() {

        // when & then
        assertThatExceptionOfType(CannotDeleteException.class)
            .isThrownBy(() -> Q1.deleteQuestion(UserTest.SANJIGI))
            .withMessageMatching("질문을 삭제할 권한이 없습니다.");
    }
}
