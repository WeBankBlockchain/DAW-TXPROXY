package com.webank.wsdaw.txproxy.service;

import com.webank.wsdaw.txproxy.vo.request.AccountBalanceRequest;
import com.webank.wsdaw.txproxy.vo.request.CallRequest;
import org.fisco.bcos.sdk.v3.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

public interface ProxyService {

    public Call.CallOutput call(CallRequest request);

    public TransactionReceipt sendTransaction(String signedTransactionData, String network);

    public BlockNumber getBlockNumber();

    public String balanceOf(AccountBalanceRequest request) throws ContractException;
}
