package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

class DeleteHistoriesTest {

    @DisplayName("객체 정상 생성")
    @Test
    public void makeDeleteHistories_정상() {
        assertThatCode(() -> {
            new DeleteHistories();
        }).doesNotThrowAnyException();
    }

    @DisplayName("addQuestionHistory 작업 요청을 받응면 내부 리스트에 Question 삭제 이력을 추가함")
    @Test
    public void addQuestionistory_정상() {
        DeleteHistories deleteHistoriesGroup = new DeleteHistories();
        List<DeleteHistory> deleteHistories = deleteHistoriesGroup.getDeleteHistories();

        assertThat(deleteHistories.size()).isEqualTo(0);

        deleteHistoriesGroup.addQuestionDelete(QuestionTest.Q1);

        assertThat(deleteHistories.size()).isEqualTo(1);
    }

    @DisplayName("addAnswerHistory 작업 요청을 받응면 내부 리스트에 Answer 삭제 이력을 추가함")
    @Test
    public void addAnswerHistories_정상() {
        Question Q3 = new Question("title1", "contents1").writeBy(UserTest.JAVAJIGI);
        Answer answer = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Answer answer2 = new Answer(UserTest.JAVAJIGI, Q3, "질문 작성자가 답변 달았음.");
        Q3.addAnswer(answer);
        Q3.addAnswer(answer2);
        Answers answers = Q3.getAnswers();

        DeleteHistories deleteHistoriesGroup = new DeleteHistories();
        List<DeleteHistory> deleteHistories = deleteHistoriesGroup.getDeleteHistories();
        deleteHistoriesGroup.addAnswerHistories(answers);

        assertThat(deleteHistories.size()).isEqualTo(2);
    }
}
