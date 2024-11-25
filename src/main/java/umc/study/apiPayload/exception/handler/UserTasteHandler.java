package umc.study.apiPayload.exception.handler;

import umc.study.apiPayload.code.BaseErrorCode;
import umc.study.apiPayload.exception.GeneralException;

public class UserTasteHandler extends GeneralException {
    public UserTasteHandler(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
