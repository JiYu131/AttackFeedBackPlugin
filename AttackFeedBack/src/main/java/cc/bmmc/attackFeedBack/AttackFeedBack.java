package cc.bmmc.attackFeedBack;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AttackFeedBack extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getLogger().info("监听器已注册");
        Bukkit.getLogger().info("[AttackFeedBack]插件成功加载！");
        Bukkit.getLogger().info("[AttackFeedBack]The plugin has been loaded successfully!");
    }
}
