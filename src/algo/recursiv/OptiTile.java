package algo.recursiv;

/**
 * <b>OptiTile est la classe repr��sentant une tuile du jeu.</b>
 * <p>
 * Une tuile est caract��ris��e par les informations suivantes :
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
 * @author Valentine_Bouch��
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
     * Constructeur d'OptiTile avec les symboles
     * 
     * @param x
     *          La position en x.
     * @param y
     *          La position en y.
     * @param shape
     *          La forme de la tuile.
     */
	public OptiTile(int x, int y, String shape) {
		this.x = x;
		this.y = y;
		mark = false;
		if(shape.contains("║")) {
			type=Type.Vertical;
		}else if(shape.contains("═")) {
			type=Type.Horizontal;
		}else if(shape.contains("╚")) {
			type=Type.DownLeft;
		}else if(shape.contains("╔")) {
			type=Type.TopLeft;
		}else if(shape.contains("╗")) {
			type=Type.TopRight;
		}else if(shape.contains("╝")) {
			type=Type.DownRight;
		}else if(shape.contains("╬")) {
			type=Type.Cross;
		}else {
			type=Type.Empty;
		}
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
     * Renvoie l'��tat du marquage de la tuile
     * 
     * @return mark
     *          L'��tat du marquage.
     * 
     */
	public boolean getMark() {
        return this.mark;
    }
	
	/**
     * Change l'��tat du marquage de la tuile
     * 
     * @param mark 
     *          Le nouveau marquage de la tuile.
     * 
     */
	public void setMark(boolean mark) {
        this.mark = mark;
    }
}
