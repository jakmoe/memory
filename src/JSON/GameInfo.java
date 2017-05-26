package JSON;

public class GameInfo {
	private double version;
	private double volume_music;
	private double volume_effects;
	private int difficulty;

	public GameInfo() {
		version = 1.0;
		volume_music = 0.5;
		volume_effects = 0.5;
		difficulty = 3;
	}
	public double getVersion() {
		return version;
	}

	public void setVersion(double version) {
		this.version = version;
	}
	public double getVolume_effects() {
		return volume_effects;
	}
	public void setVolume_effects(double volume_effects) {
		this.volume_effects = volume_effects;
	}
	public double getVolume_music() {
		return volume_music;
	}
	public void setVolume_music(double volume_music) {
		this.volume_music = volume_music;
	}
	public int getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(int difficulty) {
		this.difficulty = difficulty;
	}
	public int getCardcount(){
		switch (difficulty) {
		case 1:
			return 8;
		case 2:
			return 16;
		case 3:
			return 24;
		case 4:
			return 32;
		case 5:
			return 36;
		default:
			return 24;
		}
	}
	
	public double getPicSize(double width, double height){
		switch (difficulty) {
		case 1:
			return (width / height * 110 * 1.6);
		case 2:
			return (width / height * 110 * 1.25);
		case 3:
			return (width / height * 110 * 1.1);
		case 4:
			return (width / height * 110 * 0.9);
		case 5:
			return (width / height * 110 * 0.85);
		default:
			return 24;
		}
	}
}
