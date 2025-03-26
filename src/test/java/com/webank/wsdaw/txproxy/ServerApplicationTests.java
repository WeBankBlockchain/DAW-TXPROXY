package com.webank.wsdaw.txproxy;

import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ServerApplicationTests {
    @Autowired protected CryptoSuite cryptoSuite;
}
