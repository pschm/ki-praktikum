package svgReader;

public class Line {
    public int y1, x1, y2, x2;

    public Line(int y1, int x1, int y2, int x2){
        this.y1 = Math.min(y1, y2);
        this.x1 = Math.min(x1, x2);
        this.y2 = Math.max(y1, y2);
        this.x2 = Math.max(x1, x2);
    }


    public String toString(){
        return "y1: " +y1 + " x1: "+ x1 + " y2: " + y2 + " x2: " + x2;
    }


    public boolean containsHorizontalLine(Line l) {
        //check if both lines are horizontal and on the same height
        if((this.y1 == this.y2) && (l.y1 == l.y2) && (this.y1 == l.y1)){
            //check, weather this line contains l
            if(this.x1 <= l.x1 && l.x2 <= this.x2)
                return true;
        }
        return false;
    }


}
