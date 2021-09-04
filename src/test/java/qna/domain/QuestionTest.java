package qna.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    void setup() {
        Q1.addAnswer(AnswerTest.A1);
    }

    @DisplayName("글 삭제")
    @Test
    void delete_ReturnOneQuestionAndOneAnswerDeleteHistories() throws CannotDeleteException {
        List<DeleteHistory> deleteHistories = Q1.delete(UserTest.JAVAJIGI);
        assertThat(deleteHistories).hasSize(2);
    }

    @DisplayName("글 작성자와 로그인 유저가 다를 경우 CannotDeleteException 발생")
    @Test
    void delete_ThrowsCannotDeleteException_IfLoginUserAndWriterAreDifferent() {
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.SANJIGI));
    }

    @DisplayName("글 작성자와 댓글 작성 유저가 다를 경우 CannotDeleteException 발생")
    @Test
    void delete_ThrowsCannotDeleteException_IfLoginUserAndAnswerWriterAreDifferent() {
        Q1.addAnswer(AnswerTest.A2);
        assertThatExceptionOfType(CannotDeleteException.class)
                .isThrownBy(() -> Q1.delete(UserTest.JAVAJIGI));
    }
}
