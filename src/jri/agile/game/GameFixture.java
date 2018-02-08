package jri.agile.game;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

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
		Rick rick = game.getRick();
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
		int row = 0, col = 1;
		boolean isPlayerDead = false;
		Player player = gc.game.getPlayer();

		Room pitRoom = new Room(row, col, Room.RoomType.PitRoom);		
		GameContext.game.setRoom(row, col, pitRoom);
		player.move('E');
		if(GameContext.game.getPlayer().isAlive()){
			isPlayerDead = false;
		}
		else isPlayerDead = true;

		return isPlayerDead;
	}
	
	public boolean doesRickKillPlayer()
	{
		boolean dead =false;
		Game theGame = new Game(5, 5);
		
		GameEntity theplayer = theGame.getPlayer();
		GameEntity wumbus = theGame.getRick();
		BoardPosition positionRick = wumbus.getCurrentPosition();
		BoardPosition positionPlayer = theplayer.getCurrentPosition();
		int x = positionRick.getXPos();
		int y = positionRick.getYPos();
		int x1 = positionPlayer.getXPos();
		int y1 = positionPlayer.getYPos();
		
		theplayer.move('E');
		theplayer.move('E');
		theplayer.move('E');
		theplayer.move('E');
		theplayer.move('S');
		theplayer.move('S');
		theplayer.move('S');
		theplayer.move('S');
		
		return theplayer.isAlive();
	}
}