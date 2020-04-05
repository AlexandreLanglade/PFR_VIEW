public class ButtonCircle extends Button {
  
  private int rayon;
  private boolean active = false;
  private int longueur;
  private int largeur;
  TypeRecherche typerech;
  
  public ButtonCircle(Point position, int longueur, int largeur, color c, TypeRecherche typerech) {
    super(position, c);
    this.longueur = longueur;
    this.largeur = largeur;
    this.rayon = 4*longueur/5;
    this.typerech = typerech;
  }
  
  public ButtonCircle(Point position, int longueur, int largeur, TypeRecherche typerech) {
    this(position, longueur, largeur, #3B4B76, typerech);
  }
  
  public ButtonCircle(Point position, int rayon, TypeRecherche typerech) {
    super(position, #3B4B76);
    this.rayon = rayon;
    longueur = 1;
    largeur = 1;
    this.typerech = typerech;
  }
    
  
  public void update() {
    noStroke();
    fill(getColor());
    rect(getX(), getY(), largeur, longueur, 7);
    strokeWeight(5);
    stroke(getColor()+70);
    if (!active) {
      fill(#CBC3C3);
      circle(getX()+rayon/2, getY()+longueur/2, 2*rayon);
    } else {
      fill(#3AF523);
      circle(getX()+largeur-rayon/2, getY()+longueur/2, 2*rayon);
    }
  }
  
  public boolean isUnderMouse(Point p) {
    int x = (int)p.getX();
    int y = (int)p.getY();
    if(!active) {
      return (x-(getX()+rayon/2))*(x-(getX()+rayon/2))+(y-(getY()+longueur/2))*(y-(getY()+longueur/2)) <= rayon*rayon;
    } else {
      return (x-(getX()+largeur-rayon/2))*(x-(getX()+largeur-rayon/2))+(y-(getY()+longueur/2))*(y-(getY()+longueur/2)) <= rayon*rayon;
    }
  }
  
  public void setActive(boolean t) {
    this.active = t;
  }
  
  public boolean getActive() {
    return this.active;
  }
  
  public int getRayon() {
    return this.rayon;
  }
  
  public TypeRecherche getTR() {
    return this.typerech;
  }
}
