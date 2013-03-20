package com.bbj.cva.screenobjects.interfaces;

/**
 * Anything that the melee attackers can actually attack. I dont' think we need
 * to subclass this since the list of things cheer's interact with should handle
 * the different sets of objects so we don't have cheers checking to see if
 * there are objects to interact with that they shouldn't
 * 
 * @author bbarrand12
 * 
 */
public interface IAttackable extends IHitAreaObject {
}
