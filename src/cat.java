public class cat extends animal {
    private static final double GROUND_LEVEL = 400; // y floor level

    public cat(double startX, double startY) {
        super("cat.png", startX, startY, 75, 75); 
    }

    @Override
    public void updatelocation(double  x, double  y){
        imageView.setX(x);
        imageView.setY(y);
    }

    @Override
    public void updateFrame(){
        this.updatelocation(imageView.getX(), imageView.getY()+2);// adjust drop speed by increasing number

    }

    @Override
    public boolean deletable(){ // checks if the 
        if(imageView.getY() >= GROUND_LEVEL){
            return true;
        }
        else{
            return false;
        }
    }


}