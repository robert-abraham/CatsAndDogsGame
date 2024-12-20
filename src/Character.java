
public class Character extends animal {
    private double x;
    public Character(double startX, double startY) {
        super("character.png", startX, startY, 50, 50);
        x = startX;

    }


    @Override
    public void updatelocation(double  x, double  y){
        imageView.setX(x);
        imageView.setY(y);
    }


    public void moveLeft(double amount){
        this.x = imageView.getX() - amount;
    }

    public void moveRight(double amount){
        this.x = imageView.getX() + amount;
    }


    @Override
    public void updateFrame(){
        this.updatelocation(this.x, imageView.getY());

    }

    @Override
    public boolean deletable(){
        return false;
    }
}
