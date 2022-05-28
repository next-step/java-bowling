package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);

    private Question question;
    private Answer answer;

    @BeforeEach
    public void setUp() {
        question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
    }

    @DisplayName("Question 삭제 테스트")
    @Test
    void deleteQuestion() {
        question.delete(UserTest.JAVAJIGI);

        assertThat(question.isDeleted()).isTrue();
    }

    @DisplayName("Question 삭제 CannotDeleteException 테스트")
    @Test
    void deleteQuestionCannotDeleteException() {
        assertThatThrownBy(() -> {
            question.delete(UserTest.SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }


}
