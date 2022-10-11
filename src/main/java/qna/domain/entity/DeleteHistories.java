package qna.domain.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DeleteHistories {
    private List<DeleteHistory> values;

    public DeleteHistories(Question question, User loginUser){

        this.values = new ArrayList<>();

        values.add(new DeleteHistory(question, loginUser));
        values.addAll(question.getAnswers()
                .stream().map(answer -> new DeleteHistory(answer, loginUser))
                .collect(Collectors.toList()));
    }

    public List<DeleteHistory> getValues() {
        return values;
    }
}
