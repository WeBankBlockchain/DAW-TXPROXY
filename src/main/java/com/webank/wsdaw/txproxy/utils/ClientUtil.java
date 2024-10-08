package com.webank.wsdaw.txproxy.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.webank.wsdaw.txproxy.vo.ChainInfo;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.fisco.bcos.sdk.v3.BcosSDK;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.config.ConfigOption;
import org.fisco.bcos.sdk.v3.config.exceptions.ConfigException;
import org.fisco.bcos.sdk.v3.config.model.ConfigProperty;

@Slf4j
public class ClientUtil {

    public static Client getClient(ChainInfo chainInfo) throws ConfigException {
        BcosSDK sdk = getSDK(chainInfo);
        return sdk.getClient(String.valueOf(chainInfo.getGroupId()));
    }

    public static BcosSDK getSDK(ChainInfo chainInfo) throws ConfigException {
        ConfigProperty configProperty = new ConfigProperty();
        setPeers(configProperty, chainInfo);
        setCertPath(configProperty, chainInfo);
        ConfigOption option = new ConfigOption(configProperty);
        return new BcosSDK(option);
    }

    public static void setPeers(ConfigProperty configProperty, ChainInfo chainInfo) {
        String[] nodes = StringUtils.split(chainInfo.getNodeStr(), ",");
        List<String> peers = Arrays.asList(nodes);
        peers = peers.stream().map(s -> StrUtil.replace(s, "\"", "")).collect(Collectors.toList());
        log.info("node peers are {}", JSONUtil.toJsonPrettyStr(peers));
        Map<String, Object> network = new HashMap<>();
        network.put("peers", peers);
        configProperty.setNetwork(network);
    }

    public static void setCertPath(ConfigProperty configProperty, ChainInfo chainInfo) {
        Map<String, Object> cryptoMaterial = new HashMap<>();
        cryptoMaterial.put("disableSsl", chainInfo.getDisableSsl());
        cryptoMaterial.put("certPath", chainInfo.getCertPath());
        cryptoMaterial.put("useSMCrypto", chainInfo.getCryptoTypeConfig() == 1 ? "true" : "false");
        configProperty.setCryptoMaterial(cryptoMaterial);
    }
}
