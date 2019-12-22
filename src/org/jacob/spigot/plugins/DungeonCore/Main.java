package org.jacob.spigot.plugins.DungeonCore;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.jacob.spigot.plugins.DungeonCore.modules.MuteGUICommand;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    public static Main instance;

    public static Main getInstance() {return instance;}

    private File playerDataFile;
    private FileConfiguration playerData;

    @Override
    public void onEnable() {

        instance = this;

        createPlayerData();

        getConfig().options().copyDefaults(true);
        saveConfig();

        registerEvents();
        registerCommands();

    }

    private void createPlayerData() {
        playerDataFile = new File(getDataFolder(), "data.yml");
        if (!playerDataFile.exists()) {
            playerDataFile.getParentFile().mkdirs();
            saveResource("data.yml", false);
        }

        playerData = new YamlConfiguration();
        try {
            playerData.load(playerDataFile);
        } catch (IOException | InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    public FileConfiguration getPlayerData() {
        return this.playerData;
    }

    public void savePlayerData() {
        try {
            playerData.save(playerDataFile);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    private void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();

        pm.registerEvents(new MuteGUICommand(), this);

    }

    private void registerCommands() {

        getCommand("mutegui").setExecutor(new MuteGUICommand());

    }

}
