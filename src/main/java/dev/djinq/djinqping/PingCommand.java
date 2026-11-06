package dev.djinq.djinqping;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class PingCommand implements CommandExecutor, TabCompleter {
    private final MessageManager messages;

    public PingCommand(MessageManager messages) {
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.hasPermission("djinqping.use")) {
            sender.sendMessage(messages.message("no-permission"));
            return true;
        }

        if (args.length > 1) {
            sender.sendMessage(messages.message("usage"));
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("djinqping.reload")) {
                sender.sendMessage(messages.message("no-permission"));
                return true;
            }

            messages.reload();
            sender.sendMessage(messages.message("reloaded"));
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                sender.sendMessage(messages.message("player-only"));
                return true;
            }

            sender.sendMessage(messages.message("your-ping", "ping", String.valueOf(player.getPing())));
            return true;
        }

        if (!sender.hasPermission("djinqping.others")) {
            sender.sendMessage(messages.message("no-permission"));
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            sender.sendMessage(messages.message("player-not-found"));
            return true;
        }

        sender.sendMessage(messages.message(
                "ping",
                "player", target.getName(),
                "ping", String.valueOf(target.getPing())
        ));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || !sender.hasPermission("djinqping.use")) {
            return List.of();
        }

        String input = args[0].toLowerCase(Locale.ROOT);
        List<String> completions = new ArrayList<>();
        if (sender.hasPermission("djinqping.others")) {
            Bukkit.getOnlinePlayers().stream()
                    .map(Player::getName)
                    .filter(name -> name.toLowerCase(Locale.ROOT).startsWith(input))
                    .sorted(String.CASE_INSENSITIVE_ORDER)
                    .forEach(completions::add);
        }

        if (sender.hasPermission("djinqping.reload") && "reload".startsWith(input)) {
            completions.add("reload");
        }

        return completions;
    }
}
