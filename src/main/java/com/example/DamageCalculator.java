package com.example;

public class DamageCalculator {
	private String attackerName;
	private String defenderName;
	private int attack;
	private int defence;
	private int armorer;
	private int offence;
	private int archery;
	private int offenseHeroLevel;
	private int archeryHeroLevel;
	private int armorerHeroLevel;
	private boolean isRanged;
	private double minDamage;
	private double maxDamage;
	private int creatureNumber;

	public DamageCalculator(String attackerName, String defenderName, int attack,
			int defence, int armorer, int offence, int archery, int offenseHeroLevel, int archeryHeroLevel,
			int armorerHeroLevel, boolean isRanged, double minDamage, double maxDamage,
			int creatureNumber) {
		this.attackerName = attackerName;
		this.defenderName=defenderName;
		this.attack = attack;
		this.defence = defence;
		this.armorer = armorer;
		this.offence = offence;
		this.archery = archery;
		this.offenseHeroLevel = offenseHeroLevel;
		this.archeryHeroLevel = archeryHeroLevel;
		this.armorerHeroLevel = armorerHeroLevel;
		this.isRanged = isRanged;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.creatureNumber = creatureNumber;
	}

	private int[] fianlCalculate(boolean allShots, int shotCount) {

		if (attackerName.equals("Behemoth")) {
			if ((defence * 3) % 5 > 0)
				defence = defence * 3 / 5;
			else
				defence = defence * 3 / 5 - 1;
		} else if (attackerName.equals("AncientBehemoth")) {
			if (defence % 5 > 0)
				defence = defence / 5;
			else
				defence = defence / 5 - 1;
		} else if (defenderName.equals("HalflingGrenader"))
			defence = defence * 8 / 10;
		if (defenderName.equals("Nix"))
			attack = attack * 7 / 10;
		else if (defenderName.equals("NixWarrior"))
			attack = attack * 4 / 10;

		double attackDefenceDifferenceBonus = 1;
		if (attack >= defence) {
			if (attack - defence >= 60)
				attackDefenceDifferenceBonus += 3;
			else
				attackDefenceDifferenceBonus += (attack - defence) * 0.05;
		} else {
			if (defence - attack >= 28)
				attackDefenceDifferenceBonus -= 0.7;
			else
				attackDefenceDifferenceBonus -= (defence - attack) * 0.025;
		}

		double offenceBonus = 1;
		double archeryBonus = 1;
		if (isRanged == false) {
			switch (offence) {
				case 0:
					break;
				case 1:
					offenceBonus = 1.1;
					break;
				case 2:
					offenceBonus = 1.2;
					break;
				case 3:
					offenceBonus = 1.3;
			}
			offenceBonus += offenseHeroLevel * (offenceBonus - 1) * 0.05;
		} else {
			switch (archery) {
				case 0:
					break;
				case 1:
					archeryBonus = 1.1;
					break;
				case 2:
					archeryBonus = 1.25;
					break;
				case 3:
					archeryBonus = 1.5;
			}
			archeryBonus += archeryHeroLevel * (archeryBonus - 1) * 0.05;
		}

		double armorerBonus = 1;
		switch (armorer) {
			case 0:
				break;
			case 1:
				armorerBonus = 0.95;
				break;
			case 2:
				armorerBonus = 0.9;
				break;
			case 3:
				armorerBonus = 0.85;
		}
		armorerBonus -= armorerHeroLevel * (1 - armorerBonus) * 0.05;

		minDamage = (minDamage * attackDefenceDifferenceBonus * offenceBonus *
				archeryBonus * armorerBonus
				* creatureNumber);
		maxDamage = (maxDamage * attackDefenceDifferenceBonus * offenceBonus *
				archeryBonus * armorerBonus
				* creatureNumber);
		if (minDamage == 0)
			minDamage = 1;
		if (maxDamage == 0)
		maxDamage = 1;
		if(allShots)
		return new int[] {(int) minDamage*shotCount, (int) maxDamage*shotCount};
		return new int[] { (int) minDamage, (int) maxDamage };
	}

	private void calculateMelee(Creature attacker) {
		if (attacker.hasMeleePenalty) {
			this.minDamage /= 2;
			this.maxDamage /= 2;
		}
	}

	private void calculateDoubleShooting() {
		this.minDamage *= 2;
		this.maxDamage *= 2;
	}

	private void calculateHateBonus() {
		this.minDamage = this.minDamage * 3 / 2;
		this.maxDamage = this.maxDamage * 3 / 2;
	}

	private void calculateOppositeElementalBonus() {
		this.minDamage = this.minDamage * 2;
		this.maxDamage = this.maxDamage * 2;
	}

	private void mindSpellImmunityPenalty(Creature defender) {
		if (defender.isMindSpellImmune) {
			this.minDamage /= 2;
			this.maxDamage /= 2;
		}
	}

	private void calculateBless(Creature attacker){
		this.maxDamage+=1;
		this.minDamage=maxDamage;
	}
	private void calculateCurse(Creature attacker){
		this.minDamage-=1;
		this.maxDamage=minDamage;
	}
	private void calculateJoustingBonus(Creature attacker, Creature defender, int joustingSteps){
		if(defender.name.equals("Pikeman") || defender.name.equals("Halberdier"))
		return;
		this.minDamage*=0.05*joustingSteps+1;
		this.maxDamage*=0.05*joustingSteps+1;
	}

	public int[] calculate(Creature attacker, Creature defender, boolean allShots, boolean advancedBless, boolean advancedCurse, int joustingSteps) {
		calculateJoustingBonus(attacker, defender, joustingSteps);
		if(advancedBless)
		calculateBless(attacker);
		else if(advancedCurse)
		calculateCurse(attacker);
		if (this.isRanged==false)
			calculateMelee(attacker);
		else if (attacker.isDoubleShooting)
			calculateDoubleShooting();
		if (attacker.hates(defender))
			calculateHateBonus();
		if (attacker.isOppositeElemental(defender))
			calculateOppositeElementalBonus();
		if (attacker.name.equals("PsychicElemental") ||
				attacker.name.equals("MagicElemental"))
			mindSpellImmunityPenalty(defender);
		return this.fianlCalculate(allShots, attacker.shotCount);
	}
}