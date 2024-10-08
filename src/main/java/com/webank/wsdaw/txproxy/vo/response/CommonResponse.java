package com.webank.wsdaw.txproxy.vo.response;

import com.webank.wsdaw.txproxy.enums.CodeEnum;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author grayson
 * @description TODO
 * @date 2023-11-09 22:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CommonResponse<T> implements Serializable {
    private int code = 0;
    private String msg = "success";
    private String debugMsg;
    private T data;

    public static <T> CommonResponse<T> success() {
        CommonResponse response = new CommonResponse();
        return response;
    }

    public static <T> CommonResponse<T> success(T data) {
        CommonResponse response = new CommonResponse();
        response.setData(data);
        return response;
    }

    public static <T> CommonResponse<T> error(int Code, String Msg) {
        CommonResponse response = new CommonResponse(Code, Msg, null, null);
        return response;
    }

    public static <T> CommonResponse<T> error(CodeEnum CodeEnum) {
        CommonResponse response =
                new CommonResponse(CodeEnum.getCode(), CodeEnum.getMsg(), null, null);
        return response;
    }

    public static <T> CommonResponse<T> error(CodeEnum CodeEnum, String debugMsg) {
        CommonResponse response =
                new CommonResponse(CodeEnum.getCode(), CodeEnum.getMsg(), debugMsg, null);
        return response;
    }

    public boolean isSuccess() {
        return code == 0;
    }
}
