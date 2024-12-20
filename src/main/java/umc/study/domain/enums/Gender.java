package umc.study.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum Gender {

    NONE("선택 안 함"),
    MALE("남성"),
    FEMALE("여성");

    private final String description;

    public String getDescription() {
        return description;
    }
}
