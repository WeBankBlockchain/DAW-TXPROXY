package com.webank.wsdaw.txproxy.config;

import com.webank.wsdaw.txproxy.utils.ClientUtil;
import com.webank.wsdaw.txproxy.vo.ChainInfo;
import lombok.extern.slf4j.Slf4j;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = true)
@Slf4j
public class BeanConfig {

    @Autowired private SystemConfig systemConfig;

    @Bean
    public CryptoSuite cryptoSuite() {
        CryptoSuite suite = new CryptoSuite(0);
        return suite;
    }

    @Bean
    public CryptoKeyPair cryptoKeyPair() {
        CryptoSuite suite = new CryptoSuite(0);
        return suite.generateRandomKeyPair();
    }

    @Bean
    public Client client() throws ConfigException {
        ChainInfo chainInfo = new ChainInfo();
        chainInfo.setNodeStr(systemConfig.getNodeStr());
        return ClientUtil.getClient(chainInfo);
    }
}
