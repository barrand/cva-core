package com.bbj.cva.screenobjects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.bbj.cva.events.PlaceUnitEvent;
import com.bbj.cva.events.RemoveScreenObjectEvent;
import com.bbj.cva.model.CvaModel;
import com.bbj.cva.model.ScreenObjectStats;
import com.bbj.cva.screenobjects.interfaces.IAnimated;
import com.bbj.cva.screenobjects.interfaces.IAttackable;
import com.bbj.cva.screenobjects.interfaces.IAttacker;
import com.bbj.cva.screenobjects.interfaces.IDier;
import com.bbj.cva.screenobjects.interfaces.IHitAreaObject;
import com.bbj.cva.screenobjects.interfaces.INonAnimated;
import com.bbj.cva.screenobjects.interfaces.IScreenObject;
import com.bbj.cva.screenobjects.interfaces.IShooter;
import com.bbj.cva.screenobjects.interfaces.IWalker;
import com.bbj.cva.screenobjects.projectiles.IProjectile;
import com.bbj.cva.util.StatsMachine;

public abstract class ScreenObject implements IScreenObject {

	protected Animation walkAnim;
	protected Animation noActionAnim;
	protected Animation dieAnim;
	protected Animation attackAnim;
	protected Animation currentAnim;

	protected ScreenObjectStats stats;

	protected Texture texture; // used in non animated objects instead of the
								// animations
	protected TextureAtlas textureAtlas;

	SpriteBatch spriteBatch; // #6
	AtlasRegion currentFrame; // #7
	public boolean loop = true;

	protected Rectangle hitArea;

	protected boolean alreadyShot = false;
	protected boolean alreadyAttacked = false;

	protected int health;
	protected float currentSpeedX;
	protected float currentSpeedY;

	protected Rectangle attackArea;// the "range" area where this character can
									// launch into its melee attack
	protected Animation attackingAnim;

	public boolean checkForInteractions = true;
	public CvaModel.ActionState actionState;
	public CvaModel.Unit type;
	public float x, y;

	ParticleEffect effect;
	int emitterIndex;
	Array<ParticleEmitter> emitters;
	int particleCount = 10;
	float fpsCounter;
	InputProcessor inputProcessor;

	float stateTime; // #8

	public ScreenObject(float x, float y, CvaModel.Unit type) {
		this.type = type;
		this.actionState = getInitActionState();
		stats = StatsMachine.getStatsByType(type);
		health = stats.maxHealth;
		stateTime = 0f;

		this.x = x;
		this.y = y;

		if (this instanceof IAttacker) {
			attackArea = new Rectangle();
			attackArea.width = stats.attackAreaWidth;
			attackArea.height = stats.attackAreaHeight;
			attackArea.x = x;
			attackArea.y = y;
		}

		if (this instanceof IHitAreaObject) {
			hitArea = new Rectangle();
			hitArea.width = stats.hitAreaWidth;
			hitArea.height = stats.hitAreaHeight;
			hitArea.x = x;
			hitArea.y = y;
		}
	}

	public void create() {
		goToNormalState();

		effect = new ParticleEffect();
		effect.load(Gdx.files.internal("data/particles/explosion.p"),
				Gdx.files.internal("data/particles"));
		// Of course, a ParticleEffect is normally just used, without messing
		// around with its emitters.
		emitters = new Array(effect.getEmitters());
		effect.getEmitters().clear();
		effect.getEmitters().add(emitters.get(0));

	}

	public void render(SpriteBatch spriteBatch) {
		x += currentSpeedX;
		y += currentSpeedY;

		float delta = Gdx.graphics.getDeltaTime();

		// todo make this better. right now it just removes the object if it is
		// way off the stage
		if (x >= 2000 || x < -500) {
			CvaModel.eventBus.post(new RemoveScreenObjectEvent(
					(ScreenObject) this));
		}

		currentFrame = null;

		// if this is an animated object, get the current frame of the current
		// animation
		if (this instanceof IAnimated) {
			stateTime += Gdx.graphics.getDeltaTime();
			currentFrame = (AtlasRegion) currentAnim.getKeyFrame(stateTime,
					loop);
		}

		// if this object should interact with the other objects on the stage,
		// check through those objects for collisions then do the appropriate
		// actions
		if (this instanceof IHitAreaObject) {
			hitArea.x = x - hitArea.width / 2;
			hitArea.y = y;
			// draw a rectangle if we are in debug mode so we can see where the
			// hit areas are
			if (CvaModel.DEBUG) {
				spriteBatch.setColor(50, 10, 20, 220);
				spriteBatch
						.draw(CvaModel.blue, hitArea.x, hitArea.y,
								hitArea.width, hitArea.height, 0, 0, 0, 0,
								false, false);
				spriteBatch.setColor(CvaModel.defaultColor);
			}

			// only look for interactions if we should and we have things to
			// interact with.
			if (checkForInteractions
					&& ((IHitAreaObject) this).getInteractables() != null) {

				int hitCount = 0;
				// loop through the interactable objects and see if there are
				// ones to interact with which that this object is touching
				for (IHitAreaObject o : ((IHitAreaObject) this)
						.getInteractables()) {
					if (((ScreenObject) o).checkForInteractions
							&& ((ScreenObject) o).hitArea.overlaps(hitArea)) {
						// if we find something then handle the collision of
						// whatever it is
						hitCount++;
						((IHitAreaObject) this).handleCollision(o);
					}
				}

				// if this object isn't running into anything and we aren't in
				// our normal state, then we should go back to our normal state
				if (actionState != getInitActionState() && hitCount == 0) {
					goToNormalState();
				}
			}
		}

		// if we are an attacker, then update the attack area
		if (this instanceof IAttacker) {
			if (stats.attacksToTheLeft) {
				attackArea.x = x - attackArea.width;
			} else {
				attackArea.x = x + attackArea.width;
			}
			attackArea.y = y;
			if (CvaModel.DEBUG) {
				spriteBatch.setColor(255, 0, 0, 200);
				spriteBatch.draw(CvaModel.blue, attackArea.x, attackArea.y,
						attackArea.width, attackArea.height, 0, 0, 0, 0, false,
						false);
				spriteBatch.setColor(CvaModel.defaultColor);
			}
		}

		// if it is an animated object then do the necessary stuff like drawing
		// the current frame. This works independent of the action state
		if (this instanceof IAnimated) {
			if (!loop && currentAnim.isAnimationFinished(stateTime)) {
				onAnimationEnd();
			}
			spriteBatch.draw(currentFrame, x + currentFrame.offsetX
					- currentFrame.originalWidth / 2, y);

			effect.draw(spriteBatch, delta);

		} else if (this instanceof INonAnimated) {
			spriteBatch.draw(texture, x - texture.getWidth() / 2, y);
		}

		// shoot a projectile if we are a shooter
		if (this instanceof IShooter
				&& actionState == CvaModel.ActionState.SHOOTING) {
			// only shoot once per animation cycle and reset when going to a
			// different animation
			AtlasRegion shootingFrame = ((IShooter) this).getShootingFrame();
			if (currentFrame == shootingFrame && !alreadyShot) {
				ScreenObject projectile = (ScreenObject) ((IShooter) this)
						.getProjectile();
				CvaModel.eventBus.post(new PlaceUnitEvent(projectile));
				alreadyShot = true;
			} else if (alreadyShot && currentFrame != shootingFrame) {
				alreadyShot = false;
			}
		}
	}

