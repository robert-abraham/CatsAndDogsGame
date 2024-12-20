import javafx.geometry.Bounds;
import javafx.scene.image.ImageView;

public abstract class animal {
    protected ImageView imageView;
    protected double width;
    protected double height;

    public animal(String imagePath, double startX, double startY, double w, double h) {
        //Resize Icon Perserving starting ratio
        imageView = new ImageView(imagePath);
        imageView.setFitWidth(w);
        imageView.setFitHeight(h);
        imageView.setPreserveRatio(true);
        imageView.setX(startX);
        imageView.setY(startY);
        this.width = imageView.getFitWidth();   
        this.height = imageView.getFitHeight(); 
    }

    //change the current x and y of any object
   public abstract void updatelocation(double x, double y);

   //ran in the animation timer update object location x,y
   public abstract void updateFrame();
   
   //informas main program when an object is deletable
   public abstract boolean deletable(); 

    //image view is the object type recieved by the pane so this is used in multiple places
    public ImageView getView() {
        return imageView;
    }

    
    public boolean checkCollision(Character player) {
        Bounds myBounds = imageView.getBoundsInParent();
        Bounds playerBounds = player.imageView.getBoundsInParent();
        return myBounds.intersects(playerBounds);
    }

}
