package com.example.carracinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Rect;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView playerCar;
    ImageView[] enemyCars;

    FrameLayout gameLayout;

    Handler handler = new Handler();
    Runnable runnable;

    int speed = 12;
    boolean gameOver = false;

    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gameLayout = findViewById(R.id.gameLayout);
        playerCar = findViewById(R.id.playerCar);

        enemyCars = new ImageView[]{
                findViewById(R.id.enemyCar1),
                findViewById(R.id.enemyCar2),
                findViewById(R.id.enemyCar3)
        };

        startGame();
        enablePlayerMovement();
    }

    private void startGame() {

        runnable = new Runnable() {
            @Override
            public void run() {

                if (!gameOver) {

                    for (ImageView enemy : enemyCars) {

                        enemy.setY(enemy.getY() + speed);

                        if (enemy.getY() > gameLayout.getHeight()) {
                            resetEnemy(enemy);
                        }

                        checkCollision(enemy);
                    }

                    handler.postDelayed(this, 30);
                }
            }
        };

        handler.postDelayed(runnable, 30);
    }

    private void resetEnemy(ImageView enemy) {

        enemy.setY(-200);

        int maxX = gameLayout.getWidth() - enemy.getWidth();
        int randomX = random.nextInt(Math.max(maxX, 1));

        enemy.setX(randomX);
    }

    private void enablePlayerMovement() {

        gameLayout.setOnTouchListener((v, event) -> {

            if (!gameOver) {
                float touchX = event.getX();
                playerCar.setX(touchX - playerCar.getWidth() / 2);
            }

            v.performClick();
            return true;
        });
    }

    private void checkCollision(ImageView enemy) {

        Rect enemyRect = new Rect();
        enemy.getHitRect(enemyRect);

        Rect playerRect = new Rect();
        playerCar.getHitRect(playerRect);

        shrinkRect(enemyRect, 0.2f);
        shrinkRect(playerRect, 0.2f);

        if (Rect.intersects(enemyRect, playerRect)) {
            gameOver();
        }
    }

    private void gameOver() {

        gameOver = true;
        handler.removeCallbacks(runnable);

        new AlertDialog.Builder(this)
                .setTitle("Game Over")
                .setMessage("You crashed!")
                .setCancelable(false)
                .setPositiveButton("OK", null)
                .show();
    }

    private void shrinkRect(Rect rect, float percent) {

        int shrinkX = (int) (rect.width() * percent / 2);
        int shrinkY = (int) (rect.height() * percent / 2);

        rect.left += shrinkX;
        rect.right -= shrinkX;
        rect.top += shrinkY;
        rect.bottom -= shrinkY;
    }
}
