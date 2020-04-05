public class ButtonOnOff extends ButtonCircle {
  public ButtonOnOff(Point position, int rayon, TypeRecherche typerech) {
     super(position, rayon, typerech);
  }
  
    public void update() {
    noStroke();
    fill(getColor());
    strokeWeight(5);
    stroke(getColor()+70);
    if (!getActive()) {
      fill(#CBC3C3);
    } else {
      fill(#3AF523);
    }
    circle(getX(), getY(), 2*getRayon());
  }
  
  public boolean isUnderMouse(Point p) {
    int x = (int)p.getX();
    int y = (int)p.getY();
    return (x-getX())*(x-getX())+(y-getY())*(y-getY()) <= getRayon()*getRayon();
  }
}
