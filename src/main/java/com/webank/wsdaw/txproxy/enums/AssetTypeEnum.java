package com.webank.wsdaw.txproxy.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author grayson
 * @description TODO
 * @date 2023-11-09 23:31
 */
@AllArgsConstructor
@Getter
public enum AssetTypeEnum {
    ETH(1),
    ERC20(2),
    ERC721(3);

    private final int type;
}
