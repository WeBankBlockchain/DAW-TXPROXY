package com.webank.wsdaw.txproxy.vo.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TransactionResponse {
    private String hash;

    private String from;

    private String to;

    private String value;

    private long timestamp;
}
