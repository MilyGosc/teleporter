package pl.milygosc.teleporter;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import pl.milygosc.teleporter.command.TeleporterCraftCommand;
import pl.milygosc.teleporter.event.TeleporterListener;
import pl.milygosc.teleporter.event.TeleporterRecipeGuiListener;

public final class Teleporter extends JavaPlugin {

    @Override
    public void onEnable() {
        // register recipes
        Bukkit.addRecipe(teleporterRecipe());

        // register event listeners
        Bukkit.getPluginManager().registerEvents(new TeleporterListener(), this);
        Bukkit.getPluginManager().registerEvents(new TeleporterRecipeGuiListener(), this);

        // register commands
        this.getCommand("teleporter").setExecutor(new TeleporterCraftCommand());
    }

    public ShapedRecipe teleporterRecipe() {
        // Reserve item name in game
        NamespacedKey key = new NamespacedKey(this, "teleporter");

        // Add recipe to game
        ShapedRecipe recipe = new ShapedRecipe(key, Items.getTeleporter());
        recipe.shape("ODO", "DED", "ODO");
        recipe.setIngredient('O', Material.CRYING_OBSIDIAN);
        recipe.setIngredient('D', Material.DIAMOND);
        recipe.setIngredient('E', Material.ENDER_PEARL);

        return recipe;
    }
}
