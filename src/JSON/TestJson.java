package JSON;

public class TestJson {

	public static void main(String[] args) {
		JSONhandler jhdl = new JSONhandler();
		
		PlayerSave ps = jhdl.readinfo(2);
		ps.setHighscore(400);
		jhdl.writeinfo(ps);
		

		jhdl.debug();
	}

}
