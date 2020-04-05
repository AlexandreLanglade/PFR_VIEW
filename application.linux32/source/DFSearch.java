import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.awt.Point; 
import java.util.List; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class DFSearch extends PApplet {




ArrayList<Button> buttonsAccueila;
ArrayList<Button> buttonsAccueilu;
ArrayList<Button> buttonsLogin;
ArrayList<Button> buttonsRecherche;
ArrayList<Button> buttonsResultat;
ArrayList<Button> buttonsHistorique;
ArrayList<Button> buttonsConfiguration;
ArrayList<Button> buttonsDescripteur;
ArrayList<Button> buttonsMdp;
FSM mae;

ControlVerifierID controlVerifierID = new ControlVerifierID();
ControlEnregistrerDansHistorique controlEnregistrerDansHistorique = new ControlEnregistrerDansHistorique();
ControlLancerRecherche controlLancerRecherche = new ControlLancerRecherche(controlEnregistrerDansHistorique);
ControlVisualiserHistorique controlVisualiserHistorique = new ControlVisualiserHistorique();
ControlLancerIndexation controlLancerIndexation = new ControlLancerIndexation(controlVerifierID);
ControlVisualiserDescripteurs controlVisualiserDescripteurs = new ControlVisualiserDescripteurs(controlVerifierID);
ControlChangerConfiguration controlChangerConfiguration = new ControlChangerConfiguration(controlVerifierID);
ControlEffacerHistorique controlEffacerHistorique = new ControlEffacerHistorique(controlVerifierID);
ControlChangerMdp controlChangerMdp = new ControlChangerMdp(controlVerifierID);
ControlAuthentification controlAuthentification = new ControlAuthentification(controlVerifierID);

int nbindex = 0;
String login = "";
String loginhide = "";
String recherche = "";
String valeur = "";
String newlog = "";
List<Recherche> histoL;
TypeRecherche typerech = TypeRecherche.TEXTE;
Recherche rechercheR;
int ord = 200;

public void setup() {
   
   
   initButtons();
   
   mae = FSM.ACCUEIL; 
   
}

public void draw() {
  if(nbindex!=0)ord -= 3;
  if (ord<-2090*nbindex)ord = -2090*nbindex;
  background(0xff001346);
  switch (mae) {
    case ACCUEIL :
      afficheAccueil();
      break;
    case LOGIN :
      afficheLogin();
      break;
    case RECHERCHE :
      afficheRecherche();
      break;
    case RESULTAT :
      afficheResultat();
      break;
    case HISTORIQUE :
      afficheHistorique();
      break;
    case CONFIGURATION :
      afficheConfiguration();
      break;
    case DESCRIPTEUR :
      afficheDescripteur();
      break;
    case MDP :
      afficheMdp();
      break;     
    default :
      break;
  }
}

public void initButtons() {
  
   // buttonsAccueila
   buttonsAccueila = new ArrayList();
   ButtonRect recha = new ButtonRect(new Point(80,400), 175, 350, FSM.RECHERCHE, null);
   buttonsAccueila.add(recha);
   ButtonRect histoa = new ButtonRect(new Point(520,400), 175, 350, FSM.HISTORIQUE, ACTION.UPHISTO);
   buttonsAccueila.add(histoa);
   ButtonRect config = new ButtonRect(new Point(965,400), 175, 350, FSM.CONFIGURATION, null);
   buttonsAccueila.add(config);
   ButtonRect descript = new ButtonRect(new Point(80,650), 175, 350, FSM.DESCRIPTEUR, null);
   buttonsAccueila.add(descript);
   ButtonRect index = new ButtonRect(new Point(520,650), 175, 350, FSM.ACCUEIL, ACTION.INDEX);
   buttonsAccueila.add(index);
   ButtonRect mdpb = new ButtonRect(new Point(965,650), 175, 350, FSM.MDP, null);
   buttonsAccueila.add(mdpb);  
   
   // buttonsAccueilu
   buttonsAccueilu = new ArrayList();
   ButtonRect seConnecter = new ButtonRect(new Point(1100,60), 70, 230, FSM.LOGIN, null);
   buttonsAccueilu.add(seConnecter);
   ButtonRect rech = new ButtonRect(new Point(195,550), 200, 400, FSM.RECHERCHE, null);
   buttonsAccueilu.add(rech);
   ButtonRect histo = new ButtonRect(new Point(800,550), 200, 400, FSM.HISTORIQUE, ACTION.UPHISTO);
   buttonsAccueilu.add(histo);
   
   // buttonsLogin
   buttonsLogin = new ArrayList();
   ButtonRect login = new ButtonRect(new Point(475,550), 80, 450, 0xff31C61E, FSM.ACCUEIL, ACTION.LOGIN);
   buttonsLogin.add(login);
   
   // buttonsRecherche
   buttonsRecherche = new ArrayList();
   ButtonOnOff t = new ButtonOnOff(new Point(530,390), 20, TypeRecherche.TEXTE);
   buttonsRecherche.add(t);
   ButtonOnOff i = new ButtonOnOff(new Point(750,390), 20, TypeRecherche.IMAGE);
   buttonsRecherche.add(i);
   ButtonOnOff s = new ButtonOnOff(new Point(970,390), 20, TypeRecherche.AUDIO);
   buttonsRecherche.add(s);
   ButtonRect lancerrech = new ButtonRect(new Point(475,700), 80, 450, FSM.RESULTAT, ACTION.RECHERCHE);
   buttonsRecherche.add(lancerrech);
   ButtonRect retourrech = new ButtonRect(new Point(50, 50), 70, 70, FSM.ACCUEIL, null);
   buttonsRecherche.add(retourrech);
   
   // buttonsResultat
   buttonsResultat = new ArrayList();
   ButtonRect enreg = new ButtonRect(new Point(200,700), 80, 450, FSM.ACCUEIL, ACTION.ENREG);
   buttonsResultat.add(enreg);
   ButtonRect nonenreg = new ButtonRect(new Point(750, 700), 80, 450, FSM.ACCUEIL, null);
   buttonsResultat.add(nonenreg);
   
   // buttonsHistorique
   buttonsHistorique = new ArrayList();
   ButtonRect retourhisto = new ButtonRect(new Point(50, 50), 70, 70, FSM.ACCUEIL, null);
   buttonsHistorique.add(retourhisto);
   ButtonRect supp = new ButtonRect(new Point(935, 120), 60, 240, 0xffF7321B, FSM.HISTORIQUE, ACTION.SUPP);
   buttonsHistorique.add(supp);
   
   // buttonsConfiguration
   buttonsConfiguration = new ArrayList();
   ButtonRect retourconfig = new ButtonRect(new Point(50, 50), 70, 70, FSM.ACCUEIL, null);
   buttonsConfiguration.add(retourconfig);
   ButtonRect modifconfig1 = new ButtonRect(new Point(780, 700), 50, 50, FSM.CONFIGURATION, ACTION.CONFUN);
   buttonsConfiguration.add(modifconfig1);
   ButtonRect modifconfig2 = new ButtonRect(new Point(860, 700), 50, 50, FSM.CONFIGURATION, ACTION.CONFDEUX);
   buttonsConfiguration.add(modifconfig2);
   ButtonRect modifconfig3 = new ButtonRect(new Point(940, 700), 50, 50, FSM.CONFIGURATION, ACTION.CONFTROIS);
   buttonsConfiguration.add(modifconfig3);
   ButtonRect modifconfig4 = new ButtonRect(new Point(1020, 700), 50, 50, FSM.CONFIGURATION, ACTION.CONFQUATRE);
   buttonsConfiguration.add(modifconfig4);
   ButtonRect modifconfig5 = new ButtonRect(new Point(1100, 700), 50, 50, FSM.CONFIGURATION, ACTION.CONFCINQ);
   buttonsConfiguration.add(modifconfig5);
   
   // buttonsDescripteur
   buttonsDescripteur = new ArrayList();
   ButtonRect retourdes = new ButtonRect(new Point(50, 50), 70, 70, FSM.ACCUEIL, null);
   buttonsDescripteur.add(retourdes);
   
   // buttonsMdp
   buttonsMdp = new ArrayList();
   ButtonRect retourcmdp = new ButtonRect(new Point(50, 50), 70, 70, FSM.ACCUEIL, null);
   buttonsMdp.add(retourcmdp);
   ButtonRect cmdp = new ButtonRect(new Point(475,550), 80, 450, 0xff31C61E, FSM.ACCUEIL, ACTION.CMDP);
   buttonsMdp.add(cmdp);
      
}

public void afficheAccueil() {
  if (controlVerifierID.verifierID() == true) {
    for (int i = 0; i < buttonsAccueila.size(); i++) {
      buttonsAccueila.get(i).update();
    }
    fill(0xff0B063B);
    stroke(0xffFFFFFF);
    strokeWeight(3);
    rect(250, 120, 900, 140);
    fill(0xffFFFFFF);
    textSize(100);
    text("Administrateur", 330, 225);
    textSize(37);
    text(" Effectuer\n     une\nRecherche", 160, 445);
    text("Historique", 600, 500);
    text("Configuration", 1015, 500);
    text("Descripteurs", 140, 750);
    text("   Lancer\n     une\nIndexation", 600, 690);
    text("Changer\n   MDP", 1060, 720);
  } else {
    for (int i = 0; i < buttonsAccueilu.size(); i++) {
      buttonsAccueilu.get(i).update();
    }
    fill(0xffFFFFFF);
    textSize(30);
    text("Se connecter", 1120, 105);
    textSize(40);
    text(" Effectuer\n    une\nRecherche", 290, 605);
    text(" Visualiser\nl'Historique", 890, 635);
    fill(0xff0B063B);
    stroke(0xffFFFFFF);
    strokeWeight(2);
    rect(75, 85, 850, 340);
    fill(0xffFFFFFF);
    textSize(170);
    text("DFSearch", 120, 320);
  }
}

public void afficheLogin() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(3);
  rect(450, 200, 500, 500);
  for (int i = 0; i < buttonsLogin.size(); i++) {
      buttonsLogin.get(i).update();
  }
  noStroke();
  fill(0xffFFFFFF);
  rect(475,440,450,80,20);
  textSize(60);
  text("Mot De Passe", 507, 320);
  textSize(40);
  text("Valider", 630, 605);
  fill(0xff000000);
  text(loginhide,500,495);
}

