package freezemonsters.sprite;

import freezemonsters.FreezeMonstersConfig;
import spriteframework.Direction;
import spriteframework.sprite.BadSprite;
import javax.swing.ImageIcon;
import java.awt.*;

public class Ray extends BadSprite {
    private Direction direction = Direction.RIGHT;
    private int speed =  6;// Velocidade do raio

    public Ray() {
    }

    public Ray(int x, int y, Direction direction) {
        this.direction = direction;
        initRay(x, y);
    }

    private void initRay(int x, int y) {
        String rayImg = "/ray.png";
        ImageIcon ii = new ImageIcon(getClass().getResource(rayImg));

        Image originalImage = ii.getImage();

        // Redimensione a imagem
        Image scaledImage = originalImage.getScaledInstance(FreezeMonstersConfig.getInstance().getRAY_WIDTH(), FreezeMonstersConfig.getInstance().getRAY_HEIGHT(), Image.SCALE_SMOOTH);

        setImage(scaledImage);

        // Posiciona o raio na frente do Woody, baseado na direção
        int offset = 20;
        switch (direction) {
            case UP:
                setX(x + offset); // Centralizado horizontalmente
                setY(y - 30); // Acima do Woody
                break;
            case DOWN:
                setX(x + offset);
                setY(y + 50); // Abaixo do Woody
                break;
            case LEFT:
                setX(x - 30);
                setY(y + offset); // Centralizado verticalmente
                break;
            case RIGHT:
                setX(x + 50);
                setY(y + offset);
                break;
        }
    }

    public void move() {
        // Movimento baseado na direção
        switch (direction) {
            case UP:
                setY(getY() - speed);
                break;
            case DOWN:
                setY(getY() + speed);
                break;
            case LEFT:
                setX(getX() - speed);
                break;
            case RIGHT:
                setX(getX() + speed);
                break;
        }
    }

    public boolean isOutOfBounds(int boardWidth, int boardHeight) {
        // Verifica se o raio saiu da tela
        return getX() < 0 || getX() > boardWidth ||
                getY() < 0 || getY() > boardHeight;
    }

    public boolean isCollidingWith(Monsters monster) {
        if (this.isVisible() && monster.isVisible()) {
            int rayX = this.getX();
            int rayY = this.getY();
            int monsterX = monster.getX();
            int monsterY = monster.getY();
            int monsterWidth = monster.getImage().getWidth(null);
            int monsterHeight = monster.getImage().getHeight(null);

            return (rayX >= monsterX) &&
                    (rayX <= (monsterX + monsterWidth)) &&
                    (rayY >= monsterY) &&
                    (rayY <= (monsterY + monsterHeight));
        }
        return false;
    }
}