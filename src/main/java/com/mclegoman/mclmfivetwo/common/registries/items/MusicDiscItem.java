/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common.registries.items;

public class MusicDiscItem extends net.minecraft.item.MusicDiscItem {
	private final String author;
	private final String name;
	public MusicDiscItem(int i, String author, String name, String id) {
		super(i, id);
		this.author = author;
		this.name = name;
	}
	@Override
	public String getDescription() {
		return this.author + " - " + this.name;
	}
}
