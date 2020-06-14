package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public Question Q1;
    public Question Q2;

    public static Question createQuestion(User user) {
        return new Question("title1", "contents1").writeBy(user);
    }

    @BeforeEach
    void setup() {
        Q1 = createQuestion(UserTest.JAVAJIGI);
        Q2 = createQuestion(UserTest.SANJIGI);
    }

    @Test
    @DisplayName("질문의 작성자일 경우 정상 삭제")
    void deleteTest() {
        assertThatCode(() -> Q1.deleted(UserTest.JAVAJIGI))
                .doesNotThrowAnyException();
        assertThatCode(() -> Q2.deleted(UserTest.SANJIGI))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("질문의 작성자가 아닐경우 Exception")
    void notAvailableDeleteTest() {
        assertThatThrownBy(() -> Q1.deleted(UserTest.SANJIGI))
                .isInstanceOf(CannotDeleteException.class);
        assertThatThrownBy(() -> Q2.deleted(UserTest.JAVAJIGI))
                .isInstanceOf(CannotDeleteException.class);
    }
}
