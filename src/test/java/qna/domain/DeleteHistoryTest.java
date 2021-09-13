package qna.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class DeleteHistoryTest {

    @Test
    @DisplayName("question_테스트")
    void question_테스트() {
        assertThat(DeleteHistory.question(new Question())).isInstanceOf(DeleteHistory.class);
    }

    @Test
    @DisplayName("answer_테스트")
    void answer_테스트() {
        assertThat(DeleteHistory.answer(new Answer())).isInstanceOf(DeleteHistory.class);
    }

}