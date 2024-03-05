package e1;

public class LogicsImpl implements Logics {
	
	private final Pair<Integer,Integer> pawn;
	private Pair<Integer,Integer> knight;
	private final int size;
	 
    public LogicsImpl(int size, PositionGenerator positionGenerator){
		this(size, positionGenerator.generate(), positionGenerator.generate());
    }
    
	public LogicsImpl(int size, Pair<Integer, Integer> knight, Pair<Integer, Integer> pawn) {
		this.size = size;
		this.knight = knight;
		this.pawn = pawn;
	}
    
	@Override
	public boolean hit(int row, int col) {
		if (row<0 || col<0 || row >= this.size || col >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		// Below a compact way to express allowed moves for the knight
		int x = row-this.knight.getX();
		int y = col-this.knight.getY();
		if (x!=0 && y!=0 && Math.abs(x)+Math.abs(y)==3) {
			this.knight = new Pair<>(row,col);
			return this.pawn.equals(this.knight);
		}
		return false;
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
