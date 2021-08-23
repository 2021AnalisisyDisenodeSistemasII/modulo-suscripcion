package com.povsal.starbank.service.definition;

import java.io.IOException;

public interface IAccountService {
    boolean accountBelongsToClient(String clientId, String accountId) throws IOException;
}
