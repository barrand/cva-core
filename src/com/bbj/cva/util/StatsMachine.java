package com.bbj.cva.util;

import com.bbj.cva.model.CvaModel;
import com.bbj.cva.model.ScreenObjectStats;

public class StatsMachine {

	public static ScreenObjectStats getStatsByType(CvaModel.Unit type) {
		ScreenObjectStats stats = new ScreenObjectStats();
		switch (type) {
		case POM:
			stats.maxHealth = 100;
			stats.attackStrength = 100;
			stats.baseSpeedX = -1.2f;
			stats.baseSpeedY = 0f;
			stats.spriteWidth = 300;
			stats.spriteHeight = 300;
			stats.hitAreaWidth = 80;
			stats.hitAreaHeight = 130;
			stats.attackAreaWidth = 100;
			stats.attackAreaHeight = 130;
			break;
		case BOLA_SHOT:
			stats.maxHealth = 0;
			stats.attackStrength = 50;
			stats.baseSpeedX = 8;
			stats.baseSpeedY = 0;
			stats.spriteWidth = 40;
			stats.spriteHeight = 40;
			stats.hitAreaWidth = 40;
			stats.hitAreaHeight = 40;
			stats.attackAreaWidth = 0;
			stats.attackAreaHeight = 0;
			break;
		case SPIDER:
			stats.maxHealth = 150;
			stats.attackStrength = 0;
			stats.baseSpeedX = 0f;
			stats.baseSpeedY = 0f;
			stats.spriteWidth = 190;
			stats.spriteHeight = 140;
			stats.hitAreaWidth = 200;
			stats.hitAreaHeight = 100;
			stats.attackAreaWidth = 0;
			stats.attackAreaHeight = 0;
			break;
		default:
			break;

		}
		return stats;
	}
}