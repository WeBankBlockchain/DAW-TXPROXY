package com.webank.wsdaw.txproxy.vo.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class SendTxRequest extends CommonRequest {
    @NotBlank private String signedTransactionData;
    @NotBlank private String address;
    @NotBlank private String code;
    @NotBlank private String nonce;
    @NotBlank private String network;
}
