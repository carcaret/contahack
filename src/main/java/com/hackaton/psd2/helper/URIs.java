package com.hackaton.psd2.helper;

public class URIs {

    private URIs() {
    }

    public static final String MY_ACCOUNTS =
            "https://apisandbox.openbankproject.com/obp/v2.0.0/my/accounts";

    public static final String TAGS =
            "https://apisandbox.openbankproject.com/obp/v2.0.0/banks/at03-bank-x/accounts/1fc1d0a8-25ff-4fee-a1d0-cbc8dfe64601/accountant/transactions/6f668296-b5ef-4980-888d-8da28cb274e9/metadata/tags";

    public static final String TRANSACTION_URL = "https://apisandbox.openbankproject.com/obp/v2.0.0/banks/%1$s/accounts/%2$s/%3$s/transactions";
}
