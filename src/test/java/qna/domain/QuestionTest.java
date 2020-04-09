package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.qna.answer.Answer;
import qna.domain.qna.question.Question;
import qna.domain.user.User;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class QuestionTest {
    private Question question1;
    private Question question2;
    private Answer answer3;
    private Answer answer4;

    @BeforeEach
    void setUp() {
        question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        answer3 = new Answer(UserTest.SANJIGI, question2, "Answers Contents3");
        answer4 = new Answer(UserTest.SANJIGI, question2, "Answers Contents4");
    }

    @DisplayName("로그인 유저가 질문 작성자이면 예외가 발생하지 않음")
    @Test
    void validateCanDeleteWhenLoginUserIsWriter() {
        User loginUser = UserTest.JAVAJIGI;

        assertThatCode(() -> {
            question1.delete(loginUser);
        }).doesNotThrowAnyException();
    }

    @DisplayName("로그인 유저가 질문 작성자가 아니면 예외 발생")
    @Test
    void validateCanDeleteWhenLoginUserIsNotWriter() {
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            question1.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("로그인 유저가 질문 작성자라면 deleted 상태가 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        question1.delete(loginUser);

        //then
        assertThat(question1.isDeleted()).isTrue();
    }

    @DisplayName("답변과 질문을 모두 로그인유저가 작성한 경우, 질문과 답변의 deleted가 모두 true로 바뀐다.")
    @Test
    void changeDeletedStateQuestionAndAnswersWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        User loginUser = UserTest.SANJIGI;
        question2.addAnswer(answer3);
        question2.addAnswer(answer4);

        //when
        question2.delete(loginUser);

        //then
        assertThat(question2.isDeleted()).isTrue();
        assertThat(answer3.isDeleted()).isTrue();
        assertThat(answer4.isDeleted()).isTrue();
    }

    @DisplayName("답변이 삭제될 때, 질문과 답변의 DeleteHistories가 반환된다.")
    @Test
    void returnDeleteHistoriesWhenDelete() throws CannotDeleteException {
        //given
        User loginUser = UserTest.SANJIGI;
        question2.addAnswer(answer3);
        question2.addAnswer(answer4);

        //when
        List<DeleteHistory> deleteHistories = question2.delete(loginUser);

        //then
        assertThat(deleteHistories).hasSize(3);
    }

    @DisplayName("답변이 없는 경우도 삭제가능")
    @Test
    void canDeleteWhenNoAnswer() throws CannotDeleteException {
        //given
        User loginUser = UserTest.JAVAJIGI;

        //when
        List<DeleteHistory> deleteHistories = question1.delete(loginUser);

        //then
        assertThat(deleteHistories).hasSize(1);
    }
}