package com.povsal.starbank.service.definition;

import java.io.IOException;

public interface ISucursalService {
    boolean sucursalExists(String sucursalId) throws IOException;
}
