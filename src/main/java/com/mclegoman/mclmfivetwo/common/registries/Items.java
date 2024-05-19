/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common.registries;

import com.mclegoman.mclmfivetwo.common.registries.items.MusicDiscItem;
import net.minecraft.item.FoodItem;
import net.minecraft.item.Item;

public class Items {
	public static final Item greenApple;
	public static final Item recordTest;
	protected static void init() {
	}
	static {
		greenApple = new FoodItem(1000, 4, 0.3F, false).setName("mclmfivetwo_greenApple");
		recordTest = new MusicDiscItem(1001, "MCLegoMan", "Test", "mclmfivetwo_test").setName("record");
	}
}
