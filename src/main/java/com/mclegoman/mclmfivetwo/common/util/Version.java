/*
    FiveTwo
    Contributor(s): MCLegoMan
    Github: https://github.com/MCLegoMan/mclmfivetwo
    Licence: GNU LGPLv3
*/

package com.mclegoman.mclmfivetwo.common.util;

import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;

public class Version implements Comparable<Version> {
	private final String name;
	private final String id;
	private final int major;
	private final int minor;
	private final int patch;
	private final ReleaseType type;
	private final int build;
	private final boolean hasModrinthId;
	private final String modrinthId;
	private Version(String name, String id, int major, int minor, int patch, ReleaseType type, int build, boolean hasModrinthId, String modrinthId) {
		this.name = name;
		this.id = id;
		this.major = major;
		this.minor = minor;
		this.patch = patch;
		this.type = type;
		this.build = build;
		this.hasModrinthId = hasModrinthId;
		this.modrinthId = modrinthId;
	}
	public static Version create(String name, String id, int major, int minor, int patch, ReleaseType type, int build, String modrinthId) {
		return new Version(name, id, major, minor, patch, type, build, true, modrinthId);
	}
	public static Version create(String name, String id, int major, int minor, int patch, ReleaseType type, int build) {
		return new Version(name, id, major, minor, patch, type, build, false, "");
	}
	public String getFriendlyString(boolean full) {
		return full ? getFriendlyString() : (getType().equals(ReleaseType.RELEASE) ? String.format("%s.%s%s", getMajor(), getMinor(), (getPatch() != 0 ? "." + getPatch() : "")) : getFriendlyString());
	}
	public boolean hasModrinthID() {
		return this.hasModrinthId;
	}
	public String getModrinthID() {
		return this.modrinthId;
	}
	public ModContainer getModContainer() {
		return FabricLoader.getInstance().getModContainer(getID()).isPresent() ? FabricLoader.getInstance().getModContainer(getID()).get() : null;
	}
	public String getName() {
		return name;
	}
	public String getID() {
		return id;
	}
	public int getMajor() {
		return major;
	}
	public int getMinor() {
		return minor;
	}
	public int getPatch() {
		return patch;
	}
	public ReleaseType getType() {
		return type;
	}
	public int getBuild() {
		return build;
	}
	public String getFriendlyString() {
		return String.format("%s.%s%s-%s.%s", getMajor(), getMinor(), (getPatch() != 0 ? "." + getPatch() : ""), Helper.releaseTypeString(getType(), Helper.TranslationType.CODE), getBuild());
	}
	public String getLoggerPrefix() {
		return "[" + getName() + " " + getFriendlyString() + "]";
	}
	public boolean isDevelopmentBuild() {
		return !type.equals(ReleaseType.RELEASE);
	}
	@Override
	public int compareTo(Version other) {
		if (major != other.major) {
			return Integer.compare(major, other.major);
		} else if (minor != other.minor) {
			return Integer.compare(minor, other.minor);
		} else if (patch != other.patch) {
			return Integer.compare(patch, other.patch);
		} else if (type != other.type) {
			return type.compareTo(other.type);
		} else {
			return Integer.compare(build, other.build);
		}
	}
	public void sendToLog(LogType logType, String logMessage) {
		if (logType.equals(LogType.INFO)) System.out.println(getLoggerPrefix() + " " + logMessage);
		if (logType.equals(LogType.WARN)) System.out.println(getLoggerPrefix() + " [WARN] " + logMessage);
		if (logType.equals(LogType.ERROR)) System.out.println(getLoggerPrefix() + " [ERROR] " + logMessage);
		if (logType.equals(LogType.DEBUG)) System.out.println(getLoggerPrefix() + " [DEBUG] " + logMessage);
	}
}