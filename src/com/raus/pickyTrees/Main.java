package com.raus.pickyTrees;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
	private double naturalGrowthChance;
	private double boneMealChance;

	@Override
	public void onEnable()
	{
		// Save config
		saveDefaultConfig();

		// Listeners
		getServer().getPluginManager().registerEvents(new TreeListener(), this);

		// Register command
		this.getCommand("picky").setExecutor(new ReloadCommand());

		reload();
	}

	@Override
	public void onDisable()
	{

	}

	public double getNaturalGrowthChance()
	{
		return naturalGrowthChance;
	}

	public double getBoneMealChance()
	{
		return boneMealChance;
	}

	public void reload()
	{
		// Reload
		reloadConfig();

		naturalGrowthChance = getConfig().getDouble("naturalGrowthChance");
		boneMealChance = getConfig().getDouble("boneMealChance");
	}
}