package com.webank.wsdaw.txproxy.contracts;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.Address;
import org.fisco.bcos.sdk.v3.codec.datatypes.Bool;
import org.fisco.bcos.sdk.v3.codec.datatypes.Event;
import org.fisco.bcos.sdk.v3.codec.datatypes.Function;
import org.fisco.bcos.sdk.v3.codec.datatypes.Type;
import org.fisco.bcos.sdk.v3.codec.datatypes.TypeReference;
import org.fisco.bcos.sdk.v3.codec.datatypes.Utf8String;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint8;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple1;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

@SuppressWarnings("unchecked")
public class Asset extends Contract {
    public static final String[] BINARY_ARRAY = {
        "608060405234801561001057600080fd5b50604051806040016040528060058152602001642a37b5b2b760d91b815250604051806040016040528060038152602001624d544b60e81b815250816003908051906020019061006192919061007d565b50805161007590600490602084019061007d565b505050610151565b82805461008990610116565b90600052602060002090601f0160209004810192826100ab57600085556100f1565b82601f106100c457805160ff19168380011785556100f1565b828001600101855582156100f1579182015b828111156100f15782518255916020019190600101906100d6565b506100fd929150610101565b5090565b5b808211156100fd5760008155600101610102565b600181811c9082168061012a57607f821691505b6020821081141561014b57634e487b7160e01b600052602260045260246000fd5b50919050565b6109b7806101606000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c806340c10f191161007157806340c10f191461014157806370a082311461015657806395d89b411461017f578063a457c2d714610187578063a9059cbb1461019a578063dd62ed3e146101ad57600080fd5b806306fdde03146100b9578063095ea7b3146100d757806318160ddd146100fa57806323b872dd1461010c578063313ce5671461011f578063395093511461012e575b600080fd5b6100c16101c0565b6040516100ce91906107d5565b60405180910390f35b6100ea6100e5366004610846565b610252565b60405190151581526020016100ce565b6002545b6040519081526020016100ce565b6100ea61011a366004610870565b61026a565b604051601281526020016100ce565b6100ea61013c366004610846565b61028e565b61015461014f366004610846565b6102b0565b005b6100fe6101643660046108ac565b6001600160a01b031660009081526020819052604090205490565b6100c16102be565b6100ea610195366004610846565b6102cd565b6100ea6101a8366004610846565b61034f565b6100fe6101bb3660046108ce565b61035d565b6060600380546101cf90610901565b80601f01602080910402602001604051908101604052809291908181526020018280546101fb90610901565b80156102485780601f1061021d57610100808354040283529160200191610248565b820191906000526020600020905b81548152906001019060200180831161022b57829003601f168201915b5050505050905090565b600033610260818585610388565b5060019392505050565b6000336102788582856104ac565b61028385858561051f565b506001949350505050565b6000336102608185856102a1838361035d565b6102ab9190610952565b610388565b6102ba82826106f6565b5050565b6060600480546101cf90610901565b600033816102db828661035d565b9050838110156103405760405162461bcd60e51b815260206004820152602560248201527f42414332303a2064656372656173656420616c6c6f77616e63652062656c6f77604482015264207a65726f60d81b60648201526084015b60405180910390fd5b61028382866102ab878561096a565b60003361026081858561051f565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b6001600160a01b0383166103ea5760405162461bcd60e51b8152602060048201526024808201527f42414332303a20617070726f76652066726f6d20746865207a65726f206164646044820152637265737360e01b6064820152608401610337565b6001600160a01b03821661044b5760405162461bcd60e51b815260206004820152602260248201527f42414332303a20617070726f766520746f20746865207a65726f206164647265604482015261737360f01b6064820152608401610337565b6001600160a01b0383811660008181526001602090815260408083209487168084529482529182902085905590518481527f8c5be1e5ebec7d5bd14f71427d1e84f3dd0314c0f7b2291e5b200ac8c7c3b925910160405180910390a3505050565b60006104b8848461035d565b90508181101561050a5760405162461bcd60e51b815260206004820152601d60248201527f42414332303a20696e73756666696369656e7420616c6c6f77616e63650000006044820152606401610337565b61051984846102ab858561096a565b50505050565b6001600160a01b0383166105835760405162461bcd60e51b815260206004820152602560248201527f42414332303a207472616e736665722066726f6d20746865207a65726f206164604482015264647265737360d81b6064820152608401610337565b6001600160a01b0382166105e55760405162461bcd60e51b815260206004820152602360248201527f42414332303a207472616e7366657220746f20746865207a65726f206164647260448201526265737360e81b6064820152608401610337565b6001600160a01b0383166000908152602081905260409020548181101561065d5760405162461bcd60e51b815260206004820152602660248201527f42414332303a207472616e7366657220616d6f756e7420657863656564732062604482015265616c616e636560d01b6064820152608401610337565b610667828261096a565b6001600160a01b03808616600090815260208190526040808220939093559085168152908120805484929061069d908490610952565b92505081905550826001600160a01b0316846001600160a01b03167fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef846040516106e991815260200190565b60405180910390a3610519565b6001600160a01b03821661074c5760405162461bcd60e51b815260206004820152601f60248201527f42414332303a206d696e7420746f20746865207a65726f2061646472657373006044820152606401610337565b806002600082825461075e9190610952565b90915550506001600160a01b0382166000908152602081905260408120805483929061078b908490610952565b90915550506040518181526001600160a01b038316906000907fddf252ad1be2c89b69c2b068fc378daa952ba7f163c4a11628f55a4df523b3ef9060200160405180910390a35050565b600060208083528351808285015260005b81811015610802578581018301518582016040015282016107e6565b81811115610814576000604083870101525b50601f01601f1916929092016040019392505050565b80356001600160a01b038116811461084157600080fd5b919050565b6000806040838503121561085957600080fd5b6108628361082a565b946020939093013593505050565b60008060006060848603121561088557600080fd5b61088e8461082a565b925061089c6020850161082a565b9150604084013590509250925092565b6000602082840312156108be57600080fd5b6108c78261082a565b9392505050565b600080604083850312156108e157600080fd5b6108ea8361082a565b91506108f86020840161082a565b90509250929050565b600181811c9082168061091557607f821691505b6020821081141561093657634e487b7160e01b600052602260045260246000fd5b50919050565b634e487b7160e01b600052601160045260246000fd5b600082198211156109655761096561093c565b500190565b60008282101561097c5761097c61093c565b50039056fea26469706673582212208fa526eb91b962d2e5ca673d5f89de09f337c97538d44f73a27cb4a37752f99264736f6c634300080b0033"
    };

