import java.awt.Color;
import java.awt.geom.Line2D;
import java.awt.Point;
import java.awt.BasicStroke;

public class RoadGraphic {

  int	x1;
  int	x2;
  int	y1;
  int	y2;
    private Line2D	line;
    private BasicStroke	stroke;

    public RoadGraphic(int x1, int y1, int x2, int y2) {
	this.line = new Line2D.Float(x1, y1, x2, y2);
	this.stroke = new BasicStroke(MapPanel.unScaleX(5));
	this.setCoord(x1, y1, x2, y2);
    }

    //*********
    // GETTERS
    //*********

    public int		getRealx1() {
      return x1;
    }
    public int		getRealx2() {
      return x2;
    }
    public int		getRealy1() {
      return y1;
    }
    public int		getRealy2() {
      return y2;
    }

    public int		getx1() {
	return (int)this.line.getX1();
    }
    public int		getx2() {
	return (int)this.line.getX2();
    }
    public int		gety1() {
	return (int)this.line.getY1();
    }
    public int		gety2() {
	return (int)this.line.getY2();
    }

    public BasicStroke	getStroke() {
	return this.stroke;
    }

    //*********
    // SETTERS
    //*********

    public void		setCoord(int x1, int y1, int x2, int y2) {
      this.line.setLine(x1, y1, x2, y2);
      this.stroke = new BasicStroke(MapPanel.unScaleX(5));
    }
    public void		setRealCoord(int x1, int y1, int x2, int y2) {
      this.x1 = x1;
      this.x2 = x2;
      this.y1 = y1;
      this.y2 = y2;
    }

    //********
    // OTHERS
    //********

    public double	containsPoint(int x, int y) {

	Point		P = new Point(x, y);
	Point		debS = new Point(this.getx1(), this.gety1());
	Point		finS = new Point(this.getx2(), this.gety2());
    	// Definit le vecteur AP
    	double a = P.x - debS.x;
    	double b = P.y - debS.y;

    	// Definit le vecteur AB 
    	double c = finS.x - debS.x;
    	double d = finS.y - debS.y;
    	double produitScalaire = (a * c) + (b * d);
    	double longueur_seg = Math.sqrt(Math.pow(finS.x - debS.x, 2)
					+ Math.pow(finS.y - debS.y, 2));
    	double param = produitScalaire / longueur_seg;
    	double res = Math.sqrt(Math.pow(P.x - debS.x, 2)
			       + Math.pow(P.y - debS.y, 2));
    	double resultat = Math.sqrt(Math.pow(res,2) - Math.pow(param,2));
	return resultat;
    }

} 
