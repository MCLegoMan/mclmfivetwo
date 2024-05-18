/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common;

import com.mclegoman.mclmfivetwo.common.registries.Registries;
import net.fabricmc.api.ModInitializer;

public class FiveTwo implements ModInitializer {
	@Override
	public void onInitialize() {
		Registries.init();

	}
}
