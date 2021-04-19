package qna.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class DeleteHistoriesTest {
    DeleteHistories deleteHistories;

    @BeforeEach
    void setUp() {
        deleteHistories = new DeleteHistories();
    }

    @Test
    @DisplayName("삭제 이력을 인자로 받아 삭제 이력 목록에 추가한다.")
    public void add() throws Exception {
        deleteHistories.add(DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.JAVAJIGI));
        assertThat(deleteHistories.list()).containsExactly(DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.JAVAJIGI));
    }

    @Test
    @DisplayName("삭제 이력 목록을 인자로 받아 삭제 이력 목록에 추가한다.")
    public void addAll() throws Exception {
        //given
        List<DeleteHistory> deleteHistories = Arrays.asList(
                DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.JAVAJIGI)
                , DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.SANJIGI));

        //when
        this.deleteHistories.addAll(deleteHistories);

        //then
        assertThat(this.deleteHistories.list()).containsExactly(
                DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.JAVAJIGI)
                , DeleteHistory.ofQuestion(QuestionTest.Q1.getId(), UserTest.SANJIGI));
    }
}
