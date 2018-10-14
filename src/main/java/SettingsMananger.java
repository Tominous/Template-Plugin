import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;


import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

public class SettingsManager {

    private SettingsManager() { }

    static SettingsManager instance = new SettingsManager();

    public static SettingsManager getInstance() {
        return instance;
    }

    FileConfiguration config;
    File cFile;

    FileConfiguration Lang;
    File lFile;

    public void setup(Plugin p) {

        if (!p.getDataFolder().exists()) {
            p.getDataFolder().mkdir();
        }

        cFile = new File(p.getDataFolder(), "config.yml");

        if (!cFile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "config.yml");
                InputStream E = getClass().getResourceAsStream("/config.yml");
                copyFile(E, en);
            }catch (Exception e) {
                e.printStackTrace();
                Bukkit.getLogger().info("Could Not Create Config.yml!");
            }
        }

        config = YamlConfiguration.loadConfiguration(cFile);



        lFile = new File(p.getDataFolder(), "lang.yml");

        if (!lFile.exists()) {
            try {
                File en = new File(p.getDataFolder(), "lang.yml");
                InputStream E = getClass().getResourceAsStream("/lang.yml");
                copyFile(E, en);
            }catch (Exception e) {
                e.printStackTrace();
                Bukkit.getLogger().info("Could Not Create Lang.yml!");
            }
        }

        Lang = YamlConfiguration.loadConfiguration(lFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public FileConfiguration getLang() {
        return Lang;
    }

    public void saveConfig() {
        try {
            config.save(cFile);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void saveLang() {
        try {
            Lang.save(lFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(cFile);
    }

    public void reloadLand() {
        Lang = YamlConfiguration.loadConfiguration(lFile);
    }


    public static void copyFile(InputStream in, File out) throws Exception { // https://bukkit.org/threads/extracting-file-from-jar.16962/
        InputStream fis = in;
        FileOutputStream fos = new FileOutputStream(out);
        try {
            byte[] buf = new byte[1024];
            int i = 0;
            while((i = fis.read(buf)) != -1) {
                fos.write(buf, 0, i);
            }
        }catch(Exception e) {
            throw e;
        }finally {
            if(fis != null) {
                fis.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
    }

}
