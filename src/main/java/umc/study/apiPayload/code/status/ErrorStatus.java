package umc.study.apiPayload.code.status;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.study.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // 가장 일반적인 응답
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "금지된 요청입니다."),


    // 멤버 관련 에러
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "USER4001", "사용자가 없습니다."),
    NICKNAME_NOT_EXIST(HttpStatus.BAD_REQUEST, "USER4002", "닉네임은 필수 입니다."),

    // 예시,,,
    ARTICLE_NOT_FOUND(HttpStatus.NOT_FOUND, "ARTICLE4001", "게시글이 없습니다."),

    // For test
    TEMP_EXCEPTION(HttpStatus.BAD_REQUEST, "TEMP4001", "이거는 테스트"),

    // User Taste 관련 에러
    USER_TASTE_NOT_FOUND(HttpStatus.NOT_FOUND, "USERTASTE400", "해당하는 유저의 선호 취향이 존재하지 않습니다"),

    // Food Type
    FOOD_TYPE_NOT_FOUND(HttpStatus.NOT_FOUND, "FOODTYPE404", "해당하는 음식 유형이 존재하지 않습니다."),

    // Restaurant
        RESTAURANT_NOT_FOUND(HttpStatus.NOT_FOUND, "RESTAURANT404", "식당이 존재하지 않습니다."),

    // Mission
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404", "미션이 존재하지 않습니다."),
    MISSION_IS_CHALLENGED(HttpStatus.CONFLICT, "MISSION409", "이미 도전 중인 미션입니다."),

    // Page
    INVALID_PAGE_NUMBER(HttpStatus.BAD_REQUEST, "PAGE400", "유효하지 않은 page 값입니다."),

    ;

    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build()
                ;
    }
}
