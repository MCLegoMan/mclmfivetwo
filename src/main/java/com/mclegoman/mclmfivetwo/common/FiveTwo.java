/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;

public class FiveTwo implements ModInitializer {
	public static final Item greenApple;
	@Override
	public void onInitialize() {
	}
	static {
		greenApple = new FoodItem(1000, 4, 0.3F, false).setName("mclmfivetwo_green_apple");
	}
}
