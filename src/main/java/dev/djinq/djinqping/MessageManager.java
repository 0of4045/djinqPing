package dev.djinq.djinqping;

import java.util.HashMap;
import java.util.Map;
import net.kyori.adventure.text.Component;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public final class MessageManager {
    private final JavaPlugin plugin;
    private FileConfiguration messages;
    private String prefix;

    public MessageManager(JavaPlugin plugin) {
        this.plugin = plugin;
        reload();
    }

    public void reload() {
        messages = org.bukkit.configuration.file.YamlConfiguration.loadConfiguration(
                new java.io.File(plugin.getDataFolder(), "messages.yml")
        );
        prefix = messages.getString("prefix", "");
    }

    public Component message(String key, String... replacements) {
        String text = prefix + messages.getString("messages." + key, "");
        Map<String, String> values = new HashMap<>();

        for (int index = 0; index + 1 < replacements.length; index += 2) {
            values.put(replacements[index], replacements[index + 1]);
        }

        return TextRenderer.render(text, values);
    }
}
