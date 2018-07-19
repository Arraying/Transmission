package net.thenova.transmission.packet;

import de.arraying.kotys.JSON;
import net.thenova.transmission.Core;

import java.util.ArrayList;
import java.util.Collections;
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
public final class PacketBuilder {

    private final List<String> receivers = new ArrayList<>();
    private JSON payload = new JSON();

    /**
     * Adds a receiver to the packet. Specify no receiver for a packet to be sent globally.
     * @param receiver The receiver.
     * @return The builder, for chaining.
     */
    public PacketBuilder withReceiver(String receiver) {
        if(receiver != null) {
            receivers.add(receiver);
        }
        return this;
    }

    /**
     * Specifies the JSON data.
     * @param json The JSON data.
     * @return The builder, for chaining.
     */
    public PacketBuilder withJSON(JSON json) {
        payload = json;
        return this;
    }

    /**
     * Builds the packet.
     * @return The packet.
     */
    public Packet build() {
        return new Packet(receivers.toArray(new String[0]), Core.INSTANCE.getConfiguration().getId(), payload);
    }

}
