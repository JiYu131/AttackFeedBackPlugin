package cc.bmmc.attackFeedBack;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class Listeners implements Listener {

    String heart = "❤";
    String sword = "🗡";
    String axe = "🪓";
    String air = "✊";
    String bow = "🏹";
    String unknown = "";
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        double damage = e.getDamage();
        String name;
        String cause;
        if (e.getEntityType().equals(EntityType.PLAYER)){
            Player p = (Player) e.getEntity();



            if (e.getDamager().getType().equals(EntityType.PLAYER)){
                Player damager = (Player) e.getDamager();
                name = "[玩家]" + damager.getName();
                Material weaponType = damager.getInventory().getItemInMainHand().getType();
                if (isSword(weaponType)){
                    cause = sword;
                } else if (isAxe(weaponType)){
                    cause = axe;
                } else if (weaponType.isAir()){
                    cause = air;
                } else {
                    cause = unknown;
                }
                sendActionBar((Player) e.getDamager(), cause + " " + ChatColor.RED + p.getName() + " -" + damage + " " + heart);
            } else {
                if (e.getDamager().getType().equals(EntityType.SPECTRAL_ARROW) || e.getDamager().getType().equals(EntityType.SPECTRAL_ARROW)) {
                    Arrow arrow = (Arrow) e.getDamager();
                    cause = bow;
                    if (arrow.getShooter() instanceof Player){
                        name = ((Player) arrow.getShooter()).getName();
                        sendActionBar((Player) arrow.getShooter(), cause + ChatColor.RED + " " + p.getName() + " -" + damage + " " + heart);
                    } else {
                        name = e.getDamager().getName();
                    }
                } else {
                    cause = unknown;
                }
                name = e.getEntity().getName();
            }
            sendActionBar(p, name + cause + ChatColor.DARK_RED + " &4 -" + damage + heart);
        } else {
            if (e.getDamager().getType().equals(EntityType.PLAYER)){
                Player damager = (Player) e.getDamager();
                Material weaponType = damager.getInventory().getItemInMainHand().getType();
                if (isSword(weaponType)){
                    cause = sword;
                } else if (isAxe(weaponType)){
                    cause = axe;
                } else if (weaponType.isAir()){
                    cause = air;
                } else {
                    cause = unknown;
                }
                sendActionBar((Player)e.getDamager(), cause + " " + ChatColor.RED + e.getEntity().getName() + " -" + damage + " " + heart);
            }
        }
    }

    public void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR,
                new TextComponent(ChatColor.translateAlternateColorCodes('&', message)));
    }

    public boolean isSword(Material material) {
        return material == Material.WOODEN_SWORD ||
                material == Material.STONE_SWORD ||
                material == Material.IRON_SWORD ||
                material == Material.GOLDEN_SWORD ||
                material == Material.DIAMOND_SWORD ||
                material == Material.NETHERITE_SWORD;
    }

    public boolean isAxe(Material material) {
        return material == Material.WOODEN_AXE ||
                material == Material.STONE_AXE ||
                material == Material.IRON_AXE ||
                material == Material.GOLDEN_AXE ||
                material == Material.DIAMOND_AXE ||
                material == Material.NETHERITE_AXE;
    }
}
