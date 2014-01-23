package com.streamcipher.svcmaster.config;

import java.util.List;

public interface IServiceConfigReader {

    void setUpReader(String... params);

    List<ManagedServiceConfig> readConfiguration() throws Exception;
}
