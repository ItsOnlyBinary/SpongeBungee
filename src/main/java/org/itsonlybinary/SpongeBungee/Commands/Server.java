package org.itsonlybinary.SpongeBungee.Commands;

import org.itsonlybinary.SpongeBungee.SpongeBungee;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.text.Texts;
import org.spongepowered.api.util.command.CommandException;
import org.spongepowered.api.util.command.CommandResult;
import org.spongepowered.api.util.command.CommandSource;
import org.spongepowered.api.util.command.args.CommandContext;
import org.spongepowered.api.util.command.spec.CommandExecutor;

import java.util.Optional;

public class Server implements CommandExecutor {
    SpongeBungee plugin;

    public Server(SpongeBungee plugin) {
        this.plugin = plugin;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if (src instanceof Player) {
            Player player = (Player) src;
            Optional<String> destinationServer = args.getOne("server");
            if (destinationServer.isPresent()) {
                String destinationString = destinationServer.get();

                player.sendMessage(Texts.of("Connecting to " + destinationString));
                plugin.getChannel().sendTo(player, buf -> {
                    buf.writeString("Connect");
                    buf.writeString(destinationString);
                });
            } else {
                // TODO show current server and possible servers
                player.sendMessage(Texts.of("No Server!"));
            }


        }

        return null;
    }
}
