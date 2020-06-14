package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class DeleteHistoriesTest {

    private DeleteHistories deleteHistories = new DeleteHistories();

    @DisplayName("컬렉션 객체를 만들 수 있다.")
    @Test
    void canCreateDeleteHistories() {
        assertThat(this.deleteHistories).isInstanceOf(DeleteHistories.class);
    }

    @DisplayName("질문 삭제 히스토리를 추가할 수 있다.")
    @Test
    void canAddQuestion() {
        this.deleteHistories.addQuestion(QuestionTest.Q1);
        assertThat(this.deleteHistories.count()).isOne();
    }

    @DisplayName("답변 리스트 삭제 히스토리들을 추가할 수 있다.")
    @Test
    void canAddAnswers() {
        Answers answers = new Answers(Arrays.asList(AnswerTest.A1, AnswerTest.A2));
        this.deleteHistories.addAnswers(answers);

        assertThat(this.deleteHistories.count()).isEqualTo(2);
    }

    @DisplayName("List 변환할 수 있다.")
    @Test
    void canToList() {
        assertThat(this.deleteHistories.toList()).isInstanceOf(List.class);
    }
}
