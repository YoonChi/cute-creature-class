public class EvolvableCuteCreature extends CuteCreature {

    private int evolvableLevel; // indicates at which level the evolution process should take place
    private String evolvableSpecies;
    private String attunedType;

    // Constructors are *not* inherited from the superclass, so create a non-static method constructor for the EvolvableCuteCreature class
    public EvolvableCuteCreature(String species, int maxHitPoints, int attackRating, int defenseRating, int experienceValue,
                                 boolean isSpecial, int evolvableLevel, String evolvableSpecies) {
        super(species, maxHitPoints, attackRating, defenseRating, experienceValue, isSpecial); // invoke super class (CuteCreature) constructor
        attunedType = "None";
        this.evolvableLevel = evolvableLevel;
        this.evolvableSpecies = evolvableSpecies;
    }

    public EvolvableCuteCreature() {    // Overloaded EvolvableCuteCreature constructor
        // The line below calls the other version of the CuteCreature constructor
        this("NoSpecies", 10, 20, 5, 200, false, 2, "Evolvable Species Name");
    }

    // setter methods
    public void setEvolveLevel(int evolvableLevel) {this.evolvableLevel = evolvableLevel;}
    public void setEvolvedSpecies(String evolvableSpecies) {this.evolvableSpecies = evolvableSpecies;}

    // getter methods
    public String getAttunedType() { return attunedType; }

    @Override
    public String toString() {

        String result = super.toString();

        if (species.equals(evolvableSpecies)) {
            String info1 = evolvableSpecies + " is attuned to " + attunedType + " type.";
            result += "\n" + info1;

        } else {
            String info2 = species + " has no type because " + species +" has not evolved yet.";
            result += "\n" + info2;
        }
        result += "\n";

        return result;
    }

    private void assignAttuneType() {
        if (species.charAt(0) >= 'A' &&  species.charAt(0) <= 'F') {
            attunedType = "light";
        }
        else if (species.charAt(0) >= 'G' && species.charAt(0)  <= 'L') {
            attunedType = "dark";
        }
        else if (species.charAt(0) >= 'M' && species.charAt(0) <= 'R') {
            attunedType = "nature";
        }
        else if (species.charAt(0) >= 'S' && species.charAt(0) <= 'Z') {
            attunedType = "tech";
        }
    }

    @Override
    protected void levelUp() {

        super.levelUp();

        maxHitPoints += 9;
        attackRating += 4;
        defenseRating += 4;

        if ((!super.sameLevel) && (level == evolvableLevel)) {
            System.out.println(species + " evolved to " + evolvableSpecies + "!");
            species = evolvableSpecies;
            assignAttuneType();
        }
    }

    public void specialAttack(CuteCreature c) {
        int damage = 0;

        String attackType = "None";

        if (c instanceof EvolvableCuteCreature) { // check to see if c is an evolvable creature

            EvolvableCuteCreature e = (EvolvableCuteCreature)c;

            e.assignAttuneType();

            if ((attunedType.equals("tech") && (e.attunedType.equals("light"))) || (attunedType.equals("light") && (e.attunedType.equals("tech")))) {
                attackType = "resistent";
            }
            if ((attunedType.equals("nature") && (e.attunedType.equals("dark"))) || (attunedType.equals("dark") && (e.attunedType.equals("nature")))) {
                attackType = "resistent";
            }
            if ((attunedType.equals("nature") && (e.attunedType.equals("tech"))) || (attunedType.equals("tech") && (e.attunedType.equals("nature")))) {
                attackType = "vulnerable";
            }
            if ((attunedType.equals("light") && (e.attunedType.equals("dark"))) || (attunedType.equals("dark") && (e.attunedType.equals("light")))) {
                attackType = "vulnerable";
            }
            if (attunedType.equals(e.getAttunedType())) {
                attackType = "same";
            }
        }

        if (level < evolvableLevel) { // only evolvable creature can use special attack
            System.out.println(species + " cannot use special attack because " + species + " has not evolved yet.");
            super.attack(c);
        }
        else {
            System.out.println(species + " attacks " + c.getSpecies() + "!");
            switch (attackType) {
                case "same" -> {
                    System.out.println("Ineffective - no damage delt.");
                }
                case "resistent" -> {
                    damage = attackRating - (5 * c.defenseRating); // special attack deals A-5D damage, with minimum possible dmg of 0
                    if (damage < 0) {
                        damage = 0;
                    }
                    c.takeDamage(damage);
                }
                case "vulnerable" -> {
                    damage = (attackRating * 5) - c.defenseRating; // special attack deals 5A-D damage, with minimum possible damage of 10
                    if (damage < 10) {
                        damage = 10;
                    }
                    c.takeDamage(damage);
                }
                default -> {
                    damage = attackRating - c.defenseRating;
                    if (damage <= 0) {
                        damage = 1;
                    }
                    c.takeDamage(damage);
                }
            }
            System.out.println("Special attack! " + c.getSpecies() + " took " + damage + " damage!");

            if (c.getHitPoints() == 0) {
                System.out.println((c.getSpecies() + " fainted!"));
                super.gainExp(c.experienceValue);
            }
        }
    }
}
