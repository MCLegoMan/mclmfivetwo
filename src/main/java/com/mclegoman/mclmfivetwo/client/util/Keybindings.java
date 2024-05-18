/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.client.util;

import net.minecraft.client.option.KeyBinding;
import org.lwjgl.input.Keyboard;

public class Keybindings {
	public static KeyBinding sprint;
	public static void init() {
	}
	static {
		 sprint = new KeyBinding("key.sprint", Keyboard.KEY_LCONTROL);
	}
}
