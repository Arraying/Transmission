package net.thenova.transmission;

import net.thenova.transmission.redis.RedisConfig;
import net.thenova.transmission.redis.RedisHandler;
import redis.clients.jedis.exceptions.JedisException;

import java.util.concurrent.atomic.AtomicBoolean;

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
@SuppressWarnings("WeakerAccess")
public final class Transmission {

    /**
     * A constant identifier that is used as a default fallback.
     */
    public static final String GLOBAL_IDENTIFIER = "@default";

    private final AtomicBoolean running = new AtomicBoolean(false);
    private final String identifier;
    private final RedisConfig config;
    private final PacketHandler packetHandler;
    private RedisHandler redisHandler;

    /**
     * Creates a new Transmission.
     * @param identifier The identifier.
     * @param config The config.
     * @param packetHandler The packet handler.
     */
    Transmission(String identifier, RedisConfig config, PacketHandler packetHandler) {
        this.identifier = identifier;
        this.config = config;
        this.packetHandler = packetHandler;
    }

    /**
     * Starts Transmission.
     * @return This.
     */
    public Transmission start() {
        if(isRunning()) {
            return this;
        }
        try {
            redisHandler = new RedisHandler(this);
            redisHandler.start();
            running.set(true);
        } catch(JedisException exception) {
            exception.printStackTrace();
        }
        return this;
    }

    /**
     * Stops Transmission.
     * @return This.
     */
    public Transmission stop() {
        if(!isRunning()
                || redisHandler == null) {
            return this;
        }
        redisHandler.stop();
        running.set(false);
        return this;
    }

    /**
     * Whether or not Transmission is running.
     * @return True if it is, false otherwise.
     */
    public boolean isRunning() {
        return running.get();
    }

    /**
     * Creates a packet.
     * @return The packet creator.
     */
    public PacketCreator createPacket() {
        return new PacketCreator(this);
    }

    /**
     * Gets the identifier.
     * @return The identifier.
     */
    public String getIdentifier() {
        return identifier;
    }

    /**
     * Gets the config.
     * @return The config.
     */
    public RedisConfig getConfig() {
        return config;
    }

    /**
     * Gets the packet handler.
     * @return The packet handler.
     */
    public PacketHandler getPacketHandler() {
        return packetHandler;
    }

    /**
     * Gets the Redis handler.
     * @return A possibly null Redis handler.
     */
    RedisHandler getRedisHandler() {
        return redisHandler;
    }

}
