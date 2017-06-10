package JSON;

public class PlayerSave {
	private String name;
	private int highscore;
	private double mintime;
	private int id;

	public PlayerSave() {
		mintime = 0;
		highscore = 0;
		name = "name";
	}
	

	public PlayerSave(int id, String name, int highscore, double mintime) {
		super();
		this.name = name;
		this.highscore = highscore;
		this.mintime = mintime;
	}


	public static PlayerSave getDummy(int dummy_id) {
		PlayerSave dummy = new PlayerSave();
		dummy = new PlayerSave();
		dummy.setHighscore(0);
		dummy.setMintime(0.0);
		dummy.setName("name");
		return dummy;
	}

	public int getHighscore() {
		return highscore;
	}


	public double getMintime() {
		return mintime;
	}

	public String getName() {
		return name;
	}

	public void setHighscore(int highscore) {
		this.highscore = highscore;
	}

	public void setMintime(double mintime) {
		this.mintime = mintime;
	}

	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

}
