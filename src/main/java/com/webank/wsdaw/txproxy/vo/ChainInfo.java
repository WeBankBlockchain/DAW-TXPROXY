package com.webank.wsdaw.txproxy.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ChainInfo {

    private String nodeStr;
    private String disableSsl = "true";
    private String groupId = "group0";
    private String certPath = "";
    // 0-ECDSA,1-SM
    private int cryptoTypeConfig = 0;
    private String network = "main";
}
