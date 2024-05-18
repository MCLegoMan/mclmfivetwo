/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.mixin.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.hud.InGameHud;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = InGameHud.class, priority = 100)
public abstract class InGameHUDMixin {
	@Inject(method = "method_979", at = @At("HEAD"), cancellable = true)
	private void mclm153$crosshair(float bl, boolean i, int j, int par4, CallbackInfo ci) {
		if (Minecraft.getMinecraft().options.perspective != 0) ci.cancel();
	}
}
