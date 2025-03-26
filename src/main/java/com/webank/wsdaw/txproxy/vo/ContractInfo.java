package com.webank.wsdaw.txproxy.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author wesleywang @Description:
 * @date 2020/12/25
 */
@Data
@Accessors(chain = true)
public class ContractInfo {

    private String symbol;

    private String address;

    private String abi;

    private String network;
}
