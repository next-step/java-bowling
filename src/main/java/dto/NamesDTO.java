package dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class NamesDTO {

    private final List<NameDTO> nameDTOs;

    private NamesDTO(List<NameDTO> nameDTOs) {
        this.nameDTOs = new ArrayList<>(nameDTOs);
    }

    public static NamesDTO of(List<NameDTO> nameDTOs) {
        return new NamesDTO(nameDTOs);
    }

    public List<String> getNames() {
        return nameDTOs.stream()
                .map(NameDTO::getName)
                .collect(Collectors.toList());
    }
}
