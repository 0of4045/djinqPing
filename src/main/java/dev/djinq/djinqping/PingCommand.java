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
import java.util.Map;

public final class PingCommand implements CommandExecutor, TabCompleter {
    private final DjinqPing plugin;
    private final MessageManager messages;

    public PingCommand(DjinqPing plugin, MessageManager messages) {
        this.plugin = plugin;
        this.messages = messages;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 1) {
            messages.send(sender, "usage");
            return true;
        }

        if (args.length == 1 && args[0].equalsIgnoreCase("reload")) {
            if (!sender.hasPermission("djinqping.reload")) {
                messages.send(sender, "no-permission");
                return true;
            }

            messages.send(sender, plugin.reloadPlugin() ? "reload-success" : "reload-failed");
            return true;
        }

        if (!sender.hasPermission("djinqping.use")) {
            messages.send(sender, "no-permission");
            return true;
        }

        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                messages.send(sender, "player-only");
                return true;
            }

            messages.send(sender, "your-ping", Map.of("ping", String.valueOf(player.getPing())));
            return true;
        }

        if (!sender.hasPermission("djinqping.others")) {
            messages.send(sender, "no-permission");
            return true;
        }

        Player target = Bukkit.getPlayerExact(args[0]);
        if (target == null) {
            messages.send(sender, "player-not-found");
            return true;
        }

        messages.send(sender, "player-ping", Map.of(
                "player", target.getName(),
                "ping", String.valueOf(target.getPing())));
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (args.length != 1 || (!sender.hasPermission("djinqping.use") && !sender.hasPermission("djinqping.reload"))) {
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
