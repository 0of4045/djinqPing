package dev.djinq.djinqping;

import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.plugin.java.JavaPlugin;

public final class DjinqPing extends JavaPlugin {
    private MessageManager messages;

    @Override
    public void onEnable() {
        ResourceMigration.mergeMissingDefaults(this, "messages.yml");
        messages = new MessageManager(this);

        PluginCommand command = getCommand("ping");
        if (command == null) {
            getLogger().severe("The ping command is missing from plugin.yml.");
            getServer().getPluginManager().disablePlugin(this);
            return;
        }

        PingCommand pingCommand = new PingCommand(messages);
        command.setExecutor(pingCommand);
        command.setTabCompleter(pingCommand);
        Bukkit.getConsoleSender().sendMessage(TextRenderer.render(
                "<#B3E342>djinqPing <#CACACA>made by <#B3E342>djinq "
                        + "<#CACACA>| <#B3E342>djinq.dev <#CACACA>| <#B3E342>Loaded"
        ));
    }
}
