package e1;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final Movement movement;
	 
    public LogicsImpl(PositionGenerator positionGenerator, Movement movement){
		this(positionGenerator.generate(), positionGenerator.generate(), movement);
    }
    
	public LogicsImpl(Pair<Integer, Integer> knight, Pair<Integer, Integer> pawn, Movement movement) {
		this.knight = knight;
		this.pawn = pawn;
		this.movement = movement;
	}
    
	@Override
	public boolean hit(int row, int col) {
		var destination = new Pair<>(row, col);
		this.knight = movement.move(destination, knight);
		return this.pawn.equals(this.knight);
	}

	@Override
	public boolean hasKnight(int row, int col) {
		return this.knight.equals(new Pair<>(row,col));
	}

	@Override
	public boolean hasPawn(int row, int col) {
		return this.pawn.equals(new Pair<>(row,col));
	}
}
