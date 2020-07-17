package pl.milygosc.teleporter;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class Items {

    private static ItemStack teleporter;

    public static ItemStack getTeleporter() {
        if (teleporter == null) {
            teleporter = new ItemStack(Material.NETHER_STAR);
            ItemMeta meta = teleporter.getItemMeta();
            meta.setDisplayName(ChatColor.DARK_PURPLE + "Teleporter");
            meta.setLore(Arrays.asList("Click right", "while in hand"));
            teleporter.setItemMeta(meta);
            teleporter.getMaxStackSize();
        }

        return teleporter;
    }
}
