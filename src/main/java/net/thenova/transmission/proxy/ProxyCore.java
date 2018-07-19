package net.thenova.transmission.proxy;

import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.event.EventHandler;
import net.thenova.transmission.Core;
import net.thenova.transmission.packet.PacketHandler;
import net.thenova.transmission.proxy.event.ProxyReceiveEvent;

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
public final class ProxyCore extends Plugin {

    /**
     * When the plugin enables.
     */
    @Override
    public void onEnable() {
        Core.INSTANCE.hello(this);
        Core.INSTANCE.init();
    }

    /**
     * Gets the handler.
     * @return The handler.
     */
    public PacketHandler getHandler() {
        return (packet) -> getProxy().getPluginManager().callEvent(new ProxyReceiveEvent(packet));
    }

}
