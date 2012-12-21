package beyondthepail;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;

public class GameOverScreen implements Screen {

    PailGame game;
    Camera camera;
    SpriteBatch batch;
    BitmapFont font;
    
    public GameOverScreen(PailGame game) {
        this.game = game;
        this.camera = game.camera;
        
        font = new BitmapFont(); // built-in arial-15
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        game.playScreen.render(delta);

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
            ((PlayScreen)game.playScreen).reset();
            game.setScreen(game.playScreen);
        }
        
        batch.begin();
        centerText("Game Over\nYou stained the carpet!\nCaught: "+
                ((PlayScreen)game.playScreen).dropsCaught +
                " droplets\n"+
                "Touch or press SPACE to try again");
        batch.end();
        
    }

    protected void centerText(String text) {
        TextBounds bounds = font.getBounds(text);
        font.drawMultiLine(batch, text,
                (camera.viewportWidth - bounds.width)/2,
                (camera.viewportHeight - bounds.height)/2);
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void show() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void dispose() {
    }

}
