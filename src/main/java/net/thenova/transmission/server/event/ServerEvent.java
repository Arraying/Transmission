package net.thenova.transmission.server.event;

import net.thenova.transmission.packet.Packet;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

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
public abstract class ServerEvent extends Event {

    private static final HandlerList list = new HandlerList();
    private final Packet packet;

    /**
     * Creates a new server event.
     * @param packet The packet.
     */
    ServerEvent(Packet packet) {
        this.packet = packet;
    }

    /**
     * Gets the handler list.
     * @return The list.
     */
    public static HandlerList getHandlerList() {
        return list;
    }

    /**
     * Gets the handlers.
     * @return The handlers.
     */
    @Override
    public HandlerList getHandlers() {
        return list;
    }

    /**
     * Gets the packet.
     * @return The packet.
     */
    public Packet getPacket() {
        return packet;
    }

}