public void afficheRecherche() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(1);
  rect(100, 100, 1200, 750);
  for (int i = 0; i < buttonsRecherche.size(); i++) {
      buttonsRecherche.get(i).update();
  }
  fill(0xffFFFFFF);
  noStroke();
  rect(700,512,550,50,10);
  textSize(60);
  text("↶", 52, 100);
  textSize(50);
  text("Recherche", 570, 165);
  textSize(30);
  text("Type de recherche : ", 180, 400);
  text("Adresse ou critère de recherche : ", 180, 550);
  text("texte", 560, 400);
  text("image", 780, 400);
  text("audio", 1000, 400);
  text("Lancer la recherche", 560, 750);
  fill(0xff000000);
  text(recherche, 720, 547);
}

public void afficheResultat() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(1);
  rect(100, 100, 1200, 750);
  for (int i = 0; i < buttonsResultat.size(); i++) {
      buttonsResultat.get(i).update();
  }
  fill(0xffFFFFFF);
  textSize(50);
  text("Résultat", 595, 165);
  textSize(30);
  text(controlLancerRecherche.afficherRecherche(rechercheR), 250, 330);
  text("Enregistrer et Quitter", 269, 750);
  text("Quitter", 918, 750);
}

public void afficheHistorique() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(1);
  rect(100, 100, 1200, 750);
  buttonsHistorique.get(0).update();
  if (controlVerifierID.verifierID() == true) {
    buttonsHistorique.get(1).update();
    fill(0xffFFFFFF);
    textSize(30);
    text("Supprimer", 968, 159);
  }
  fill(0xffFFFFFF);
  textSize(60);
  text("↶", 52, 100);
  textSize(50);
  text("Historique", 585, 165);
  textSize(15);
  int xstart = 120;
  int x = xstart;
  int y = 300;
  for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
      if (3*i+j >= histoL.size()) return; 
      text(histoL.get(3*i+j).toString(), x, y);
      x += 400;
    }
    y += 210;
    x = xstart;
  } 
}

