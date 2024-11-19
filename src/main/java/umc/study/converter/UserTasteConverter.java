package umc.study.converter;

import umc.study.domain.UserTaste;
import umc.study.domain.enums.FoodType;

import java.util.List;

public class UserTasteConverter {

    public static List<UserTaste> toUserTasteList(List<FoodType> foodTypeList) {

        return foodTypeList.stream()
                .map(FoodType ->
                        UserTaste.builder()
                                .foodType(FoodType)
                                .build())
                .toList();
    }
}
