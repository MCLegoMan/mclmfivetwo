/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common.registries;

import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;

public class Items {
	public static final Item greenApple;
	protected static void init() {
	}
	static {
		greenApple = new FoodItem(1000, 4, 0.3F, false).setName("mclmfivetwo_green_apple");
	}
}
