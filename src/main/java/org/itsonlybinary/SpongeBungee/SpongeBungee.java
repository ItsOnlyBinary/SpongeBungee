package org.itsonlybinary.SpongeBungee;

import com.google.inject.Inject;
import org.itsonlybinary.SpongeBungee.Commands.Server;
import org.slf4j.Logger;
import org.spongepowered.api.Game;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameAboutToStartServerEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.network.ChannelBinding;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.args.GenericArguments;
import org.spongepowered.api.util.command.spec.CommandSpec;

@Plugin(id = "com.itsonlybinary.SpongeBungee", name = "SpongeBungee", version = "1.0")
public class SpongeBungee {

    @Inject
    private Logger logger;
    @Inject
    private Game game;

    private ChannelBinding.RawDataChannel channel;

    @Listener
    public void onPreInit(GamePreInitializationEvent event) {
        channel = game.getChannelRegistrar().createRawChannel(this, "BungeeCord");
    }

    @Listener
    public void onPreStart(GameAboutToStartServerEvent event) {
        CommandSpec serverCommandSpec = CommandSpec.builder()
                .description(Texts.of("Change Server Command"))
                .permission("spongebungee.command.server")
                .arguments(
                        GenericArguments.optional(GenericArguments.string(Texts.of("server")))
                )
                .executor(new Server(this))
                .build();

        game.getCommandDispatcher().register(this, serverCommandSpec, "server");
    }

    public ChannelBinding.RawDataChannel getChannel() {
        return channel;
    }
}
