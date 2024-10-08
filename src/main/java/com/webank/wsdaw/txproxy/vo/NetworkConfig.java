package com.webank.wsdaw.txproxy.vo;

import java.util.List;
import lombok.Data;

@Data
public class NetworkConfig {

    private List<ChainInfo> chainInfoList;

    private List<ContractInfo> contractInfoList;
}
