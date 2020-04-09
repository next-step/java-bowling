package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qna.CannotDeleteException;
import qna.domain.deletehistory.DeleteHistory;
import qna.domain.qna.answer.Answer;
import qna.domain.qna.answer.Answers;
import qna.domain.qna.question.Question;
import qna.domain.user.User;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class AnswersTest {
    private Question question1;
    private Question question2;
    public Answer answer1;
    public Answer answer2;
    public Answer answer3;
    public Answer answer4;

    @BeforeEach
    void setUp() {
        question1 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        question2 = new Question("title2", "contents2").writeBy(UserTest.SANJIGI);
        answer1 = new Answer(UserTest.JAVAJIGI, question1, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, question1, "Answers Contents2");
        answer3 = new Answer(UserTest.SANJIGI, question2, "Answers Contents3");
        answer4 = new Answer(UserTest.SANJIGI, question2, "Answers Contents4");
    }

    @DisplayName("답변 목록 중에 로그인 유저가 쓰지 않은 답변이 있으면 예외를 반환한다. ")
    @Test
    void validateCanDeleteWhenCannotDelete() {
        Answers answers = new Answers(Arrays.asList(answer1, answer2));
        User loginUser = UserTest.SANJIGI;

        assertThatThrownBy(() -> {
            answers.delete(loginUser);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @DisplayName("답변 목록이 모두 로그인 유저가 쓴 답변이라면 deleted가 true로 바뀐다.")
    @Test
    void changeDeletedStateWhenLoginUserIsWriter() throws CannotDeleteException {
        //given
        Answers answers = new Answers(Arrays.asList(answer3, answer4));
        User loginUser = UserTest.SANJIGI;

        //when
        answers.delete(loginUser);

        //then
        assertThat(answer3.isDeleted()).isTrue();
        assertThat(answer4.isDeleted()).isTrue();
    }

    @DisplayName("delete 메소드가 실행되면, 답변 목록의 DeleteHistory 목록을 반환한다.")
    @Test
    void returnDeleteHistoriesWhenDelete() throws CannotDeleteException {
        //given
        Answers answers = new Answers(Arrays.asList(answer3, answer4));
        User loginUser = UserTest.SANJIGI;

        //when
        List<DeleteHistory> deleteHistories = answers.delete(loginUser);

        //then
        assertThat(deleteHistories).hasSize(2);
    }
}