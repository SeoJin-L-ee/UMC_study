package umc.study.apiPayload.code;

import umc.study.apiPayload.code.status.ReasonDTO;

public interface BaseCode {

    ReasonDTO getReason();

    ReasonDTO getReasonHttpStatus();
}
