package qna.domain;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
class DeleteHistoryListTest {

    private Question question;
    private Answer answer;

    private DeleteHistoryList deleteHistoryList;

    @BeforeEach
    public void setUp() {
        this.question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
        this.answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
        question.addAnswer(answer);

        this.deleteHistoryList = new DeleteHistoryList();
    }

    @Test
    void delete_성공() {
        SoftAssertions softAssertions = new SoftAssertions();
        softAssertions.assertThat(question.isDeleted()).isFalse();
        softAssertions.assertThat(answer.isDeleted()).isFalse();

        AnswerList answerList = new AnswerList(question);
        List<DeleteHistory> deleteHistories = deleteHistoryList.delete(question, answerList);

        softAssertions.assertThat(question.isDeleted()).isTrue();
        softAssertions.assertThat(question.isDeleted()).isTrue();

        softAssertions.assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.QUESTION);
        softAssertions.assertThat(deleteHistories.get(1).getContentType()).isEqualTo(ContentType.ANSWER);

        softAssertions.assertAll();
    }

}
