package qna.domain;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import qna.CannotDeleteException;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void setup() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    @DisplayName("작성자가 삭제 요청 시 정상적으로 삭제된다.")
    void deleteWithValidUser() throws CannotDeleteException {
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 삭제 요청 시 예외를 던진다.")
    void deleteWithInvalidUser() {
        assertThatThrownBy(() -> question.delete(UserTest.SANJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("질문을 삭제할 권한이 없습니다.");
    }

    @Test
    @DisplayName("작성자가 아닌 사람이 답변을 작성했다면, 삭제 요청 시 예외를 던진다.")
    void deleteWhenAnswerExists() {
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "SANJIGI's answer"));
        assertThatThrownBy(() -> question.delete(UserTest.JAVAJIGI))
            .isInstanceOf(CannotDeleteException.class)
            .hasMessageContaining("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");
    }
}
