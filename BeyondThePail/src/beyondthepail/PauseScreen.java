package beyondthepail;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFont.TextBounds;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PauseScreen implements Screen {

    PailGame game;
    BitmapFont font;
    SpriteBatch batch;
    Camera camera;
    
    public PauseScreen(PailGame game) {
        this.game = game;
        this.camera = game.camera;
        font = new BitmapFont(); // built-in font arial-15
        batch = new SpriteBatch();
    }

    @Override
    public void render(float delta) {
        game.playScreen.render(delta);

        if (Gdx.input.isTouched() || Gdx.input.isKeyPressed(Keys.SPACE)) {
            resume();
        }
        
        batch.begin();
        centerText("PAUSED\nTouch or press SPACE to play");
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
        game.setScreen(game.playScreen);
    }

    @Override
    public void dispose() {
    }

}
