package com.webank.wsdaw.txproxy.advice;

import com.webank.wsdaw.txproxy.enums.CodeEnum;
import com.webank.wsdaw.txproxy.vo.response.CommonResponse;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.exceptions.ClientException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@Slf4j
public class GlobalAdvice {

    @ExceptionHandler({ClientException.class})
    public CommonResponse usernameException(ClientException ex) {
        return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
    }

    @ExceptionHandler({Exception.class})
    public CommonResponse unhandledException(Exception ex) {
        log.error("unhandled exception", ex);
        return CommonResponse.error(CodeEnum.UNKNOWN_ERROR);
    }
}
