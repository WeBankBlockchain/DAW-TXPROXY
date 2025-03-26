package com.webank.wsdaw.txproxy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author grayson
 * @description TODO
 * @date 2023-11-09 23:37
 */
@AllArgsConstructor
@Getter
public enum NetWorkTypeEnum {
    FISCO("FISCOBCOS"),
    Ethereum("Ethereum");
    private final String type;
}
