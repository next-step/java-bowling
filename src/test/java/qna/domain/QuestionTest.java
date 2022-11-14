package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class QuestionTest {
    public static final Question QuestionWithAnswers = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question QuestionWithoutAnswer = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    @BeforeEach
    public void beforeEach() {
        QuestionWithAnswers.setDeleted(false);
        QuestionWithoutAnswer.setDeleted(false);
        QuestionWithAnswers.addAnswer(AnswerTest.A1);
        QuestionWithAnswers.addAnswer(AnswerTest.A2);
    }

    @Test
    @DisplayName("작성자가 답변 없는 질문 삭제할 경우 삭제 성공")
    public void delete_성공() throws CannotDeleteException {
        QuestionWithoutAnswer.delete(UserTest.SANJIGI);
        assertThat(QuestionWithoutAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 유저가 삭제할 경우 삭제 실패")
    public void delete_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> QuestionWithAnswers.delete(UserTest.SANJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(QuestionWithAnswers.isDeleted()).isFalse();
        assertThatThrownBy(() -> QuestionWithoutAnswer.delete(UserTest.JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(QuestionWithoutAnswer.isDeleted()).isFalse();
    }

    @Test
    @DisplayName("작성자가 단 답변만 있을 경우 삭제 성공")
    public void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        Answer A3 = new Answer(UserTest.SANJIGI, QuestionTest.QuestionWithAnswers, "Answers Contents3");
        QuestionWithoutAnswer.addAnswer(A3);
        QuestionWithoutAnswer.delete(UserTest.SANJIGI);
        assertThat(QuestionWithoutAnswer.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("작성자가 아닌 유저가 단 답변이 있을 경우 삭제 실패")
    public void delete_답변_중_다른_사람이_쓴_글() {
        assertThatThrownBy(() -> QuestionWithAnswers.delete(UserTest.JAVAJIGI))
                .isExactlyInstanceOf(CannotDeleteException.class);
        assertThat(QuestionWithAnswers.isDeleted()).isFalse();
    }
}
