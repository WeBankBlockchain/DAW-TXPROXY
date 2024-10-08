package com.webank.wsdaw.txproxy.controller;

import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.txproxy.config.SystemConfig;
import com.webank.wsdaw.txproxy.service.fisco.FiscoProxyService;
import com.webank.wsdaw.txproxy.vo.request.AccountBalanceRequest;
import com.webank.wsdaw.txproxy.vo.request.CallRequest;
import com.webank.wsdaw.txproxy.vo.request.SendTxRequest;
import com.webank.wsdaw.txproxy.vo.response.CommonResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/bcos")
@Slf4j
public class FiscoController {

    @Autowired private FiscoProxyService fiscoProxyService;

    @Autowired private SystemConfig systemEnvironmentConfig;

    @PostMapping("sendTransaction")
    public CommonResponse<TransactionReceipt> sendTransaction(
            @RequestBody SendTxRequest sendTxRequest) throws Exception {
        log.info("get data: {}", JSONUtil.toJsonPrettyStr(sendTxRequest));
        TransactionReceipt receipt =
                fiscoProxyService.sendTransaction(
                        sendTxRequest.getSignedTransactionData(), sendTxRequest.getNetwork());
        log.info("send tx receipt: {}, {}", receipt.getStatus(), receipt.getMessage());
        return CommonResponse.success(receipt);
    }

    @PostMapping("call")
    public CommonResponse<Call.CallOutput> call(@RequestBody @Valid CallRequest request)
            throws Exception {
        log.info("Call request is {}", JSONUtil.toJsonPrettyStr(request));
        Call.CallOutput output = fiscoProxyService.call(request);
        return CommonResponse.success(output);
    }

    @PostMapping("getBlockNumber")
    public CommonResponse<Long> getBlockNumber() {
        long blockNumber = fiscoProxyService.getBlockNumber().getBlockNumber().longValue();
        log.info("BlockNumber is {}", blockNumber);
        return CommonResponse.success(blockNumber);
    }

    @PostMapping("balanceOf")
    public CommonResponse<String> balanceOf(@RequestBody @Valid AccountBalanceRequest request)
            throws Exception {
        String b = fiscoProxyService.balanceOf(request);
        log.info(
                "contract {}, account {} balance: {}",
                request.getContractAddress(),
                request.getAccountAddress(),
                b);
        return CommonResponse.success(b);
    }
}
