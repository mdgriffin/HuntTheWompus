package jri.agile.game;

public class GameTestUtil {
	
	public static Game buildEmptyMap (int height, int width) {
		Game game = new Game(height, width, new RickVideoPlayerMock());
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				game.setRoom(i, j, new Room (height, width, Room.RoomType.Normal));
			}
		}
		
		return game;
	}
	
	public static Game buildMap () {
		int width = 5;
		int height = 5;
		
		Game game = new Game(height, width, new RickVideoPlayerMock());
		
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {
				game.setRoom(i, j, new Room (i, j, Room.RoomType.Normal));
			}
		}
		
		game.setRoom(0, 4, new Room (0, 4, Room.RoomType.BatRoom));
		game.setRoom(4, 0, new Room (4, 0, Room.RoomType.BatRoom));
		game.setRoom(2, 2, new Room (2, 2, Room.RoomType.PitRoom));
		
		return game;
	}

}
