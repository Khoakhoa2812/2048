package MenuButton;

import java.awt.*;

public abstract class MenuButton {
    private Rectangle bound;
    private Image buttonImage;
    private Toolkit t = Toolkit.getDefaultToolkit();

    public Toolkit getT() {
        return t;
    }

    public Rectangle getBound() {
        return bound;
    }

    public void setBound(Rectangle bound) {
        this.bound = bound;
    }

    public Image getButtonImage() {
        return buttonImage;
    }

    public void setButtonImage(Image buttonImage) {
        this.buttonImage = buttonImage;
    }
    public abstract void render(Graphics g);

}
