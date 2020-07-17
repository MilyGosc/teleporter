package pl.milygosc.teleporter.event;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import pl.milygosc.teleporter.Items;

import java.util.ArrayList;
import java.util.List;

public class TeleporterListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPlayedBefore()) {
            player.getInventory().addItem(Items.getTeleporter());
            player.sendMessage("Hello Stranger! For the good start you received §Ka§5Teleporter§R§Ka§R, use it wisely!");
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        Action action = event.getAction();
        ItemStack item = event.getItem();

        String teleporterName = Items.getTeleporter().getItemMeta().getDisplayName();

        if ((action.equals(Action.RIGHT_CLICK_AIR) || action.equals(Action.RIGHT_CLICK_BLOCK))
                && item != null && teleporterName.equals(item.getItemMeta().getDisplayName())) {

            List<Player> onlinePlayers = new ArrayList<>(Bukkit.getOnlinePlayers());
            Inventory teleportGui = Bukkit.createInventory(player, 27, ChatColor.DARK_PURPLE + "Teleporter");

            for (Player onlinePlayer : onlinePlayers) {
                if (onlinePlayer.getUniqueId().equals(player.getUniqueId())) continue;
                ItemStack playerHead = new ItemStack(Material.PLAYER_HEAD, 1);
                SkullMeta meta = (SkullMeta) playerHead.getItemMeta();
                meta.setOwningPlayer(onlinePlayer);
                meta.setDisplayName(onlinePlayer.getDisplayName());
                playerHead.setItemMeta(meta);
                teleportGui.addItem(playerHead);
            }

            player.openInventory(teleportGui);
        }
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        Player invoker = (Player) event.getWhoClicked();

        if (event.getView().getTitle().equalsIgnoreCase(ChatColor.DARK_PURPLE + "Teleporter")) {
            if (event.getCurrentItem() != null && event.getCurrentItem().getType().equals(Material.PLAYER_HEAD)) {
                String targetPlayerName = event.getCurrentItem().getItemMeta().getDisplayName();
                Player targetPlayer = Bukkit.getPlayer(targetPlayerName);

                if (targetPlayer != null && targetPlayer.isOnline()) {
                    if (!invoker.getInventory().containsAtLeast(Items.getTeleporter(), 1)) {
                        invoker.sendMessage("To teleport you need at least one §Ka§5Teleporter§R§Ka§R in your inventory.");
                        return;
                    }

                    event.setCancelled(true);
                    invoker.getInventory().removeItem(Items.getTeleporter());
                    invoker.teleport(targetPlayer.getLocation());
                    invoker.sendMessage("§Ka§5Teleporter§R§Ka§R made his job!");
                } else {
                    invoker.sendMessage("Target player went offline");
                }
            }
        }
    }
}