public void afficheConfiguration() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(1);
  rect(100, 100, 1200, 750);
  for (int i = 0; i < buttonsConfiguration.size(); i++) {
      buttonsConfiguration.get(i).update();
  }
  fill(0xffFFFFFF);
  noStroke();
  rect(300,700,200,50,10);
  fill(0xffFFFFFF);
  textSize(50);
  text("Configuration", 535, 165);
  textSize(25);
  text(controlChangerConfiguration.afficherConfig(), 520, 300);
  text("Nouvelle valeur : ", 300, 680);
  text("Appliquer à : ", 780, 680);
  text("1", 798, 735);
  text("2", 878, 735);
  text("3", 958, 735);
  text("4", 1038, 735);
  text("5", 1118, 735);
  textSize(60);
  text("↶", 52, 100);
  fill(0xff000000);
  textSize(25);
  text(valeur, 310, 735);
}

public void afficheDescripteur() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(1);
  rect(100, 100+ord, 1200, 2090*nbindex);
  for (int i = 0; i < buttonsDescripteur.size(); i++) {
      buttonsDescripteur.get(i).update();
  }
  fill(0xffFFFFFF);
  textSize(50);
  text("Descripteurs", 540, 165+ord);
  textSize(60);
  text("↶", 52, 100);
  textSize(20);
  text(controlVisualiserDescripteurs.visualiserDescripteurs(), 580, ord+400);
}

