package bowling.domain.engine;

import java.util.function.Function;

public enum RecordType {

    NUMBER(record -> String.valueOf(record.getPitchResult().getValue())),
    STRIKE(ignored -> "X"),
    SPARE(ignored -> "/"),
    GUTTER(ignored -> "-"),
    ;

    private final Function<Record, String> converter;

    RecordType(Function<Record, String> converter) {
        this.converter = converter;
    }

    public String export(Record record) {
        return converter.apply(record);
    }

}
