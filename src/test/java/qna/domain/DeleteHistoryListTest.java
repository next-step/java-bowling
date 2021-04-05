//package qna.domain;
//
//import org.assertj.core.api.SoftAssertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Transactional
//class DeleteHistoryListTest {
//
//    private Question question;
//    private Answer answer;
//
//    private DeleteHistoryList deleteHistoryList;
//
//    @BeforeEach
//    public void setUp() {
//        this.question = new Question(1L, "title1", "contents1").writeBy(UserTest.JAVAJIGI);
//        this.answer = new Answer(11L, UserTest.JAVAJIGI, QuestionTest.Q1, "Answers Contents1");
//
//        this.deleteHistoryList = new DeleteHistoryList();
//    }
//
//    @Test
//    void delete_성공() {
//        SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(question.isDeleted()).isFalse();
//        softAssertions.assertThat(answer.isDeleted()).isFalse();
//
//        Answers answers = new Answers(question);
//        List<DeleteHistory> deleteHistories = deleteHistoryList.delete(question, answers);
//
//        softAssertions.assertThat(question.isDeleted()).isTrue();
//        softAssertions.assertThat(answer.isDeleted()).isFalse();
//
//        softAssertions.assertThat(deleteHistories.size()).isEqualTo(1);
//        softAssertions.assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.QUESTION);
//
//        softAssertions.assertAll();
//    }
//
//    @Test
//    void delete_성공_질문자_답변자_같음() {
//        question.addAnswer(answer);
//
//        SoftAssertions softAssertions = new SoftAssertions();
//        softAssertions.assertThat(question.isDeleted()).isFalse();
//        softAssertions.assertThat(answer.isDeleted()).isFalse();
//
//        Answers answers = new Answers(question);
//        List<DeleteHistory> deleteHistories = deleteHistoryList.delete(question, answers);
//
//        softAssertions.assertThat(question.isDeleted()).isTrue();
//        softAssertions.assertThat(question.isDeleted()).isTrue();
//
//        softAssertions.assertThat(deleteHistories.size()).isEqualTo(2);
//        softAssertions.assertThat(deleteHistories.get(0).getContentType()).isEqualTo(ContentType.QUESTION);
//        softAssertions.assertThat(deleteHistories.get(1).getContentType()).isEqualTo(ContentType.ANSWER);
//
//        softAssertions.assertAll();
//    }
//
//
//}
