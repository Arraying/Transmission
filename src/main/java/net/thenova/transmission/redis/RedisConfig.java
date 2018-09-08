package net.thenova.transmission.redis;

/**
 * Copyright 2018 Arraying
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
public final class RedisConfig {

    private final String host;
    private final int port;
    private final String auth;

    /**
     * Creates a new Redis config.
     * @param host The host.
     * @param port The port.
     * @param auth The authentication string.
     */
    public RedisConfig(String host, int port, String auth) {
        this.host = host;
        this.port = port;
        this.auth = auth;
    }

    /**
     * Gets the host.
     * @return The host.
     */
    String getHost() {
        return host;
    }

    /**
     * Gets the port.
     * @return The port.
     */
    int getPort() {
        return port;
    }

    /**
     * Gets the auth.
     * @return The auth.
     */
    String getAuth() {
        return auth;
    }

}
