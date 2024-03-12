package com.wave3.objects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Random;

import com.wave3.gameElement.HUD;
import com.wave3.gameElement.Handler;
import com.wave3.main.GameWindow;

public class FastEnemy extends GameObject{
	
	private int spawn_timer = 60;

	public FastEnemy(Handler handler) {
		super(handler);
		// TODO Auto-generated constructor stub
		id = ID.BASICENEMY;
		
		x = handler.getRandom().nextInt(GameWindow.GAMEWIDTH - 200) + 50;
		y = handler.getRandom().nextInt(GameWindow.GAMEHEIGHT - 200) + 50;
		width = 25;
		height = 25;
	}

	@Override
	public void tick() {
		if(spawn_timer > 0) {
			spawn_timer--;
			return;
		}
		if(spawn_timer == 0) {
			this.velX = (handler.getRandom().nextInt(2) * 2 - 1) * 5;
			this.velY = (handler.getRandom().nextInt(2) * 2 - 1) * 10;
			id = ID.BASICENEMY;
			spawn_timer--;
		}
		// Update the position
		x += velX;
		y += velY;

		clamp();
		
		if(hit.get("left") || hit.get("right")) velX *= -1;
		if(hit.get("up") || hit.get("down")) velY *= -1;
	}

	@Override
	public void render(Graphics2D g2d) {
		// TODO Auto-generated method stub
		g2d.setColor(Color.orange);
		if(spawn_timer > 0) {
			g2d.drawRect((int)x, (int)y, (int)width, (int)height);
		}
		else {
			g2d.fillRect((int)x, (int)y, (int)width, (int)height);
		}
	}

	@Override
	public void collision(ID id) {
		// Temporary code to remove the BasicEnemy if it hits the player
		if(id == ID.PLAYER && spawn_timer == -1) {
			HUD.health -= 5;
		}
	}
	
}