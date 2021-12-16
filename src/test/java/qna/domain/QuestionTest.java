package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static qna.domain.AnswerTest.*;
import static qna.domain.UserTest.*;

public class QuestionTest {
    public static final Question Q1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
    public static final Question Q2 = new Question("title2", "contents2").writeBy(SANJIGI);

    @Test
    @DisplayName("답변이 존재하지 않는 경우, User 자신이 작성한 Question 은 삭제가 가능하다.")
    void deleteBy_answersNotExist_성공() throws CannotDeleteException {
        // given
        Question question1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Question question2 = new Question("title1", "contents1").writeBy(SANJIGI);

        // when
        question1.deleteBy(JAVAJIGI);
        question2.deleteBy(SANJIGI);

        // then
        assertThat(question1.isDeleted()).isTrue();
        assertThat(question2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 존재하지 않는 경우, User 자신이 작성하지 않은 Question 은 삭제가 불가하다.")
    void deleteBy_answersNotExist_실패() {
        Question question1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Question question2 = new Question("title1", "contents1").writeBy(SANJIGI);

        assertThatThrownBy(() -> {
            question1.deleteBy(SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            question2.deleteBy(JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    @DisplayName("답변이 존재하고 모든 답변이 자신이 작성한 경우, User 자신이 작성한 Question 은 삭제가 가능하다.")
    void deleteBy_answersExist_SingleUser_성공() throws CannotDeleteException {
        // given
        Question question1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Question question2 = new Question("title1", "contents1").writeBy(SANJIGI);
        question1.addAnswer(A1);
        question1.addAnswer(A3);
        question1.addAnswer(A5);
        question2.addAnswer(A2);
        question2.addAnswer(A4);

        // when
        question1.deleteBy(JAVAJIGI);
        question2.deleteBy(SANJIGI);

        // then
        assertThat(question1.isDeleted()).isTrue();
        assertThat(question2.isDeleted()).isTrue();
    }

    @Test
    @DisplayName("답변이 존재하고 모든 답변이 자신이 작성한 것이 아닌 경우, User 자신이 작성하지 않은 Question 은 삭제가 불가하다.")
    void deleteBy_answersExist_MultiUser_성공() throws CannotDeleteException {
        // given
        Question question1 = new Question("title1", "contents1").writeBy(JAVAJIGI);
        Question question2 = new Question("title1", "contents1").writeBy(SANJIGI);
        question1.addAnswer(A1);
        question1.addAnswer(A3);
        question1.addAnswer(A2);
        question2.addAnswer(A5);
        question2.addAnswer(A4);

        assertThatThrownBy(() -> {
            question1.deleteBy(SANJIGI);
        }).isInstanceOf(CannotDeleteException.class);

        assertThatThrownBy(() -> {
            question2.deleteBy(JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }
}
