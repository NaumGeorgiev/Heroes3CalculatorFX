package com.example;

import java.util.Set;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;

public class Creature {

    public String name;
    public int attack;
    public int defence;
    public double minDamage;
    public double maxDamage;
    public int health;
    public boolean isRanged;
    public boolean hasMeleePenalty;
    public Set<String> hatedCreatureNames;
    public Set<String> oppositeElementalNames;
    public boolean isMindSpellImmune;
    public boolean isDoubleShooting;
    public int shotCount;

    public Creature(String name, int attack, int defence, double minDamage, double maxDamage, int health,
            boolean isRanged, boolean hasMeleePenalty, Set<String> hatedCreatureNames,
            Set<String> oppositeElementalNames,
            boolean isMindSpellImmune, boolean isDoubleShooting, int shotCount) { // Update
        this.name = name;
        this.attack = attack;
        this.defence = defence;
        this.minDamage = minDamage;
        this.maxDamage = maxDamage;
        this.health = health;
        this.isRanged = isRanged;
        this.hasMeleePenalty = hasMeleePenalty;
        this.hatedCreatureNames = hatedCreatureNames;
        this.oppositeElementalNames = oppositeElementalNames;
        this.isMindSpellImmune = isMindSpellImmune;
        this.isDoubleShooting = isDoubleShooting;
        this.shotCount = shotCount;
    }
    public boolean hates(Creature defender){
        return this.hatedCreatureNames.contains(defender.name);
    }
    public boolean isOppositeElemental(Creature defender){
        return this.oppositeElementalNames.contains(defender.name);
    }
    public void setRanged(boolean isRanged){
        this.isRanged=isRanged;
        if(this.isDoubleShooting)
        this.isDoubleShooting=false;
    }

