package qna.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;
import qna.CannotDeleteException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Transactional
class AnswerListTest {
    private Question question;

    private Answer answer1;
    private Answer answer2;

    @BeforeEach
    void setup() {
        question = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);

        answer1 = new Answer(UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        answer2 = new Answer(UserTest.SANJIGI, QuestionTest.Q1, "Answers Contents2");
    }

    @Test
    void delete_성공() throws CannotDeleteException {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(answer1.isDeleted()).isFalse();

        AnswerList answerList = new AnswerList(question);
        answerList.preCheckDeletion(UserTest.JAVAJIGI);

        softAssertions.assertThat(answer1.isDeleted()).isFalse();

        softAssertions.assertAll();
    }

    @Test
    void delete_답변_중_다른_사람이_쓴_글() {
        question.addAnswer(answer2);

        AnswerList answerList = new AnswerList(question);
        assertThatThrownBy(() -> {
            answerList.preCheckDeletion(UserTest.JAVAJIGI);
        }).isInstanceOf(CannotDeleteException.class);
    }

    @Test
    void delete_성공_질문자_답변자_같음() throws CannotDeleteException {
        question.addAnswer(answer1);

        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(answer1.isDeleted()).isFalse();

        AnswerList answerList = new AnswerList(question);
        answerList.preCheckDeletion(UserTest.JAVAJIGI);
        answerList.delete();

        softAssertions.assertThat(answer1.isDeleted()).isTrue();

        softAssertions.assertAll();
    }

}
