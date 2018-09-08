package net.thenova.transmission.redis;

import net.thenova.transmission.Transmission;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.exceptions.JedisException;

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
public final class RedisHandler {

    /**
     * The channel name.
     */
    static final String CHANNEL = "tr";

    private final Transmission transmission;
    private JedisPool pool;

    /**
     * Creates a new Redis handler.
     * @param transmission The Transmission.
     */
    public RedisHandler(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Starts the pool.
     * @throws JedisException If there is an error.
     */
    public void start() throws JedisException {
        RedisConfig config = transmission.getConfig();
        pool = new JedisPool(new JedisPoolConfig(), config.getHost(), config.getPort(), Integer.MAX_VALUE, config.getAuth());
        try(Jedis connection = pool.getResource()) {
            connection.ping();
        }
        new Thread(() -> {
            try(Jedis jedis = pool.getResource()) {
                jedis.subscribe(new RedisListener(transmission), CHANNEL);
            }
        }).start();
    }

    /**
     * Publishes a message.
     * @param message The message.
     */
    public void publish(String message) {
        try(Jedis connection = pool.getResource()) {
            connection.publish(CHANNEL, message);
        } catch(JedisException exception) {
            exception.printStackTrace();
        }
    }

    /**
     * Closes the pool.
     */
    public void stop() {
        pool.close();
    }

}