    public static Creature[] createAll() {
        File csvFile = new File("H3Units.csv");
        Creature[] creatures = new Creature[175];
        int i = 0;
        try {
            BufferedReader reader = new BufferedReader(new FileReader(csvFile));
            String creatureInfo = reader.readLine();
            
            while (true) {
                creatureInfo = reader.readLine();
                if (creatureInfo.isEmpty()) {
                    reader.close();
                    break;
                }
                String[] stats = creatureInfo.split(",");
                boolean isRanged = creatureInfo.contains("Ranged");;
                creatures[i] = new Creature(stats[0], Integer.valueOf(stats[3]), Integer.valueOf(stats[4]),
                        Integer.valueOf(stats[5]), Integer.valueOf(stats[6]), Integer.valueOf(stats[7]),
                        isRanged,
                        hasMeleePenalty(creatureInfo), hatedCreatureNames(stats[0]), oppositeElementalsNames(stats[0]),
                        isMindSpellImmune(creatureInfo), isDoubleShooting(creatureInfo, isRanged), getShotCount(creatureInfo, isRanged));
                        i++;
                        
            }
            creatures[i] = new Creature("Nymph", 5, 2, 1, 2, 4, false, false, null, null, false, false, 0);
			creatures[i + 1] = new Creature("Oceanid", 6, 2, 1, 3, 4, false, false, null, null, false, false, 0);
			creatures[i + 2] = new Creature("CrewMate", 7, 4, 2, 4, 15, false, false, null, null, false, false, 0);
			creatures[i + 3] = new Creature("Seaman", 8, 6, 3, 4, 15, false, false, null, null, false, false, 0);
			creatures[i + 4] = new Creature("Pirate", 8, 6, 3, 7, 15, true, false, null, null, false, false, 4);
			creatures[i + 5] = new Creature("Corasair", 10, 8, 3, 7, 15, true, false, null, null, false, false, 4);
			creatures[i + 6] = new Creature("SeaDog", 12, 11, 3, 7, 15, true, false, null, null, false, false, 12);
			creatures[i + 7] = new Creature("Stormbird", 10, 8, 6, 9, 30, false, false, null, null, false, false, 0);
			creatures[i + 8] = new Creature("Ayssid", 11, 8, 6, 10, 30, false, false, null, null, false, false, 0);
			creatures[i + 9] = new Creature("SeaWitch", 12, 7, 10, 14, 35, true, true, null, null, false, false, 0);
			creatures[i + 10] = new Creature("Sorceress", 12, 9, 10, 16, 35, true, true, null, null, false, false, 0);
			creatures[i + 11] = new Creature("Nix", 13, 16, 18, 22, 80, false, false, null, null, false, false, 0);
			creatures[i + 12] = new Creature("NixWarrior", 14, 17, 18, 22, 90, false, false, null, null, false, false, 0);
			creatures[i + 13] = new Creature("SeaSerpent", 22, 16, 30, 55, 180, false, false, null, null, false, false, 0);
			creatures[i + 14] = new Creature("Haspid", 29, 20, 30, 55, 300, false, false, null, null, false, false, 0);
            creatures[i + 15] = new Creature("Leprechaun", 8, 5, 3, 5, 15, false, false, null, null, false, false, 0);
            creatures[i+16]=new Creature("Satyr", 10, 11, 6, 10, 35, false, false, null, null, false, false, 0);
            creatures[i+17]=new Creature("SteelGolem", 10, 11, 6, 8, 45, false, false, null, null, false, false, 0);
            creatures[i+18]=new Creature("Fangarm", 12, 12, 8, 12, 50, false, false, null, null, true, false, 0);
            creatures[i+19]=new Creature("HalflingGrenader", 5, 2, 2, 3, 4, true, true, null, null, false, false, 24);
            creatures[i+20]=new Creature("Mechanic", 6, 5, 2, 4, 14, false, false, null, null, false, false, 0);
            creatures[i+21]=new Creature("Engineer", 7, 5, 2, 5, 16, false, false, null, null, false, false, 0);
            creatures[i+22]=new Creature("Armadillo", 5, 10, 3, 5, 25, false, false, null, null, false, false, 0);
            creatures[i+23]=new Creature("BellwetherArmadillo", 6, 11, 3, 5, 25, false, false, null, null, false, false, 0);
            creatures[i+24]=new Creature("Automaton", 12, 10, 7, 7, 30, false, false, null, null, false, false, 0);
            creatures[i+25]=new Creature("SentinelAutomaton", 12, 10, 9, 9, 30, false, false, null, null, false, false, 0);
            creatures[i+26]=new Creature("Sandworm", 13, 12, 12, 16, 50, false, false, null, null, false, false, 0);
            creatures[i+27]=new Creature("Olgoi-KhorKhoi", 15, 12, 12, 16, 60, false, false, null, null, false, false, 0);
            creatures[i+28]=new Creature("Gunslinger", 17, 12, 14, 24, 45, true, true, null, null, false, false, 16);
            creatures[i+29]=new Creature("BountyHunter", 18, 14, 14, 24, 45, true, true, null, null, false, false, 24);
            creatures[i+30]=new Creature("Couatl", 17, 17, 25, 45, 160, false, false, null, null, false, false, 0);
            creatures[i+31]=new Creature("CrimsonCouatl", 21, 21, 25, 45, 200, false, false, null, null, false, false, 0);
            creatures[i+32]=new Creature("Dreadnought", 18, 20, 40, 50, 200, false, false, null, null, true, false, 0);
            creatures[i+33]=new Creature("Juggernaut", 23, 23, 40, 50, 300, false, false, null, null, true, false, 0);
            sortByNames(creatures);
            
        } catch (Exception e) {
            System.out.println(i);
            e.printStackTrace();
        }
        return creatures;
    }
    private static boolean hasMeleePenalty(String creatureInfo) {
        return (creatureInfo.contains("Ranged") && !creatureInfo.contains("Nomeleepenalty"));
    }
    private static Set<String> hatedCreatureNames(String name) {
        Set<String> toBeReturned = new HashSet<>();
        switch (name) {
            case "Angel":
            case "ArchAngel":
                toBeReturned.add("Devil");
                toBeReturned.add("ArchDevil");
                break;
            case "Devil":
            case "ArchDevil":
                toBeReturned.add("Angel");
                toBeReturned.add("ArchAngel");
                break;
            case "Genie":
            case "MasterGenie":
                toBeReturned.add("Efreeti");
                toBeReturned.add("EfreetSultan");
                break;
            case "Efreeti":
            case "EfreetSultan":
                toBeReturned.add("Genie");
                toBeReturned.add("MasterGenie");
                break;
            case "BlackDragon":
                toBeReturned.add("Titan");
                break;
            case "Titan":
                toBeReturned.add("BlackDragon");
                break;
        }
        return toBeReturned;
    }
    private static Set<String> oppositeElementalsNames(String name) {
        Set<String> toBeReturned = new HashSet<>();
        switch (name) {
            case "AirElemental":
            case "StormElemental":
                toBeReturned.add("EarthElemental");
                toBeReturned.add("MagmaElemental");
                break;
            case "EarthElemental":
            case "MagmaElemental":
                toBeReturned.add("AirElemental");
                toBeReturned.add("StormElemental");
                break;
            case "WaterElemental":
            case "IceElemental":
                toBeReturned.add("FireElemental");
                toBeReturned.add("EnergyElemental");
                break;
            case "FireElemental":
            case "EnergyElemental":
                toBeReturned.add("WaterElemental");
                toBeReturned.add("IceElemental");
                break;
        }
        return toBeReturned;
    }
    private static boolean isMindSpellImmune(String creatureInfo) {
        return (creatureInfo.contains("Undead") || creatureInfo.contains("Elemental")
                || creatureInfo.contains("Unliving") || creatureInfo.contains("Mind")
                || creatureInfo.contains("Resistallspells"));
    }
    private static boolean isDoubleShooting(String creatureInfo, boolean isRanged) {
        return (isRanged && creatureInfo.contains("Doubleattack"));
    }
    public static int getShotCount(String creatureInfo, boolean isRanged) {
        if(isRanged){
            String[] parts = creatureInfo.split("\\(|shots");
            return Integer.parseInt(parts[1]);    
        }
        return 0;
    } 
    private static Creature[] sortByNames(Creature[] creature) {
        try {
            for (int i = 0; i < creature.length; i++) {
                for (int j = 0; j < creature.length - i - 1; j++) {
                    for (int n = 0; n < creature[j].name.length(); n++) {
                        if (creature[j+1].name.length() == n || creature[j + 1].name.charAt(n) < creature[j].name.charAt(n)) {
                            Creature temp = creature[j];
                            creature[j] = creature[j + 1];
                            creature[j + 1] = temp;
                            break;
                        }
                        if (creature[j + 1].name.charAt(n) == creature[j].name.charAt(n)) {
                            continue;
                        }
                        break;
                    }
                }
            }
        } catch (Exception e) {
        }
		return creature;
	}
    public static String[] createNames(Creature[] creatures) {
		String[] creaturesNames = new String[creatures.length];
		for (int i = 0; i < creaturesNames.length; i++) {
			creaturesNames[i] = creatures[i].name;
		}
		return creaturesNames;
	}
    public static Creature getCreatureByName(Creature[] creatures ,String creatureName){
        for(int i=0; i<creatures.length; i++){
            if(creatureName.equals(creatures[i].name))
            return creatures[i];
        }
        return creatures[0];
    }
}