public void afficheMdp() {
  fill(0xff0B063B);
  stroke(0xffFFFFFF);
  strokeWeight(3);
  rect(450, 200, 500, 500);
  for (int i = 0; i < buttonsMdp.size(); i++) {
      buttonsMdp.get(i).update();
  }
  noStroke();
  fill(0xffFFFFFF);
  rect(475,440,450,80,20);
  textSize(60);
  text("Changer le Mdp", 470, 320);
  textSize(40);
  text("Valider", 630, 605);
  textSize(17);
  text("Nouveau code secret :", 485, 425);
  textSize(60);
  text("↶", 52, 100);
  fill(0xff000000);
  textSize(40);
  text(newlog,500,495);
}


public void mousePressed() {
  Point p = new Point(mouseX, mouseY);
  ArrayList<Button> al = null;
  switch (mae) {
    case ACCUEIL :
      if (controlVerifierID.verifierID() == true) {
        al = buttonsAccueila;
      } else {
        al = buttonsAccueilu;
      }
      break;
    case LOGIN :
      al = buttonsLogin;
      break;
    case RECHERCHE :
      al = buttonsRecherche;
      break;
    case RESULTAT :
      al = buttonsResultat;
      break;
    case HISTORIQUE :
      al = buttonsHistorique;
      break;
    case CONFIGURATION :
      al = buttonsConfiguration;
      break;
    case DESCRIPTEUR :
      al = buttonsDescripteur;
      break;
    case MDP :
      al = buttonsMdp;
      break;
    default :
      return;
  }
  for (int i = 0; i < al.size(); i++) {
        if (al.get(i).isUnderMouse(p)) {
          if (al.get(i) instanceof ButtonRect) {
            ButtonRect br = (ButtonRect)al.get(i);
            mae = br.getLink();
            if (mae == FSM.DESCRIPTEUR) ord = 200;
            ACTION act = br.getAct();
            if (act == null) return;
            if (act == ACTION.LOGIN) {
              controlAuthentification.authentification(login);
              login = "";
              loginhide = "";
            } else if (act == ACTION.INDEX) {
              controlLancerIndexation.lancerIndexation();
              nbindex++;
            } else if (act == ACTION.RECHERCHE) {
              rechercheR = controlLancerRecherche.lancerRecherche(typerech, recherche);
              recherche = "";
            } else if (act == ACTION.ENREG) {
              controlEnregistrerDansHistorique.enregistrerDansHistorique(rechercheR);
            } else if (act == ACTION.UPHISTO) {
              histoL = controlVisualiserHistorique.get();
            } else if (act == ACTION.SUPP && controlVerifierID.verifierID() == true) {
              controlEffacerHistorique.effacerHistorique();
            } else if (act == ACTION.CONFUN && valeur.length() > 0) {
              controlChangerConfiguration.changerConfiguration(3, Integer.parseInt(valeur));
              valeur = "";
            } else if (act == ACTION.CONFDEUX && valeur.length() > 0) {
              controlChangerConfiguration.changerConfiguration(4, Integer.parseInt(valeur));
              valeur = "";
            } else if (act == ACTION.CONFTROIS && valeur.length() > 0) {
              controlChangerConfiguration.changerConfiguration(5, Integer.parseInt(valeur));
              valeur = "";
            } else if (act == ACTION.CONFQUATRE && valeur.length() > 0) {
              controlChangerConfiguration.changerConfiguration(2, Integer.parseInt(valeur));
              valeur = "";
            } else if (act == ACTION.CONFCINQ && valeur.length() > 0) {
              controlChangerConfiguration.changerConfiguration(1, Integer.parseInt(valeur));
              valeur = "";
            } else if (act == ACTION.CMDP && newlog.length() > 0) {
              controlChangerMdp.changerMdp(newlog);
              newlog = "";
            }
          } else {
            ButtonCircle bc = (ButtonCircle)al.get(i);
            if (mae == FSM.RECHERCHE) {
              for (int j = 0; j < buttonsRecherche.size(); j++) {
                if (buttonsRecherche.get(j) instanceof ButtonCircle) {
                  ButtonCircle bc2 = (ButtonCircle)buttonsRecherche.get(j);
                  bc2.setActive(false);
                }
              } 
              bc.setActive(true);
              typerech = bc.getTR();
            }
          }
          break;
        }
      }
}

