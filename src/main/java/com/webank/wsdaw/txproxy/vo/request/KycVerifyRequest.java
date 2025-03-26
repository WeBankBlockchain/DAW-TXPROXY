package com.webank.wsdaw.txproxy.vo.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class KycVerifyRequest extends CommonRequest {
    @NotBlank private String address;
    @NotBlank private String code;
    @NotBlank private String nonce;
}
