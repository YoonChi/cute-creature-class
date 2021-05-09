public class CuteCreature {

    protected String species;
    protected int level;          // positive integer, must be greater than 0
    private int hitPoints;      // non-negative
    protected int maxHitPoints;   // positive number
    protected int attackRating;   // non-negative
    protected int defenseRating;  // integer, can be negative.
    protected int experiencePoints; // non-negative int
    protected int experienceValue;  // non-negative int
    private boolean isSpecial;
    private int totalPoints;
    private int currentLevel;
    protected boolean sameLevel;
    protected int expNeededForNextLevel;

    // non-static method constructor
    public CuteCreature(String species, int maxHitPoints, int attackRating,
                        int defenseRating, int experienceValue, boolean isSpecial) {
        level = 1; // default
        hitPoints = maxHitPoints;
        experiencePoints = 0;
        totalPoints = 200;
        expNeededForNextLevel = 200;
        this.species = species;
        this.maxHitPoints = maxHitPoints;
        this.attackRating = attackRating;
        this.defenseRating = defenseRating;
        this.experienceValue = experienceValue;
        this.isSpecial = isSpecial;
    }

    // default constructor
    public CuteCreature() {
        // The line below calls the other version of the CuteCreature constructor
        this("NoSpecies", 30, 20, 5, 200, false);
    }

    // setter methods
    public void setSpecies(String species) {this.species = species;}
    public void setAttackRating(int attackRating) {this.attackRating = attackRating;}
    public void setDefenseRating(int defenseRating) {this.defenseRating = defenseRating;}
    public void setExperienceValue(int experienceValue) {this.experienceValue = experienceValue;}
    public void setIsSpecial(boolean isSpecial) {this.isSpecial = isSpecial; }
    public void setMaxHitPoints(int maxHitPoints) {this.maxHitPoints = maxHitPoints;}

    // getter methods
    public String getSpecies() {return species;}
    public int getHitPoints() {return hitPoints;}
    public boolean getIsSpecial() {return isSpecial;}
    public int getAttackRatting() {return attackRating;}
    public int getDefenseRating() {return defenseRating;}
    public int getExperienceValue() {return defenseRating;}
    public int getExperiencePoints() {return experiencePoints;}
    public int getMaxHitPoints() {return maxHitPoints;}

    // declared private because it is meant to be called only from gainExp public method
    protected void levelUp() {
        sameLevel = false;

        if (level >= 2 && level <= 9) {
            maxHitPoints += 7;
            attackRating += 3;
            defenseRating += 3;
        }
        else if (level >= 10) {
            maxHitPoints += 2;
            attackRating += 1;
            defenseRating += 1;
        }

        experienceValue = experienceValue + 15;
        hitPoints = maxHitPoints;

        if (level > currentLevel) {
            System.out.println(species + " leveled to " + level + "!");
        }
        else {
            System.out.println(species + " has not leveled up, but it's okay - still going strong!");
            sameLevel = true;
        }
    }

    public void gainExp(int exp) {
        experiencePoints = experiencePoints + exp;
        currentLevel = level;

        if ((experiencePoints) > expNeededForNextLevel) {
            level += 1;
            expNeededForNextLevel += 75;
            totalPoints += expNeededForNextLevel;

        }

        System.out.println(species + " gained " + exp + " experience!");

        levelUp();
    }

    public void takeDamage(int dmg) {
        int chp = this.hitPoints - dmg;
        if (chp <= 0) {
            this.hitPoints = 0;
        }
        else {
            this.hitPoints = chp;
        }
    }


    public void attack(CuteCreature c) {

        double attackChance = Math.random() * 100;
        String attackType;

        int damage = 0;

        System.out.println(species + " attacks " + c.getSpecies() + "!");

        if (attackChance < 65) { //65% chance for a hit
            attackType = "Hit";
        }
        else if (attackChance < 80) { // 15% chance for critical hit
            attackType = "Critical hit";
        }
        else { // 20% chance for miss
            attackType = "Miss";
        }

        if (attackType.equals("Hit")) {
            damage = this.attackRating - c.defenseRating;

            if (damage <= 0) {
                c.takeDamage(1);
                damage = 1;
            }
            else {
                c.takeDamage(damage);
            }

            System.out.println(attackType + "! " + c.getSpecies() + " took " + damage + " damage!");

            if (c.getHitPoints() == 0){
                System.out.println((c.getSpecies() + " fainted!"));
                gainExp(c.experienceValue);
            }

        }
        else if (attackType.equals("Critical hit")) {
            damage = this.attackRating - c.defenseRating;

            if (damage <= 0) {
                damage = 2;
            }
            else {
                damage *= 2;
            }

            c.takeDamage(damage);

            System.out.println(attackType + "! " + c.getSpecies() + " took " + damage + " damage!");

            if (c.getHitPoints() == 0){
                System.out.println((c.getSpecies() + " fainted!"));
                gainExp(c.experienceValue);
            }
        }
        else {
            System.out.println("Miss!");
        }
    }


    public String toString() {
//        String info1 = "Level " + getLevel() + " - " + getSpecies();
        String info1 = "Level " + level + " - " + species;
        String info2 = "--------------";
        String info3 = "";


        if (isSpecial) {
            info3 = "*** Special! ***";
        }

        String info4 = "HP: " + hitPoints + "/" + maxHitPoints;
        String info5 = "ATK: " + attackRating;
        String info6 = "DEF: " + defenseRating;
        String info7 = "XP: " + experiencePoints + "/" + totalPoints;
        String info8 = "XP VAL: " + experienceValue;

        return info1 + "\n" + info2 + "\n" + info3 + "\n" + info4 + "\n" + info5 + "\n" + info6 + "\n" + info7 + "\n" + info8;

    }
}
