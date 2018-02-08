package jri.agile.fixtures;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import jri.agile.game.*;

public class GameFixture {
	
	public static GameContext gc;
	
	public GameFixture () {
		if (gc == null) {
			gc = new GameContext();
		}
	}
	
	public void setup () {
		Game game = new Game(5,5);
	}
	
	
	public int getPlayerPositionX () {
		GameEntity player = GameContext.game.getPlayer();
		
		return  player.getCurrentPosition().getXPos();
	}
	
	public int getPlayerPositionY () {
		GameEntity player = GameContext.game.getPlayer();
		
		return  player.getCurrentPosition().getYPos();
	}
	
	public int getWumpusPositionX () {
		GameEntity wump = GameContext.game.getRick();
		
		return  wump.getCurrentPosition().getXPos();
	}
	
	public int getWumpusPositionY () {
		GameEntity wump = GameContext.game.getRick();
		
		return  wump.getCurrentPosition().getYPos();
	}
	
	public String movePlayerOneRoomEastWest () {
		GameEntity player = GameContext.game.getPlayer();
		player.move('E');
		int posY = player.getCurrentPosition().getYPos();
		int posX = player.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		player.move('W');
		int posFinalY = player.getCurrentPosition().getYPos();
		int posFinalX = player.getCurrentPosition().getXPos();
		fullPosition += ", (" + posFinalY + "," + posFinalX + ")";
		return fullPosition;
	}
	
	public String movePlayerOneRoomNorthSouth () {
		GameEntity player = GameContext.game.getPlayer();
		player.move('S');
		int posY = player.getCurrentPosition().getYPos();
		int posX = player.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		player.move('N');
		int posFinalY = player.getCurrentPosition().getYPos();
		int posFinalX = player.getCurrentPosition().getXPos();
		fullPosition += ", (" + posFinalY + "," + posFinalX + ")";
		return fullPosition;
	}
	
	public String moveWumbusOneRoomWestEast() {
		GameEntity wumbus = GameContext.game.getRick();
		wumbus.move('W');
		int posY = wumbus.getCurrentPosition().getYPos();
		int posX = wumbus.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		wumbus.move('E');
		int posFinalY = wumbus.getCurrentPosition().getYPos();
		int posFinalX = wumbus.getCurrentPosition().getXPos();
		fullPosition += ", (" + posFinalY + "," + posFinalX + ")";
		return fullPosition;
		
	}
	public String moveWumbusOneRoomSouthNorth() {
		GameEntity wumbus = GameContext.game.getRick();
		wumbus.move('N');
		int posY = wumbus.getCurrentPosition().getYPos();
		int posX = wumbus.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		wumbus.move('S');
		int posFinalY = wumbus.getCurrentPosition().getYPos();
		int posFinalX = wumbus.getCurrentPosition().getXPos();
		fullPosition += ", (" + posFinalY + "," + posFinalX + ")";
		return fullPosition;
	}
	public String tryMovePlayerOffBoardWest () {
		GameEntity player = GameContext.game.getPlayer();
		player.move('W');
		int posY = player.getCurrentPosition().getYPos();
		int posX = player.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		return fullPosition;
	}
	
	public String tryMovePlayerOffBoardNorth () {
		GameEntity player = GameContext.game.getPlayer();
		player.move('N');
		int posY = player.getCurrentPosition().getYPos();
		int posX = player.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		return fullPosition;
	}
	public String tryMoveWumbusOffBoardEast () {
		GameEntity wumbus = GameContext.game.getRick();
		wumbus.move('E');
		int posY = wumbus.getCurrentPosition().getYPos();
		int posX = wumbus.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		return fullPosition;
	}
	
	public String tryMoveWumbusOffBoardSouth () {
		GameEntity wumbus = GameContext.game.getRick();
		wumbus.move('S');
		int posY = wumbus.getCurrentPosition().getYPos();
		int posX = wumbus.getCurrentPosition().getXPos();
		String fullPosition = "(" + posX + "," + posY + ")"; 
		return fullPosition;
	}
	
	public boolean TestIfMapHasPitRoom()
	{
		Room roomPit =  new Room(1, 0, Room.RoomType.PitRoom);
		GameContext.game.setRoom(0, 1, roomPit);
		Room r1 = GameContext.game.getRoom(0, 1);
		boolean isItPit = r1.hasPit();
		return isItPit;
	}
	
	public boolean TestIfMapHasBatRoom()
	{
		Room roomBat =  new Room(0, 1, Room.RoomType.BatRoom);
		GameContext.game.setRoom(0, 1, roomBat);
		Room r1 = GameContext.game.getRoom(0, 1);
		boolean isItBat = r1.hasBats();
		return isItBat;
	}
	
