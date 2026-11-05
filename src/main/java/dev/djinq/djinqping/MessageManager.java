package dev.djinq.djinqping;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageManager {
    private static final MiniMessage MINI_MESSAGE = MiniMessage.miniMessage();
    private final JavaPlugin plugin;
    private FileConfiguration messages;
    private Component prefix;

    public MessageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        messages = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(
                new java.io.File(plugin.getDataFolder(), "messages.yml")
        );
        prefix = render(messages.getString("prefix", ""));
    }

    public Component message(String key, String... replacements) {
        String text = messages.getString("messages." + key, "");

        for (int index = 0; index + 1 < replacements.length; index += 2) {
            text = text.replace("%" + replacements[index] + "%", replacements[index + 1]);
        }

        return prefix.append(render(text));
    }

    private Component render(String text) {
        if (text.contains("<")) {
            return MINI_MESSAGE.deserialize(text);
        }

        return LegacyComponentSerializer.legacyAmpersand().deserialize(text);
    }
}