public void keyPressed(){
 if(mae == FSM.LOGIN) {
   if (key == ENTER || key == RETURN) {return;}
   else if (key == BACKSPACE) {
     if (login.length() == 0) return;
     loginhide = loginhide.substring(0, loginhide.length()-1);
     login = login.substring(0, login.length()-1);
   } else {
     loginhide += "*";  
     login += key;
   }
 }
 else if(mae == FSM.RECHERCHE) {
   if (key == ENTER || key == RETURN) {return;}
   else if (key == BACKSPACE) {
     if (recherche.length() == 0) return;
     recherche = recherche.substring(0, recherche.length()-1);
   } else {
     recherche += key;
   }
 }
 else if(mae == FSM.CONFIGURATION) {
   if (key == ENTER || key == RETURN) {return;}
   else if (key == BACKSPACE) {
     if (valeur.length() == 0) return;
     valeur = valeur.substring(0, valeur.length()-1);
   } else {
     valeur += key;
   }
 }
 else if(mae == FSM.MDP) {
   if (key == ENTER || key == RETURN) {return;}
   else if (key == BACKSPACE) {
     if (newlog.length() == 0) return;
     newlog = newlog.substring(0, newlog.length()-1);
   } else {
     newlog += "*";
   }
 }
}
public enum ACTION {
  INDEX,
  LOGIN,
  RECHERCHE,
  ENREG,
  UPHISTO,
  SUPP,
  CONFUN,
  CONFDEUX,
  CONFTROIS,
  CONFQUATRE,
  CONFCINQ,
  CMDP
}
public abstract class Button {
  
  private Point position;
  private int c;
  
  public Button(Point position, int c) {
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
  
  public int getColor() {
    return c;
  }
  
  public void setColor(int c) {
    this.c = c;
  }
  
}
public class ButtonCircle extends Button {
  
  private int rayon;
  private boolean active = false;
  private int longueur;
  private int largeur;
  TypeRecherche typerech;
  
  public ButtonCircle(Point position, int longueur, int largeur, int c, TypeRecherche typerech) {
    super(position, c);
    this.longueur = longueur;
    this.largeur = largeur;
    this.rayon = 4*longueur/5;
    this.typerech = typerech;
  }
  
  public ButtonCircle(Point position, int longueur, int largeur, TypeRecherche typerech) {
    this(position, longueur, largeur, 0xff3B4B76, typerech);
  }
  
  public ButtonCircle(Point position, int rayon, TypeRecherche typerech) {
    super(position, 0xff3B4B76);
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
      fill(0xffCBC3C3);
      circle(getX()+rayon/2, getY()+longueur/2, 2*rayon);
    } else {
      fill(0xff3AF523);
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
      fill(0xffCBC3C3);
    } else {
      fill(0xff3AF523);
    }
    circle(getX(), getY(), 2*getRayon());
  }
  
  public boolean isUnderMouse(Point p) {
    int x = (int)p.getX();
    int y = (int)p.getY();
    return (x-getX())*(x-getX())+(y-getY())*(y-getY()) <= getRayon()*getRayon();
  }
}
public class ButtonRect extends Button {
  
  private int longueur;
  private int largeur;
  private FSM link;
  private ACTION act;
  
  public ButtonRect(Point position, int longueur, int largeur, int c, FSM link, ACTION act) {
    super(position, c);
    this.longueur = longueur;
    this.largeur = largeur;
    this.link = link;
    this.act = act;
  }
  
  public ButtonRect(Point position, int longueur, int largeur, FSM link, ACTION act) {
    this(position, longueur, largeur, 0xff3B4B76, link, act);
  }
  
  public void update() {
    int c = getColor();
    if (isUnderMouse(new Point(mouseX, mouseY))) c = color(0xff8CA7F0);
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
public enum FSM {
  ACCUEIL,
  LOGIN,
  RECHERCHE,
  RESULTAT,
  HISTORIQUE,
  CONFIGURATION,
  DESCRIPTEUR,
  MDP
}
  public void settings() {  size(1400, 950); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "DFSearch" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