    public static final String BINARY =
            org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {
        "608060405234801561001057600080fd5b50604051806040016040528060058152602001642a37b5b2b760d91b815250604051806040016040528060038152602001624d544b60e81b815250816003908051906020019061006192919061007d565b50805161007590600490602084019061007d565b505050610151565b82805461008990610116565b90600052602060002090601f0160209004810192826100ab57600085556100f1565b82601f106100c457805160ff19168380011785556100f1565b828001600101855582156100f1579182015b828111156100f15782518255916020019190600101906100d6565b506100fd929150610101565b5090565b5b808211156100fd5760008155600101610102565b600181811c9082168061012a57607f821691505b6020821081141561014b5763b95aa35560e01b600052602260045260246000fd5b50919050565b6109b7806101606000396000f3fe608060405234801561001057600080fd5b50600436106100b45760003560e01c80635bfa2796116100715780635bfa27961461013d5780636904e96514610152578063852d921314610165578063ad8a973114610178578063b11b68831461018b578063cc8be70e1461019357600080fd5b80630256e278146100b957806313a1aa5c146100d05780631f2d4860146100f357806346b13615146101065780634d2e8120146101155780634f5e00c714610128575b600080fd5b6002545b6040519081526020015b60405180910390f35b6100e36100de3660046107f1565b6101bc565b60405190151581526020016100c7565b6100e36101013660046107f1565b61024f565b604051601281526020016100c7565b6100e36101233660046107f1565b610267565b61013b6101363660046107f1565b610284565b005b610145610292565b6040516100c7919061081b565b6100e36101603660046107f1565b610324565b6100bd610173366004610870565b610332565b6100e36101863660046108a3565b61035d565b610145610376565b6100bd6101a13660046108df565b6001600160a01b031660009081526020819052604090205490565b600033816101ca8286610332565b90508381101561023057604051636381e58960e11b815260206004820152602560248201527f42414332303a2064656372656173656420616c6c6f77616e63652062656c6f77604482015264207a65726f60d81b60648201526084015b60405180910390fd5b610244828661023f8785610917565b610385565b506001949350505050565b60003361025d818585610385565b5060019392505050565b60003361025d81858561027a8383610332565b61023f919061092e565b61028e82826104ab565b5050565b6060600480546102a190610946565b80601f01602080910402602001604051908101604052809291908181526020018280546102cd90610946565b801561031a5780601f106102ef5761010080835404028352916020019161031a565b820191906000526020600020905b8154815290600101906020018083116102fd57829003601f168201915b5050505050905090565b60003361025d81858561058b565b6001600160a01b03918216600090815260016020908152604080832093909416825291909152205490565b60003361036b858285610767565b61024485858561058b565b6060600380546102a190610946565b6001600160a01b0383166103e857604051636381e58960e11b8152602060048201526024808201527f42414332303a20617070726f76652066726f6d20746865207a65726f206164646044820152637265737360e01b6064820152608401610227565b6001600160a01b03821661044a57604051636381e58960e11b815260206004820152602260248201527f42414332303a20617070726f766520746f20746865207a65726f206164647265604482015261737360f01b6064820152608401610227565b6001600160a01b0383811660008181526001602090815260408083209487168084529482529182902085905590518481527fd1e45707b3f71c77903b61f04c900f772db264b9bf618f1cc3308fb516eb6169910160405180910390a3505050565b6001600160a01b03821661050257604051636381e58960e11b815260206004820152601f60248201527f42414332303a206d696e7420746f20746865207a65726f2061646472657373006044820152606401610227565b8060026000828254610514919061092e565b90915550506001600160a01b0382166000908152602081905260408120805483929061054190849061092e565b90915550506040518181526001600160a01b038316906000907f18f84334255a242551aa98c68047b5da8063eab9fbeaec1eddeea280044b9ff19060200160405180910390a35050565b6001600160a01b0383166105f057604051636381e58960e11b815260206004820152602560248201527f42414332303a207472616e736665722066726f6d20746865207a65726f206164604482015264647265737360d81b6064820152608401610227565b6001600160a01b03821661065357604051636381e58960e11b815260206004820152602360248201527f42414332303a207472616e7366657220746f20746865207a65726f206164647260448201526265737360e81b6064820152608401610227565b6001600160a01b038316600090815260208190526040902054818110156106cc57604051636381e58960e11b815260206004820152602660248201527f42414332303a207472616e7366657220616d6f756e7420657863656564732062604482015265616c616e636560d01b6064820152608401610227565b6106d68282610917565b6001600160a01b03808616600090815260208190526040808220939093559085168152908120805484929061070c90849061092e565b92505081905550826001600160a01b0316846001600160a01b03167f18f84334255a242551aa98c68047b5da8063eab9fbeaec1eddeea280044b9ff18460405161075891815260200190565b60405180910390a35b50505050565b60006107738484610332565b9050818110156107c657604051636381e58960e11b815260206004820152601d60248201527f42414332303a20696e73756666696369656e7420616c6c6f77616e63650000006044820152606401610227565b610761848461023f8585610917565b80356001600160a01b03811681146107ec57600080fd5b919050565b6000806040838503121561080457600080fd5b61080d836107d5565b946020939093013593505050565b600060208083528351808285015260005b818110156108485785810183015185820160400152820161082c565b8181111561085a576000604083870101525b50601f01601f1916929092016040019392505050565b6000806040838503121561088357600080fd5b61088c836107d5565b915061089a602084016107d5565b90509250929050565b6000806000606084860312156108b857600080fd5b6108c1846107d5565b92506108cf602085016107d5565b9150604084013590509250925092565b6000602082840312156108f157600080fd5b6108fa826107d5565b9392505050565b63b95aa35560e01b600052601160045260246000fd5b60008282101561092957610929610901565b500390565b6000821982111561094157610941610901565b500190565b600181811c9082168061095a57607f821691505b6020821081141561097b5763b95aa35560e01b600052602260045260246000fd5b5091905056fea2646970667358221220e52f094151e3d0f0b3c37ae2c01ae82b021b2d0f04b1f182fedbc49a6d94a59964736f6c634300080b0033"
    };

