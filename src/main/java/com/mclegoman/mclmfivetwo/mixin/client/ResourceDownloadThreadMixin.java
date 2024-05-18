/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.mixin.client;

import net.minecraft.client.ResourceDownloadThread;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

@Mixin(value = ResourceDownloadThread.class, priority = 100)
public abstract class ResourceDownloadThreadMixin {
	@Redirect(at = @At(value = "INVOKE", target = "Ljava/net/URL;openStream()Ljava/io/InputStream;"), method = "run")
	private InputStream init(URL instance) throws IOException, URISyntaxException {
		return new URI("https://web.archive.org/web/20121224001728if_/http://s3.amazonaws.com/MinecraftResources/").toURL().openStream();
	}
}
