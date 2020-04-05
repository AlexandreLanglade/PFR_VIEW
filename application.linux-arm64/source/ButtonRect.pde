public class ButtonRect extends Button {
  
  private int longueur;
  private int largeur;
  private FSM link;
  private ACTION act;
  
  public ButtonRect(Point position, int longueur, int largeur, color c, FSM link, ACTION act) {
    super(position, c);
    this.longueur = longueur;
    this.largeur = largeur;
    this.link = link;
    this.act = act;
  }
  
  public ButtonRect(Point position, int longueur, int largeur, FSM link, ACTION act) {
    this(position, longueur, largeur, #3B4B76, link, act);
  }
  
  public void update() {
    color c = getColor();
    if (isUnderMouse(new Point(mouseX, mouseY))) c = color(#8CA7F0);
    stroke(c, 180);
    if (longueur < largeur) {
      strokeWeight(longueur/10);
    } else {
      strokeWeight(largeur/10);
    }
    strokeJoin(ROUND);
    fill(c);
    rect(getX(), getY(), largeur, longueur, 50);
  }
  
  public boolean isUnderMouse(Point p) {
    int x = (int)p.getX();
    int y = (int)p.getY();
    return x>getX() && x<getX()+largeur && y>getY() && y<getY()+longueur;
  }
  
  public FSM getLink() {
    return link;
  }
  
  public ACTION getAct() {
    return act;
  }
}