	public boolean TestIfMapHasNormalRoom()
	{
		Room roomNormal =  new Room(0, 1, Room.RoomType.Normal);
		GameContext.game.setRoom(0, 1, roomNormal);
		Room r1 = GameContext.game.getRoom(0, 1);
		boolean isItBat = r1.hasBats();
		boolean isItPit = r1.hasPit();
		boolean isItNormal = true;
		if(isItBat == false && isItPit == false)
			isItNormal = true;
			else
				isItNormal = false;
		return isItNormal;
	}

	
	public boolean TestMoveRickRandomly()
	{
		Game game = new Game(3, 3);
		GameEntity rick = game.getRick();
		BoardPosition position = rick.getCurrentPosition();
		int movedNorth = 0;
		int movedSouth = 0;
		int movedEast = 0;
		int movedWest = 0;
		int rested = 0;
		
		for (int i = 0; i < 10000; i++) {
			position.setXPos(1);
			position.setYPos(1);
			
			rick.moveRandom();
			
			if (position.getYPos() == 0) {
				movedNorth++;
			} else if (position.getYPos() == 2) {
				movedSouth++;
			} else  if (position.getXPos() == 0) {
				movedWest++;
			} else if (position.getXPos() == 2) {
				movedEast++;
			} else if (position.getXPos() == 1 && position.getYPos() == 1) {
				rested++;
			}
		}
	
		boolean ricksMovesRandomly = false;
		if(movedNorth + movedSouth + movedEast + movedWest + rested == 10000)
		{
			if(movedNorth > 1500 && movedSouth > 1500 &&movedEast > 1500 &&movedWest > 1500 && rested > 1500)
			{
				ricksMovesRandomly = true;
			}
			else ricksMovesRandomly = false;
		}	
		return ricksMovesRandomly;
	}

	
	
	public boolean doesPlayerDieInPitRoom()
	{
		boolean isPlayerDead = false;
		Game gameWithPit = new Game(2, 2);
		Player player = gameWithPit.getPlayer();
		gameWithPit.setRoom(0, 0, new Room (2, 2, Room.RoomType.Normal));
		gameWithPit.setRoom(0, 1, new Room (2, 2, Room.RoomType.Normal));
		gameWithPit.setRoom(1, 0, new Room (2, 2, Room.RoomType.Normal));
		gameWithPit.setRoom(1, 1, new Room (2, 2, Room.RoomType.PitRoom));
		player.move('E');
		player.move('S');
		
		if(gameWithPit.getPlayer().isAlive() == false)
		{
			isPlayerDead = true;
		}
		else isPlayerDead = false;
		
		return isPlayerDead;
	}
	
	public static Game buildEmptyMap (int height, int width) {
		Game game = new Game(height, width);
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				game.setRoom(i, j, new Room (height, width, Room.RoomType.Normal));
			}
		}
		
		return game;
	}
	
	public boolean doesRickKillPlayer()
	{
		boolean isPlayerDead = false;
		Game theGame = buildEmptyMap(2, 1);
		GameEntity theplayer = theGame.getPlayer();
		GameEntity wumbus = theGame.getRick();
		int i = 0;
		while(i < 100){
			theplayer.move('R');
			i++;
		}
		
		if(theGame.getPlayer().isAlive() == false)
		{
			isPlayerDead = true;
		}
		else isPlayerDead = false;
		
		return isPlayerDead;
	}
	
	
	public int checkInitialArrowCount(){
		Game theGame = buildEmptyMap(2, 2);
		int numArrowsAtStartOfGame = theGame.getPlayer().getNumArrows();
		
		return numArrowsAtStartOfGame;
	}
	
	
	
	public boolean doesArrowCountDecrease()
	{
		boolean doesArrowCountDecrease = false;
		Game theGame = buildEmptyMap(2, 2);
		int numArrowsBefore = theGame.getPlayer().getNumArrows();
		
		theGame.getPlayer().shoot('S');
		
		int numArrowsAfterShooting = theGame.getPlayer().getNumArrows();
		
		if(numArrowsBefore > numArrowsAfterShooting)
		{
			doesArrowCountDecrease = true;
		}
		else doesArrowCountDecrease = false;
		
		return doesArrowCountDecrease;
	}
	
	
	public boolean CanPlayerPickUpArrowAfterShooting()
	{
		boolean wasArrowPickedUp = false;		
		Game game = buildEmptyMap(5, 5);
		Player player = game.getPlayer();
		player.move('E');
		int arrowsFull = player.getNumArrows();
		player.shoot('W');
		int arrowsAfterOneShot = player.getNumArrows();
		player.move('W');
		int arrowsAfterPickedUp = player.getNumArrows();
		
		if(arrowsFull > arrowsAfterOneShot && arrowsFull == arrowsAfterPickedUp)
		{
			wasArrowPickedUp = true;
		}
		else wasArrowPickedUp = false;
		
		return wasArrowPickedUp;
	}
	
	public String arrowCountWhileMovingThroughRoomAfterShot()
	{
		Game game = buildEmptyMap(5, 5);
		Player player = game.getPlayer();
		player.move('E');
		int arrowsFull = player.getNumArrows();
		player.shoot('S');
		player.move('S');
		int arrowsAfterShotRoomOne = player.getNumArrows();
		player.move('S');
		int arrowsAfterShotRoomTwo = player.getNumArrows();
		player.move('S');
		int arrowsAfterShotRoomThree = player.getNumArrows();
		player.move('S');
		int arrowsAfterPickedUp = player.getNumArrows();
		
		String ArrowCount = arrowsFull + "," + arrowsAfterShotRoomOne + "," 
							+ arrowsAfterShotRoomTwo + "," + arrowsAfterShotRoomThree + "," + arrowsAfterPickedUp;
		
		return ArrowCount;
	}

	
	public boolean doesPlayerKillThemsevlesWithArrow()
	{
		boolean isPlayerDead = false;
		Game game = buildEmptyMap(5, 5);
		Player player = game.getPlayer();
		player.shoot('W');
		
		if(game.getPlayer().isAlive() == false)
		{
			isPlayerDead = true;
		}
		else isPlayerDead = false;
		
		return isPlayerDead;
	}
	
	
	
	
	
	
	
	
}