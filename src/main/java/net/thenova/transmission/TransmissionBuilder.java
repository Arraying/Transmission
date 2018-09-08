package net.thenova.transmission;

import net.thenova.transmission.redis.RedisConfig;

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
public final class TransmissionBuilder {

    private String identifier = Transmission.GLOBAL_IDENTIFIER;
    private RedisConfig config = new RedisConfig("localhost", 6379, "password");
    private PacketHandler handler = new PacketHandler.NoImplementation();

    /**
     * Sets the identifier for Transmission.
     * The identifier is used to determine who a packet should get sent to.
     * @param identifier The identifier.
     * @return The builder, for chaining.
     */
    public TransmissionBuilder withIdentifier(String identifier) {
        if(identifier == null) {
            throw new IllegalArgumentException("identifier null");
        }
        this.identifier = identifier;
        return this;
    }

    /**
     * Sets the Redis config for Transmission.
     * @param config The config.
     * @return The builder, for chaining.
     */
    public TransmissionBuilder withConfig(RedisConfig config) {
        if(config == null) {
            throw new IllegalArgumentException("config is null");
        }
        this.config = config;
        return this;
    }

    /**
     * Sets the packet handler.
     * @param handler The packet handler.
     * @return The builder, for chaining.
     */
    public TransmissionBuilder withHandler(PacketHandler handler) {
        if(handler == null) {
            throw new IllegalArgumentException("event handler is null");
        }
        this.handler = handler;
        return this;
    }

    /**
     * Makes Transmission.
     * @return Transmission itself.
     */
    public Transmission build() {
        return new Transmission(identifier, config, handler);
    }

}
