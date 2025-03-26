package com.webank.wsdaw.txproxy.vo.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author grayson
 * @description TODO
 * @date 2023-11-09 22:46
 */
@Data
@NoArgsConstructor
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonRequest {
    private String seqNo;
}
