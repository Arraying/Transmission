package net.thenova.transmission.redis;

import net.thenova.transmission.Configuration;
import net.thenova.transmission.Core;
import net.thenova.transmission.packet.Packet;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisConnectionException;

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
public enum Redis {

    /**
     * The Redis handles instance.
     */
    INSTANCE;

    /**
     * The channel name.
     */
    public static final String CHANNEL = "tr";
    private JedisPool pool;

    /**
     * Initializes the Redis handler.
     * @throws JedisConnectionException If an error occurs connecting.
     */
    public void init() throws JedisConnectionException {
        Configuration configuration = Core.INSTANCE.getConfiguration();
        pool = new JedisPool(new JedisPoolConfig(),
                configuration.getRedisHost(),
                configuration.getRedisPort(),
                Integer.MAX_VALUE,
                configuration.getRedisAuth());
        try(Jedis jedis = pool.getResource()) {
            jedis.ping();
        }
        new Thread(() -> {
            try(Jedis jedis = pool.getResource()) {
                jedis.subscribe(new RedisListener(), CHANNEL);
            }
        }).start();
    }

    /**
     * Dispatches a packet.
     * @param packet The packet.
     */
    public void dispatch(Packet packet) {
        try(Jedis jedis = pool.getResource()) {
            jedis.publish(CHANNEL, packet.toJSON().marshal());
        } catch(JedisConnectionException exception) {
            exception.printStackTrace();
        }
    }

}
