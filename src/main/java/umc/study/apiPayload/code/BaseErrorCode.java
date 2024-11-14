package umc.study.apiPayload.code;

import umc.study.apiPayload.code.status.ErrorReasonDTO;

public interface BaseErrorCode {

    ErrorReasonDTO getReason();

    ErrorReasonDTO getReasonHttpStatus();
}
