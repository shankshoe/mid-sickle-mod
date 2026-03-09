package com.sm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;

public class Sicklemod implements ModInitializer {
	public static final String MOD_ID = "sicklemod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);


	@Override
	public void onInitialize() {
		ModItem.registerModItems();
		LOGGER.info("Hello Fabric world!");
	}
}