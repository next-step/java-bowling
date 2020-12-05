package qna.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Answers {

    private final List<Answer> answers;

    public Answers(List<Answer> answers) {
        this.answers = new ArrayList<>(answers);
    }

    public DeleteHistories deleteSelf(LocalDateTime deleteDate) {
        return answers.stream()
                .map(answer -> answer.deleteSelf(deleteDate))
                .collect(Collectors.collectingAndThen(Collectors.toList(),DeleteHistories::new));
    }
}
