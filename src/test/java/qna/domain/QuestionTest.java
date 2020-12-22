package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;

    @BeforeEach
    void initQuestion() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @Test
    public void delete_성공() {
        assertThatCode(() -> {
            question.canDeleteBy(UserTest.JAVAJIGI);
            question.delete();
        }).doesNotThrowAnyException();

        assertThat(question.isDeleted()).isEqualTo(true);
    }

    @Test
    public void delete_다른_사람이_쓴_글() {
        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            question.canDeleteBy(UserTest.SANJIGI);
            question.delete();
        }).withMessage("질문을 삭제할 권한이 없습니다.");

        assertThat(question.isDeleted()).isEqualTo(false);
    }

    @Test
    public void delete_성공_질문자_답변자_같음() {
        question.addAnswer(new Answer(UserTest.JAVAJIGI, question, "answer1"));

        assertThatCode(() -> {
            question.canDeleteBy(UserTest.JAVAJIGI);
            question.delete();
        }).doesNotThrowAnyException();

        assertThat(question.isDeleted()).isEqualTo(true);
    }

    @Test
    public void delete_답변_중_다른_사람이_쓴_글() {
        question.addAnswer(new Answer(UserTest.SANJIGI, question, "answer1"));

        assertThatExceptionOfType(CannotDeleteException.class).isThrownBy(() -> {
            question.canDeleteBy(UserTest.JAVAJIGI);
            question.delete();
        }).withMessage("다른 사람이 쓴 답변이 있어 삭제할 수 없습니다.");

        assertThat(question.isDeleted()).isEqualTo(false);
    }
}
