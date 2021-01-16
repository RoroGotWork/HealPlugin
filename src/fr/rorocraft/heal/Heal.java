package fr.rorocraft.heal;

import org.bukkit.plugin.java.JavaPlugin;

import fr.rorocraft.heal.commands.HealCommand;

public class Heal extends JavaPlugin {

	@Override
	public void onEnable() {
		System.out.println("[HealPlugin] Le plugin est pret");
		
		this.saveDefaultConfig();
		
		this.getCommand("heal").setExecutor(new HealCommand(this));
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		System.out.println("[HealPlugin] Le plugin s'est eteint");
		super.onDisable();
	}

	
	
}
