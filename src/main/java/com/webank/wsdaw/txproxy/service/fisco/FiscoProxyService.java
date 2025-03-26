package com.webank.wsdaw.txproxy.service.fisco;

import com.webank.wsdaw.txproxy.contracts.Asset;
import com.webank.wsdaw.txproxy.service.ProxyService;
import com.webank.wsdaw.txproxy.vo.request.AccountBalanceRequest;
import com.webank.wsdaw.txproxy.vo.request.CallRequest;
import java.math.BigInteger;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.client.protocol.request.Transaction;
import org.fisco.bcos.sdk.v3.client.protocol.response.BlockNumber;
import org.fisco.bcos.sdk.v3.client.protocol.response.Call;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;
import org.fisco.bcos.sdk.v3.utils.Hex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FiscoProxyService implements ProxyService {

    @Autowired private CryptoKeyPair cryptoKeyPair;
    @Autowired private Client client;

    @Override
    public Call.CallOutput call(CallRequest request) {
        Transaction transaction =
                new Transaction(request.getFrom(), request.getTo(), Hex.decode(request.getData()));
        return client.call(transaction).getCallResult();
    }

    @Override
    public TransactionReceipt sendTransaction(String signedTransactionData, String network) {
        log.info("Sending transaction: {}", signedTransactionData);
        TransactionReceipt transactionReceipt =
                client.sendTransaction(signedTransactionData, false).getTransactionReceipt();
        log.info(
                "receipt is {}, {}",
                transactionReceipt.getStatus(),
                transactionReceipt.getMessage());
        return transactionReceipt;
    }

    @Override
    public BlockNumber getBlockNumber() {
        return client.getBlockNumber();
    }

    @Override
    public String balanceOf(AccountBalanceRequest request) throws ContractException {
        Asset asset = Asset.load(request.getContractAddress(), client, cryptoKeyPair);
        BigInteger balance = asset.balanceOf(request.getAccountAddress());
        return balance.toString();
    }
}
