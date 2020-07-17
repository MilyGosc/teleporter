package pl.milygosc.teleporter.event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class TeleporterRecipeGuiListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        // disallow players to take items from recipe schema
        if (event.getView().getTitle().equals("§Ka§5Teleporter§R§Ka§R recipe")) {
            event.setCancelled(true);
        }
    }
}