    public static final String SM_BINARY =
            org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {
        "[{\"inputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"constructor\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Approval\",\"type\":\"event\"},{\"anonymous\":false,\"inputs\":[{\"indexed\":true,\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"indexed\":true,\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"indexed\":false,\"internalType\":\"uint256\",\"name\":\"value\",\"type\":\"uint256\"}],\"name\":\"Transfer\",\"type\":\"event\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"owner\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"}],\"name\":\"allowance\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[3714247998,2234356243],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"approve\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[157198259,523061344],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"account\",\"type\":\"address\"}],\"name\":\"balanceOf\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[1889567281,3431720718],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":5}],\"inputs\":[],\"name\":\"decimals\",\"outputs\":[{\"internalType\":\"uint8\",\"name\":\"\",\"type\":\"uint8\"}],\"selector\":[826074471,1186018837],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"subtractedValue\",\"type\":\"uint256\"}],\"name\":\"decreaseAllowance\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[2757214935,329362012],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":2,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"spender\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"addedValue\",\"type\":\"uint256\"}],\"name\":\"increaseAllowance\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[961581905,1294893344],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":4,\"value\":[2]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"mint\",\"outputs\":[],\"selector\":[1086394137,1331560647],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[3]}],\"inputs\":[],\"name\":\"name\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"selector\":[117300739,2971363459],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[4]}],\"inputs\":[],\"name\":\"symbol\",\"outputs\":[{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"}],\"selector\":[2514000705,1543120790],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":4,\"value\":[2]}],\"inputs\":[],\"name\":\"totalSupply\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"selector\":[404098525,39248504],\"stateMutability\":\"view\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":0,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transfer\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[2835717307,1761929573],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"conflictFields\":[{\"kind\":0},{\"kind\":2,\"slot\":1,\"value\":[0]}],\"inputs\":[{\"internalType\":\"address\",\"name\":\"from\",\"type\":\"address\"},{\"internalType\":\"address\",\"name\":\"to\",\"type\":\"address\"},{\"internalType\":\"uint256\",\"name\":\"amount\",\"type\":\"uint256\"}],\"name\":\"transferFrom\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"selector\":[599290589,2911541041],\"stateMutability\":\"nonpayable\",\"type\":\"function\"}]"
    };

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_ALLOWANCE = "allowance";

    public static final String FUNC_APPROVE = "approve";

    public static final String FUNC_BALANCEOF = "balanceOf";

    public static final String FUNC_DECIMALS = "decimals";

    public static final String FUNC_DECREASEALLOWANCE = "decreaseAllowance";

    public static final String FUNC_INCREASEALLOWANCE = "increaseAllowance";

    public static final String FUNC_MINT = "mint";

    public static final String FUNC_NAME = "name";

    public static final String FUNC_SYMBOL = "symbol";

    public static final String FUNC_TOTALSUPPLY = "totalSupply";

    public static final String FUNC_TRANSFER = "transfer";

    public static final String FUNC_TRANSFERFROM = "transferFrom";

    public static final Event APPROVAL_EVENT =
            new Event(
                    "Approval",
                    Arrays.asList(
                            new TypeReference<Address>(true) {},
                            new TypeReference<Address>(true) {},
                            new TypeReference<Uint256>() {}));

    public static final Event TRANSFER_EVENT =
            new Event(
                    "Transfer",
                    Arrays.asList(
                            new TypeReference<Address>(true) {},
                            new TypeReference<Address>(true) {},
                            new TypeReference<Uint256>() {}));

    protected Asset(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public static Asset load(String contractAddress, Client client, CryptoKeyPair credential) {
        return new Asset(contractAddress, client, credential);
    }

    public static Asset deploy(Client client, CryptoKeyPair credential) throws ContractException {
        return deploy(
                Asset.class,
                client,
                credential,
                getBinary(client.getCryptoSuite()),
                getABI(),
                null,
                null);
    }

    public List<ApprovalEventResponse> getApprovalEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList =
                extractEventParametersWithLog(APPROVAL_EVENT, transactionReceipt);
        ArrayList<ApprovalEventResponse> responses =
                new ArrayList<ApprovalEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ApprovalEventResponse typedResponse = new ApprovalEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.owner = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.spender = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public List<TransferEventResponse> getTransferEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList =
                extractEventParametersWithLog(TRANSFER_EVENT, transactionReceipt);
        ArrayList<TransferEventResponse> responses =
                new ArrayList<TransferEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            TransferEventResponse typedResponse = new TransferEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.from = (String) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.to = (String) eventValues.getIndexedValues().get(1).getValue();
            typedResponse.value = (BigInteger) eventValues.getNonIndexedValues().get(0).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public BigInteger allowance(String owner, String spender) throws ContractException {
        final Function function =
                new Function(
                        FUNC_ALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(owner),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender)),
                        Collections.singletonList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt approve(String spender, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        4);
        return executeTransaction(function);
    }

    public String getSignedTransactionForApprove(String spender, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        4);
        return createSignedTransaction(function);
    }

    public String approve(String spender, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        4);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getApproveInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public Tuple1<Boolean> getApproveOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_APPROVE,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>((Boolean) results.get(0).getValue());
    }

    public BigInteger balanceOf(String account) throws ContractException {
        final Function function =
                new Function(
                        FUNC_BALANCEOF,
                        Collections.singletonList(new Address(account)),
                        Collections.singletonList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public BigInteger decimals() throws ContractException {
        final Function function =
                new Function(
                        FUNC_DECIMALS,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Uint8>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt decreaseAllowance(String spender, BigInteger subtractedValue) {
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        subtractedValue)),
                        Collections.emptyList(),
                        4);
        return executeTransaction(function);
    }

    public String getSignedTransactionForDecreaseAllowance(
            String spender, BigInteger subtractedValue) {
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        subtractedValue)),
                        Collections.emptyList(),
                        4);
        return createSignedTransaction(function);
    }

    public String decreaseAllowance(
            String spender, BigInteger subtractedValue, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        subtractedValue)),
                        Collections.emptyList(),
                        4);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getDecreaseAllowanceInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public Tuple1<Boolean> getDecreaseAllowanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_DECREASEALLOWANCE,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>((Boolean) results.get(0).getValue());
    }

    public TransactionReceipt increaseAllowance(String spender, BigInteger addedValue) {
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        addedValue)),
                        Collections.emptyList(),
                        4);
        return executeTransaction(function);
    }

    public String getSignedTransactionForIncreaseAllowance(String spender, BigInteger addedValue) {
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        addedValue)),
                        Collections.emptyList(),
                        4);
        return createSignedTransaction(function);
    }

    public String increaseAllowance(
            String spender, BigInteger addedValue, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(spender),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        addedValue)),
                        Collections.emptyList(),
                        4);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getIncreaseAllowanceInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public Tuple1<Boolean> getIncreaseAllowanceOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_INCREASEALLOWANCE,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>((Boolean) results.get(0).getValue());
    }

    public TransactionReceipt mint(String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_MINT,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForMint(String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_MINT,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return createSignedTransaction(function);
    }

    public String mint(String to, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_MINT,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getMintInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_MINT,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public String name() throws ContractException {
        final Function function =
                new Function(
                        FUNC_NAME,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public String symbol() throws ContractException {
        final Function function =
                new Function(
                        FUNC_SYMBOL,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Utf8String>() {}));
        return executeCallWithSingleValueReturn(function, String.class);
    }

    public BigInteger totalSupply() throws ContractException {
        final Function function =
                new Function(
                        FUNC_TOTALSUPPLY,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public TransactionReceipt transfer(String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForTransfer(String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return createSignedTransaction(function);
    }

    public String transfer(String to, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple2<String, BigInteger> getTransferInput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {}, new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple2<String, BigInteger>(
                (String) results.get(0).getValue(), (BigInteger) results.get(1).getValue());
    }

    public Tuple1<Boolean> getTransferOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_TRANSFER,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>((Boolean) results.get(0).getValue());
    }

    public TransactionReceipt transferFrom(String from, String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(from),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForTransferFrom(String from, String to, BigInteger amount) {
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(from),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return createSignedTransaction(function);
    }

    public String transferFrom(
            String from, String to, BigInteger amount, TransactionCallback callback) {
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Arrays.asList(
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(from),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.Address(to),
                                new org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256(
                                        amount)),
                        Collections.emptyList(),
                        0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<String, String, BigInteger> getTransferFromInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Collections.emptyList(),
                        Arrays.asList(
                                new TypeReference<Address>() {},
                                new TypeReference<Address>() {},
                                new TypeReference<Uint256>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<String, String, BigInteger>(
                (String) results.get(0).getValue(),
                (String) results.get(1).getValue(),
                (BigInteger) results.get(2).getValue());
    }

    public Tuple1<Boolean> getTransferFromOutput(TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getOutput();
        final Function function =
                new Function(
                        FUNC_TRANSFERFROM,
                        Collections.emptyList(),
                        Collections.singletonList(new TypeReference<Bool>() {}));
        List<Type> results =
                this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple1<Boolean>((Boolean) results.get(0).getValue());
    }

    public static class ApprovalEventResponse {
        public TransactionReceipt.Logs log;

        public String owner;

        public String spender;

        public BigInteger value;
    }

    public static class TransferEventResponse {
        public TransactionReceipt.Logs log;

        public String from;

        public String to;

        public BigInteger value;
    }
}
