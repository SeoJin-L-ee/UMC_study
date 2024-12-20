package umc.study.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum FoodType {

    KOREAN("한식"),
    CHINESE("중식"),
    WESTERN("양식"),
    JAPANESE("일식"),
    CHICKEN("치킨"),
    SNACK("간식"),
    MEAT_GRILL("고기/구이"),
    LUNCHBOX("도시락"),
    NIGHT_SNACK("야식"),
    FAST_FOOD("패스트푸드"),
    DESSERT("디저트"),
    ASIAN_FOOD("아시아 음식");

    private final String description;

    public String getDescription() {
        return description;
    }

    public static FoodType fromDescription(String description) {
        for (FoodType type : FoodType.values()) {
            if (type.getDescription().equals(description)) {
                return type;
            }
        }
        throw new IllegalArgumentException("다음에 해당하는 enum 값이 존재하지 않습니다.: " + description);
    }
}
