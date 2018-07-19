package net.thenova.transmission;

import de.arraying.kotys.JSON;
import de.arraying.kotys.JSONField;

import java.io.File;
import java.io.IOException;
import java.util.Stack;

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
public final class Configuration {

    @JSONField(key = "id") private String id;
    @JSONField(key = "redis-host") private String redisHost;
    @JSONField(key = "redis-port") private Integer redisPort;
    @JSONField(key = "redis-auth") private String redisAuth;

    /**
     * Loads the configuration.
     * @param parentFolder The parent folder.
     * @return The configuration.
     * @throws IOException If there is an IO error.
     */
    static Configuration load(File parentFolder) throws IOException  {
        if(!parentFolder.exists()) {
            throw new IOException("Parent folder did not exist, created: " + parentFolder.mkdirs());
        }
        File file = new File(parentFolder, "config.json");
        if(!file.exists()) {
            throw new IOException("Config file did not exist, created: " + file.createNewFile());
        }
        JSON json = new JSON(file);
        try {
            return json.marshal(Configuration.class);
        } catch(IllegalArgumentException | IllegalStateException exception) {
            exception.printStackTrace();
            throw new IOException("JSON error");
        }
    }

    /**
     * Gets the server ID.
     * @return The ID.
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the Redis host.
     * @return The host.
     */
    public String getRedisHost() {
        return redisHost;
    }

    /**
     * Gets the Redis port.
     * @return The port.
     */
    public Integer getRedisPort() {
        return redisPort;
    }

    /**
     * Gets the Redis auth.
     * @return The auth.
     */
    public String getRedisAuth() {
        return redisAuth;
    }

    /**
     * Whether or not the config is valid.
     * @return The validity of the config.
     */
    boolean isValid() {
        return id != null && !id.isEmpty()
                && redisHost != null && !redisHost.isEmpty()
                && redisPort != null && redisPort > 0
                && redisAuth != null && !redisAuth.isEmpty();
    }

}
