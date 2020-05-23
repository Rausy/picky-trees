package com.raus.pickyTrees;

import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.StructureGrowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class TreeListener implements Listener
{
	private Main plugin = JavaPlugin.getPlugin(Main.class);

	@EventHandler
	public void onGrow(StructureGrowEvent event)
	{
		Biome biome = event.getLocation().getBlock().getBiome();
		boolean wrongBiome = false;

		switch (event.getSpecies())
		{
		case ACACIA:
			wrongBiome = biome != Biome.SAVANNA &&
			biome != Biome.SAVANNA_PLATEAU &&
			biome != Biome.SHATTERED_SAVANNA &&
			biome != Biome.SHATTERED_SAVANNA_PLATEAU;
			break;

		case SMALL_JUNGLE:
		case COCOA_TREE:
		case JUNGLE:
		case JUNGLE_BUSH:
			wrongBiome = biome != Biome.BAMBOO_JUNGLE &&
			biome != Biome.BAMBOO_JUNGLE_HILLS &&
			biome != Biome.JUNGLE &&
			biome != Biome.JUNGLE_EDGE &&
			biome != Biome.JUNGLE_HILLS &&
			biome != Biome.MODIFIED_JUNGLE &&
			biome != Biome.MODIFIED_JUNGLE_EDGE;
			break;

		case REDWOOD:
		case TALL_REDWOOD:
		case MEGA_REDWOOD:
			wrongBiome = biome != Biome.GIANT_SPRUCE_TAIGA &&
			biome != Biome.GIANT_SPRUCE_TAIGA_HILLS &&
			biome != Biome.GIANT_TREE_TAIGA &&
			biome != Biome.GIANT_TREE_TAIGA_HILLS &&
			biome != Biome.SNOWY_TAIGA &&
			biome != Biome.SNOWY_TAIGA_HILLS &&
			biome != Biome.SNOWY_TAIGA_MOUNTAINS &&
			biome != Biome.TAIGA &&
			biome != Biome.TAIGA_HILLS &&
			biome != Biome.TAIGA_MOUNTAINS &&
			biome != Biome.MOUNTAINS &&
			biome != Biome.WOODED_MOUNTAINS &&
			biome != Biome.GRAVELLY_MOUNTAINS &&
			biome != Biome.MODIFIED_GRAVELLY_MOUNTAINS &&
			biome != Biome.SNOWY_TUNDRA;
			break;

		case DARK_OAK:
			wrongBiome = biome != Biome.DARK_FOREST &&
			biome != Biome.DARK_FOREST_HILLS;
			break;

		case BIRCH:
		case TALL_BIRCH:
			wrongBiome = biome != Biome.BIRCH_FOREST &&
			biome != Biome.BIRCH_FOREST_HILLS &&
			biome != Biome.TALL_BIRCH_FOREST &&
			biome != Biome.TALL_BIRCH_HILLS &&
			biome != Biome.PLAINS &&
			biome != Biome.SUNFLOWER_PLAINS &&
			biome != Biome.FOREST &&
			biome != Biome.FLOWER_FOREST;
			break;

		case SWAMP:
			wrongBiome = biome != Biome.SWAMP &&
			biome != Biome.SWAMP_HILLS;
			break;

		case TREE:
		case BIG_TREE:
			wrongBiome = biome != Biome.PLAINS &&
			biome != Biome.SUNFLOWER_PLAINS &&
			biome != Biome.FOREST &&
			biome != Biome.FLOWER_FOREST &&
			biome != Biome.SNOWY_TUNDRA &&
			biome != Biome.MOUNTAINS &&
			biome != Biome.WOODED_MOUNTAINS &&
			biome != Biome.GRAVELLY_MOUNTAINS &&
			biome != Biome.MODIFIED_GRAVELLY_MOUNTAINS &&
			biome != Biome.SWAMP &&
			biome != Biome.SWAMP_HILLS &&
			biome != Biome.BAMBOO_JUNGLE &&
			biome != Biome.BAMBOO_JUNGLE_HILLS &&
			biome != Biome.JUNGLE &&
			biome != Biome.JUNGLE_EDGE &&
			biome != Biome.JUNGLE_HILLS &&
			biome != Biome.MODIFIED_JUNGLE &&
			biome != Biome.MODIFIED_JUNGLE_EDGE &&
			biome != Biome.RIVER &&
			biome != Biome.SAVANNA &&
			biome != Biome.SAVANNA_PLATEAU &&
			biome != Biome.SHATTERED_SAVANNA &&
			biome != Biome.SHATTERED_SAVANNA_PLATEAU &&
			biome != Biome.WOODED_BADLANDS_PLATEAU &&
			biome != Biome.MODIFIED_WOODED_BADLANDS_PLATEAU;
			break;

		default:
			// Do nothing
			break;
		}

		// Cancel growth event
		if (wrongBiome)
		{
			boolean cancel = false;

			if (event.isFromBonemeal())
			{
				cancel = Math.random() >= plugin.getBoneMealChance();

				// Take bone meal from player
				if (cancel)
				{
					PlayerInventory inv = event.getPlayer().getInventory();
					ItemStack main = inv.getItemInMainHand();
					ItemStack off = inv.getItemInOffHand();
					if (main.getType() == Material.BONE_MEAL)
					{
						main.setAmount(main.getAmount() - 1);
						inv.setItemInMainHand(main);
					}
					else if (off.getType() == Material.BONE_MEAL)
					{
						off.setAmount(off.getAmount() - 1);
						inv.setItemInOffHand(off);
					}
				}
			}
			else
			{
				cancel = Math.random() >= plugin.getNaturalGrowthChance();
			}

			if (cancel)
			{
				System.out.println("cancelled");
				event.setCancelled(true);
			}
		}
	}
}