/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.client;

import com.mclegoman.mclmfivetwo.client.util.Keybindings;
import net.fabricmc.api.ClientModInitializer;

public class FiveTwoClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		Keybindings.init();
	}
}
