package net.thenova.transmission;

import de.arraying.kotys.JSON;
import net.thenova.transmission.redis.RedisHandler;

import java.util.ArrayList;
import java.util.List;

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
@SuppressWarnings("unused")
public final class PacketCreator {

    private final Transmission transmission;
    private final List<String> receivers = new ArrayList<>();
    private JSON payload = new JSON();

    /**
     * Creates a new packet creator.
     * @param transmission Transmission.
     */
    PacketCreator(Transmission transmission) {
        this.transmission = transmission;
    }

    /**
     * Adds receivers to the packet. Specify no receiver for a packet to be sent globally.
     * @param receivers A variadic argument of receivers.
     * @return The creator, for chaining.
     */
    public PacketCreator withReceivers(String... receivers) {
        if(receivers == null) {
            throw new IllegalArgumentException("receivers is null");
        }
        for(String receiver : receivers) {
            if(receiver == null) {
                throw new IllegalArgumentException("receiver is null");
            }
            this.receivers.add(receiver);
        }
        return this;
    }

    /**
     * Specifies the JSON data.
     * @param json The JSON data.
     * @return The creator, for chaining.
     */
    public PacketCreator withJSON(JSON json) {
        if(json == null) {
            throw new IllegalArgumentException("json is null");
        }
        payload = json;
        return this;
    }

    /**
     * Sends the packet.
     */
    public void send() {
        Packet packet = new Packet(receivers.toArray(new String[0]), transmission.getIdentifier(), payload);
        RedisHandler handler = transmission.getRedisHandler();
        if(handler != null) {
            handler.publish(packet.toJSON().marshal());
        }
    }

}
