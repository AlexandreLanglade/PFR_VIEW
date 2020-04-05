public abstract class Button {
  
  private Point position;
  private color c;
  
  public Button(Point position, color c) {
    this.position = position;
    this.c = c;
  }
  
  public abstract void update();
  
  public abstract boolean isUnderMouse(Point p);
  
  public int getX() {
    return (int)position.getX();
  }
  
  public int getY() {
    return (int)position.getY();
  }
  
  public color getColor() {
    return c;
  }
  
  public void setColor(color c) {
    this.c = c;
  }
  
}
