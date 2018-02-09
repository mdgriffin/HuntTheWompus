package jri.agile.fixtures;

import jri.agile.game.GameText;

public class NotificationMessages {

	
	public String PlayAgainMessageAppears () {
		return GameText.playAgain;
	}
	
	public String PlayerEndsGame () {
		return GameText.goodbye;
	}
	
	public String whenGameStarts () {
		return GameText.instructions.replace("\n", "");
	}
	
	public String whenPlayerKillsRick () {
		return GameText.arrowShoot + " " + GameText.killedRick;
	}
	
	public String WhenBatsAreInNextRoom () {
		return GameText.senseBats;
	}
	
	public String WhenInSameRoomAsBats () {
		return GameText.movedByBats;
	}
	
	public String WhenPlayerIsNextToPitRoom () {
		return GameText.sensePit;
	}
	
	public String WhenPlayerIsInPitRoom () {
		return GameText.killedByPit;
	}
	
	public String whenRickIsInNextRoom () {
		return GameText.rickText;
	}
	
	public String whenRickKillsPlayer () {
		return GameText.killedByRick;
	}
}
