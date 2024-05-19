/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.mixin.client;

import com.mclegoman.mclmfivetwo.client.util.Keybindings;
import net.minecraft.client.Minecraft;
import org.lwjgl.input.Keyboard;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Minecraft.class)
public class MinecraftMixin {
	@Inject(method = "tick", at = @At("TAIL"))
	private void mclm25$tick(CallbackInfo ci) {
		if (Keyboard.isCreated()) {
			if (Keybindings.sprint.pressed && !Minecraft.getMinecraft().playerEntity.isSprinting()) {
				Minecraft.getMinecraft().playerEntity.setSprinting(true);
			}
		}
	}
}
