package algo.opti;

/**
 * <b>OptiTile est la classe représentant une tuile du jeu.</b>
 * <p>
 * Une tuile est caractérisée par les informations suivantes :
 * <ul>
 * <li>Une position en x</li>
 * <li>Une position en y</li>
 * <li>Un type</li>
 * <li>Un marquage</li>
 * </ul>
 * </p>
 * 
 * @see Type
 * 
 * @author Valentine_Bouché
 * @version 1.0
 */
public class OptiTile {
	private int x;
	private int y;
	private Type type;
	private boolean mark;
	
	/**
     * Constructeur d'OptiTile
     * 
     * @param x
     *          La position en x.
     * @param y
     *          La position en y.
     * @param type
     *          Le type de la Tuile.
     */
	public OptiTile(int x, int y, Type type) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.mark = false;
	}
	
	/**
     * Renvoie la valeur de X
     * 
     * @return x
     *          La position de la tuile en X.
     * 
     */
	public int getX() {
		return this.x;
	}
	
	/**
     * Renvoie la valeur de Y
     * 
     * @return y
     *          La position de la tuile en Y.
     * 
     */
	public int getY() {
		return this.y;
	}
	
	/**
     * Renvoie le type de la tuile
     * 
     * @return type
     *          Le type de la tuile.
     * 
     */
	public Type getType() {
		return this.type;
	}
	
	/**
     * Renvoie l'état du marquage de la tuile
     * 
     * @return mark
     *          L'état du marquage.
     * 
     */
	public boolean getMark() {
        return this.mark;
    }
	
	/**
     * Change l'état du marquage de la tuile
     * 
     * @param mark 
     *          Le nouveau marquage de la tuile.
     * 
     */
	public void setMark(boolean mark) {
        this.mark = mark;
    }
}
