package net.thenova.transmission.packet;

import de.arraying.kotys.JSON;
import de.arraying.kotys.JSONField;

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
public final class Packet {

    @JSONField(key = "r") private String[] receivers;
    @JSONField(key = "s") private String sender;
    @JSONField(key = "d") private JSON payload;

    /**
     * Creates a new packet via JSON ORM.
     */
    Packet() {}

    /**
     * Creates a new packet.
     * @param receivers The receiver array.
     * @param sender The sender.
     * @param payload The payload.
     */
    Packet(String[] receivers, String sender, JSON payload) {
        this.receivers = receivers;
        this.sender = sender;
        this.payload = payload;
    }

    /**
     * Whether this packet is for every packet listener.
     * @return True if everyone received this, false otherwise.
     */
    public boolean isForEveryone() {
        return receivers.length == 0;
    }

    /**
     * Gets the receivers.
     * @return The receivers.
     */
    public String[] getReceivers() {
        return receivers;
    }

    /**
     * Gets the payload.
     * @return The payload (may be null).
     */
    public JSON getPayload() {
        return payload;
    }

    /**
     * Converts the packet to JSON.
     * @return The JSON packet.
     */
    public JSON toJSON() {
        return new JSON()
                .put("r", receivers)
                .put("s", sender)
                .put("d", payload);
    }

}
