/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.mixin.client;

import com.mclegoman.mclmfivetwo.common.data.Data;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.client.gui.widget.LanguageButton;
import net.minecraft.client.render.Tessellator;
import net.minecraft.util.Language;
import net.minecraft.util.math.MathHelper;
import org.lwjgl.opengl.GL11;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.image.BufferedImage;
import java.util.Calendar;
import java.util.Date;

@Mixin(value = TitleScreen.class, priority = 100)
public abstract class TitleScreenMixin extends Screen {
	@Shadow private int field_2278;

	@Shadow private String splashText;

	@Shadow protected abstract void method_1726(int i, int j, Language language);

	@Shadow protected abstract void method_1724(int i, int j, Language language);

	@Shadow protected abstract void method_5255(Language language, int i, int j);

	@Shadow private String oldGl1;

	@Shadow @Final public static String MORE_INFO_MESSAGE;

	@Shadow private int oldGl2Width;

	@Shadow private int oldGl1Width;

	@Shadow private int oldGlLeft;

	@Shadow private int oldGlTop;

	@Shadow private int oldGlRight;

	@Shadow private int oldGlBottom;

	@Shadow @Final private Object mutex;

	@Shadow protected abstract void renderBackground(int mouseX, int mouseY, float tickDelta);

	@Shadow private float minecraftRandomNumber;

	@Inject(method = "init", at = @At("HEAD"), cancellable = true)
	private void mclm153$init(CallbackInfo ci) {
		this.field_2278 = this.field_1229.textureManager.method_1417(new BufferedImage(256, 256, 2));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		if (calendar.get(Calendar.MONTH) + 1 == 5 && calendar.get(Calendar.DATE) == 17) {
			this.splashText = "Happy Birthday Minecraft!";
		}

		Language language = Language.getInstance();
		int buttonY = (this.height / 2) - 12;
		if (this.field_1229.isDemo()) {
			this.method_1726(buttonY, 24, language);
		} else {
			this.method_1724(buttonY, 24, language);
		}

		this.method_5255(language, buttonY, 24);
		if (this.field_1229.isApplet) {
			this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, buttonY + 60, language.translate("menu.options")));
		} else {
			this.buttons.add(new ButtonWidget(0, this.width / 2 - 100, buttonY + 60, 98, 20, language.translate("menu.options")));
			this.buttons.add(new ButtonWidget(4, this.width / 2 + 2, buttonY + 60, 98, 20, language.translate("menu.quit")));
		}

		this.buttons.add(new LanguageButton(5, this.width / 2 - 124, buttonY + 60));
		synchronized(this.mutex) {
			this.oldGl1Width = this.textRenderer.getStringWidth(this.oldGl1);
			this.oldGl2Width = this.textRenderer.getStringWidth(MORE_INFO_MESSAGE);
			int var6 = Math.max(this.oldGl1Width, this.oldGl2Width);
			this.oldGlLeft = (this.width - var6) / 2;
			this.oldGlTop = ((ButtonWidget)this.buttons.get(0)).y - 24;
			this.oldGlRight = this.oldGlLeft + var6;
			this.oldGlBottom = this.oldGlTop + 24;
		}
		ci.cancel();
	}

	@Inject(method = "render", at = @At("HEAD"), cancellable = true)
	public void mclm153$render(int mouseX, int mouseY, float tickDelta, CallbackInfo ci) {
		this.renderBackground(mouseX, mouseY, tickDelta);
		Tessellator var4 = Tessellator.INSTANCE;
		this.fillGradient(0, 0, this.width, this.height, -2130706433, 16777215);
		this.fillGradient(0, 0, this.width, this.height, 0, Integer.MIN_VALUE);
		this.field_1229.textureManager.method_5146("/title/logo.png");
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		if ((double)this.minecraftRandomNumber < 1.0E-4) {
			// Minceraft
			this.drawTexture(this.width / 2 - 274 / 2, 30, 0, 90, 155, 44);
			this.drawTexture((this.width / 2 - 274 / 2) + 155, 30, 0, 135, 155, 44);
		} else {
			// Minecraft
			this.drawTexture(this.width / 2 - 274 / 2, 30, 0, 0, 155, 44);
			this.drawTexture((this.width / 2 - 274 / 2) + 155, 30, 0, 45, 155, 44);
		}
		var4.color(16777215);
		GL11.glPushMatrix();
		GL11.glTranslatef((float)(this.width / 2 + 90), 70.0F, 0.0F);
		GL11.glRotatef(-20.0F, 0.0F, 0.0F, 1.0F);
		float var8 = 1.8F - MathHelper.abs(MathHelper.sin((float)(Minecraft.getTime() % 1000L) / 1000.0F * 3.1415927F * 2.0F) * 0.1F);
		var8 = var8 * 100.0F / (float)(this.textRenderer.getStringWidth(this.splashText) + 32);
		GL11.glScalef(var8, var8, var8);
		this.drawCenteredString(this.textRenderer, this.splashText, 0, -8, 16776960);
		GL11.glPopMatrix();
		this.drawWithShadow(this.textRenderer, "FiveTwo " + Data.version.getFriendlyString(), 2, this.height - 22, 16777215);
		this.drawWithShadow(this.textRenderer, "Minecraft 1.5.2" + (this.field_1229.isDemo() ? " Demo" : ""), 2, this.height - 10, 16777215);
		String copyright = "Copyright Mojang AB. Not an official Minecraft product.";
		String not_approved = "Not approved by or associated with Mojang Studios or Microsoft.";
		this.drawWithShadow(this.textRenderer, copyright, this.width - this.textRenderer.getStringWidth(copyright) - 2, this.height - 22, 16777215);
		this.drawWithShadow(this.textRenderer, not_approved, this.width - this.textRenderer.getStringWidth(not_approved) - 2, this.height - 10, 16777215);
		if (this.oldGl1 != null && this.oldGl1.length() > 0) {
			fill(this.oldGlLeft - 2, this.oldGlTop - 2, this.oldGlRight + 2, this.oldGlBottom - 1, 1428160512);
			this.drawWithShadow(this.textRenderer, this.oldGl1, this.oldGlLeft, this.oldGlTop, 16777215);
			this.drawWithShadow(this.textRenderer, MORE_INFO_MESSAGE, (this.width - this.oldGl2Width) / 2, ((ButtonWidget)this.buttons.get(0)).y - 12, 16777215);
		}

		super.render(mouseX, mouseY, tickDelta);
		ci.cancel();
	}
	@Inject(method = "method_5260", at = @At("HEAD"), cancellable = true)
	public void mclm153$disableRealms(Language language, int i, int j, CallbackInfo ci) {
		ci.cancel();
	}
}
