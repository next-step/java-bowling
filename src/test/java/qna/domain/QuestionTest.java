package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @Test
    @DisplayName("작성자 본인의 질문 삭제하기")
    void deleteTest() {
        assertThatCode(() -> Q1.deleteByWriter(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
        assertThatCode(() -> Q2.deleteByWriter(UserTest.SANJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("작성자 타인의 질문 삭제하기 Exception")
    void notAvailableDeleteTest() {
        assertThatThrownBy(() -> Q1.deleteByWriter(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleteByWriter(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