	public void handleCollision(IHitAreaObject o) {
		// handle the case if we were hit by a projectile
		if (o instanceof IProjectile) {
			Gdx.app.log("cva", this.toString());
			IProjectile projectile = (IProjectile) o;
			takeDamage(projectile.getDamage(), ((ScreenObject) projectile).x,
					((ScreenObject) projectile).y);

			// remove the projectile since it already hit something
			// maybe we could do projectiles that go through all offense?
			// in that case, we wouldn't want to remove the object here
			CvaModel.eventBus
					.post(new RemoveScreenObjectEvent((ScreenObject) o));
		}

		// if we are an attacker and we ran into something to attack
		if (o instanceof IAttackable && this instanceof IAttacker) {
			// switch to attacking state if we already aren't there
			if (actionState != CvaModel.ActionState.ATTACKING) {
				startAttacking((IAttackable) o);
			}
			// if we are an attacker, and we get to an attack frame, DO SOME
			// DAMAGE!! only attack once per animation cycle and reset when
			// going to a different animation
			AtlasRegion attackFrame = ((IAttacker) this).getAttackingFrame();
			if (currentFrame == attackFrame && !alreadyAttacked) {
				// attack the object with our attack strength, if we kill it
				// then we need to go back to normal
				((ScreenObject) o).takeDamage(stats.attackStrength, 20, 20);
				alreadyAttacked = true;
			} else if (alreadyAttacked && currentFrame != attackFrame) {
				alreadyAttacked = false;
			}
		}
	}

	abstract protected void goToNormalState();

	/**
	 * sometimes the attacking object needs to know if the damage killed the
	 * thing so they can go back to walking or whatever
	 * 
	 * @param damage
	 * @return
	 */
	public void takeDamage(int damage, float x, float y) {
		health -= damage;
		effect.setPosition(x, y);
		ParticleEmitter emitter = emitters.get(emitterIndex);
		particleCount += 100;
		System.out.println(particleCount);
		particleCount = Math.max(0, particleCount);
		if (particleCount > emitter.getMaxParticleCount())
			emitter.setMaxParticleCount(particleCount * 2);
		emitter.getEmission().setHigh(
				particleCount / emitter.getLife().getHighMax() * 1000);
		effect.getEmitters().clear();
		effect.getEmitters().add(emitter);

		emitters = new Array(effect.getEmitters());
		effect.getEmitters().add(emitters.get(0));
		emitters.get(0).start();

		if (health <= 0) {
			startDying();
		}
	}

	protected void startProjectiling() {
		currentSpeedX = stats.baseSpeedX;
	}

	protected void startWalking() {
		stateTime = 0f;
		actionState = CvaModel.ActionState.WALKING;
		currentAnim = ((IWalker) this).getWalkAnimation();
		currentSpeedX = stats.baseSpeedX;
	}

	protected void startShooting() {
		actionState = CvaModel.ActionState.SHOOTING;
		currentAnim = ((IShooter) this).getShootingAnimation();
	}

	protected void startAttacking(IAttackable o) {
		actionState = CvaModel.ActionState.ATTACKING;
		currentAnim = ((IAttacker) this).getAttackAnimation();
		currentSpeedX = 0f;
	}

	protected void startDying() {
		actionState = CvaModel.ActionState.DYING;
		checkForInteractions = false;
		stateTime = 0f;
		currentAnim = ((IDier) this).getDieAnimation();
		currentSpeedX = 0f;
		loop = false;
	}

	protected void onAnimationEnd() {
		// override me if needed
	}

	public int getDamage() {
		return stats.attackStrength;
	}

	public void destroy() {
		// spriteBatch.dispose();
		effect.dispose();
	}

	abstract public CvaModel.ActionState getInitActionState();
}
