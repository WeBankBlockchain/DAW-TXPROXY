package com.webank.wsdaw.txproxy.vo.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class AssetInfo {
    private String contractAddress;
    private String assetName;
    private String assetSymbol;
}
