package net.thenova.transmission.server;

import net.thenova.transmission.Core;
import net.thenova.transmission.packet.PacketHandler;
import net.thenova.transmission.server.event.ServerReceiveEvent;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

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
public final class ServerCore extends JavaPlugin {

    /**
     * When the plugin enables.
     */
    @Override
    public void onEnable() {
        Core.INSTANCE.hello(this);
        if(!Core.INSTANCE.init()) {
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }

    /**
     * Gets the handler.
     * @return The handler.
     */
    public PacketHandler getHandler() {
        return (packet) -> Bukkit.getPluginManager().callEvent(new ServerReceiveEvent(packet));
    }

}
