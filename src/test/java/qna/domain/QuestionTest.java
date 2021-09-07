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
    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void setup() {
        this.question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        this.answer1 = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        this.answer2 = new Answer(22L, UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    @DisplayName("질문 삭제")
    void deleteOne() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문, 답변 삭제")
    void delete() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        question.addAnswer(answer1);
        question.delete(UserTest.JAVAJIGI);
        assertThat(question.isDeleted()).isTrue();
        assertThat(answer1.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("질문과 답변 사용자 다를 경우 삭제 불가")
    void notDelete() throws Exception {
        assertThat(question.isDeleted()).isFalse();
        question.addAnswer(answer1);
        question.addAnswer(answer2);
        assertThatThrownBy(() -> {
            question.delete(UserTest.JAVAJIGI);
        }).isExactlyInstanceOf(CannotDeleteException.class);
    }
}
