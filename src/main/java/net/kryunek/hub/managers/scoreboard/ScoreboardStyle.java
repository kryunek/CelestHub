package net.kryunek.hub.managers.scoreboard;

import lombok.Getter;

@Getter
public enum ScoreboardStyle {

	KOHI(true, 15), VIPER(true, -1), MODERN(false, 1), CUSTOM(false, 0);

	private boolean descending;
	private int startNumber;

	/**
	 * Scoreboard Style.
	 *
	 * @param descending  whether the positions are going down or up.
	 * @param startNumber from where to loop from.
	 */
	ScoreboardStyle(boolean descending, int startNumber) {
		this.descending = descending;
		this.startNumber = startNumber;
	}

	public ScoreboardStyle reverse() {
		return descending(!this.descending);
	}

	public ScoreboardStyle descending(boolean descending) {
		this.descending = descending;
		return this;
	}

	public ScoreboardStyle startNumber(int startNumber) {
		this.startNumber = startNumber;
		return this;
	}

}
