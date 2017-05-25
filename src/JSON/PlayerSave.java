package JSON;

public class PlayerSave {
	public static PlayerSave getDummy(int dummy_id){
		PlayerSave dummy = new PlayerSave();
    	dummy = new PlayerSave();
    	dummy.setHighscore(20);
    	dummy.setMintime(20.0);
    	dummy.setName("name");
    	dummy.setId(dummy_id);
		return dummy;
	}
	private int id;
	private String name;
	private int highscore;
	
	private double mintime;
	public int getHighscore() {
		return highscore;
	}
	public int getId() {
		return id;
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
	public void setId(int id) {
		this.id = id;
	}
	public void setMintime(double mintime) {
		this.mintime = mintime;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}
