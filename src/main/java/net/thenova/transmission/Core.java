package net.thenova.transmission;

import net.thenova.transmission.packet.PacketHandler;
import net.thenova.transmission.proxy.ProxyCore;
import net.thenova.transmission.redis.Redis;
import net.thenova.transmission.server.ServerCore;
import redis.clients.jedis.exceptions.JedisConnectionException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

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
public enum Core {

    /**
     * The core instance.
     */
    INSTANCE;

    private Logger logger;
    private Configuration configuration;
    private PacketHandler packetHandler;
    private File pluginDirectory;

    /**
     * Initializes as the proxy core.
     * @param core The core.
     */
    public void hello(ProxyCore core) {
        logger = core.getLogger();
        packetHandler = core.getHandler();
        pluginDirectory = core.getDataFolder();
    }

    /**
     * Initializes the server core.
     * @param core The core.
     */
    public void hello(ServerCore core) {
        logger = core.getLogger();
        packetHandler = core.getHandler();
        pluginDirectory = core.getDataFolder();
    }

    /**
     * Attempts to initialize the plugin.
     * @return True if successful, false otherwise.
     */
    public boolean init() {
        try {
            configuration = Configuration.load(pluginDirectory);
        } catch(IOException exception) {
            exception.printStackTrace();
            return false;
        }
        if(!configuration.isValid()) {
            logger.severe("Configuration is invalid");
            return false;
        }
        try {
            Redis.INSTANCE.init();
        } catch(JedisConnectionException exception) {
            exception.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * Gets the configuration.
     * @return The configuration.
     */
    public Configuration getConfiguration() {
        return configuration;
    }

    /**
     * Gets the packet handler.
     * @return The handler.
     */
    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

}
