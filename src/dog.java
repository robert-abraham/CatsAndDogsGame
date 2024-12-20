public class dog extends animal {
    private boolean bounced = false;
    private boolean bounce_finish = false;

    private double velocityY = 0;

    private static final double GRAVITY = 0.02; //Drop velocity speed
    private static final double GROUND_LEVEL = 400; // y floor level

    public dog(double startX, double startY) {
        super("dog.png", startX, startY, 100, 100);
    }

    @Override
    public void updatelocation(double x, double y) {
        imageView.setX(x);
        imageView.setY(y);
    }

    @Override
    public void updateFrame() {
        // Apply gravity
        velocityY += GRAVITY;
        
        double newX = imageView.getX();
        double newY = imageView.getY() + velocityY;

        // Check if the dog hits the ground
        if (newY + imageView.getFitHeight() >= GROUND_LEVEL) {
            if (!bounced) {
                // First time hitting the ground, bounce
                newY = GROUND_LEVEL - imageView.getFitHeight();
                velocityY = -velocityY * 0.5;
                bounced = true;
            } else {
                // After bouncing once, land and stop moving
                newY = GROUND_LEVEL - imageView.getFitHeight();
                velocityY = 0;
                this.bounce_finish = true;
            }
        }

        this.updatelocation(newX, newY);
    }
    @Override
    public boolean deletable(){
        return this.bounce_finish;
    }
}
