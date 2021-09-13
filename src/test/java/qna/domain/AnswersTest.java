package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AnswersTest {

    Answers answers;

    @BeforeEach
    void setUp() {
        List<Answer> answerList = new ArrayList<>();
        answerList.add(new Answer());
        answers = new Answers(answerList);
    }

    @Test
    @DisplayName("createDeleteHistories_테스트")
    void createDeleteHistories_테스트() {
        assertThat(answers.deleteHistories().get(0)).isInstanceOf(DeleteHistory.class);
    }

}