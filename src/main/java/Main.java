import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    Plugin p = this;
    SettingsManager s = SettingsManager.getInstance();
    FileConfiguration config, lang;

    @Override
    public void onEnable() {
        s.setup(p);
        config = s.getConfig();
        lang = s.getLang();
        Bukkit.getLogger().info(" Savag3Core Loaded! ");

    }

    @Override
    public void onDisable() {

    }





}
