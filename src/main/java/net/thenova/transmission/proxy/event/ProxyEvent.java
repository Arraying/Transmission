package net.thenova.transmission.proxy.event;

import net.md_5.bungee.api.plugin.Event;
import net.thenova.transmission.packet.Packet;

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
public abstract class ProxyEvent extends Event {

    private final Packet packet;

    /**
     * Creates a new proxy event.
     * @param packet The packet.
     */
    ProxyEvent(Packet packet) {
        this.packet = packet;
    }

    /**
     * Gets the packet.
     * @return The packet.
     */
    public Packet getPacket() {
        return packet;
    }

}
