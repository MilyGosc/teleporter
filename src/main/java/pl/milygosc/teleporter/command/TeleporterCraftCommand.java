package pl.milygosc.teleporter.command;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class TeleporterCraftCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            Inventory gui = Bukkit.createInventory(null, 27, "§Ka§5Teleporter§R§Ka§R recipe");

            ItemStack obsidian = new ItemStack(Material.CRYING_OBSIDIAN);
            ItemStack diamond = new ItemStack(Material.DIAMOND);
            ItemStack pearl = new ItemStack(Material.ENDER_PEARL);

            gui.setItem(13, pearl);

            gui.setItem(3, obsidian);
            gui.setItem(5, obsidian);
            gui.setItem(21, obsidian);
            gui.setItem(23, obsidian);

            gui.setItem(4, diamond);
            gui.setItem(12, diamond);
            gui.setItem(14, diamond);
            gui.setItem(22, diamond);

            player.openInventory(gui);

            return true;
        }

        sender.sendMessage("You must be a player to use this command");
        return false;
    }
}
