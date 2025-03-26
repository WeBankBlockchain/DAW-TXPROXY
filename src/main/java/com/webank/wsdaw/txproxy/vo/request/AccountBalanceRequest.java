package com.webank.wsdaw.txproxy.vo.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@Data
public class AccountBalanceRequest extends CommonRequest {
    @NotBlank private String accountAddress;
    @NotBlank private String contractAddress;
}
