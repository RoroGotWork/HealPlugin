package fr.rorocraft.heal.commands;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import fr.rorocraft.heal.Heal;

public class HealCommand  implements CommandExecutor{
	private Heal main;
	Map<UUID, Long> cooldown = new HashMap<>();

	public HealCommand(Heal heal) {
		this.main = heal;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(sender instanceof Player ) {
			Player p = (Player) sender;
			
		long cooldownTime = main.getConfig().getLong("cooldown_time");
		int healLevel = main.getConfig().getInt("heal_level");
		String message = main.getConfig().getString("message");
		
		if(cooldownTime != 0 && healLevel != 0 && message != null ) {	
			
		if(cooldown.containsKey(p.getUniqueId())){
			
		long secondLeft = (cooldown.get(p.getUniqueId()) / 1000  + cooldownTime) - (System.currentTimeMillis() / 1000);	
			
		if(secondLeft > 0) {
			p.sendMessage("Vous devez attendre " + secondLeft + " secondes avant d'être heal");
			
			
			
		} else {
			cooldown.remove(p.getUniqueId());
			cooldown.put(p.getUniqueId(), System.currentTimeMillis() );	
			
			p.setHealth(healLevel);
			p.sendMessage(message);
		}
		
		} else {
			
			p.setHealth(healLevel);
			p.sendMessage(message);
			
		cooldown.put(p.getUniqueId(), System.currentTimeMillis() );	
		}
		
			
		}
            else {
			p.sendMessage("Tu ne peux pas être heal");
			System.out.println("Erreur de configuration dans le plugin heal");
		}
		}
		
		
		return false;
	}

}
