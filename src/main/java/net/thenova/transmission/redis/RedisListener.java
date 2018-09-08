package net.thenova.transmission.redis;

import de.arraying.kotys.JSON;
import net.thenova.transmission.Transmission;
import net.thenova.transmission.Packet;
import redis.clients.jedis.JedisPubSub;

import java.util.Arrays;

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
final class RedisListener extends JedisPubSub {

    private final Transmission transmission;

    /**
     * Creates a new Redis listener.
     * @param transmission The transmission.
     */
    RedisListener(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * When a message is received.
     * @param channel The channel.
     * @param message The message.
     */
    @Override
    public void onMessage(String channel, String message) {
        if(!channel.equals(RedisHandler.CHANNEL)) {
            return;
        }
        Packet packet;
        try {
            JSON json = new JSON(message);
            packet = json.marshal(Packet.class);
        } catch(IllegalArgumentException | IllegalStateException exception) {
            exception.printStackTrace();
            return;
        }
        if(packet.isForEveryone() || Arrays.stream(packet.getReceivers()).anyMatch(it -> it.equals(transmission.getIdentifier()))) {
            transmission.getPacketHandler().handle(packet);
        }
    }

}
