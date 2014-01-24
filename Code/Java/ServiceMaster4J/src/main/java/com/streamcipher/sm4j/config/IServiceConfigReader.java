package com.streamcipher.sm4j.config;

import java.util.List;

public interface IServiceConfigReader {

    void setUpReader(String... params);

    List<ManagedServiceConfig> readConfiguration() throws Exception;
}
