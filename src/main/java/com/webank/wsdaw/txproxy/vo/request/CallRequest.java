package com.webank.wsdaw.txproxy.vo.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class CallRequest extends CommonRequest {
    @NotBlank private String from;
    @NotBlank private String to;
    @NotBlank private String data;
    @NotBlank private String network;
}
