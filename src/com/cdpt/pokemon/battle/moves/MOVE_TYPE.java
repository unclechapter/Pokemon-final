package com.cdpt.pokemon.battle.moves;

public enum MOVE_TYPE {
	NORMAL(new String[]{},
            new String[]{"Rock", "Steel"},
            new String[]{"Ghost"}),
    FIGHTING(new String[]{"Normal", "Rock", "Steel", "Ice", "Dark"},
            new String[]{"Flying", "Poison", "Bug", "Psychic"},
            new String[]{"Ghost"}),
    FLYING(new String[]{"Fighting", "Grass"},
            new String[]{"Rock", "Steel", "Electric"},
            new String[]{}),
    POISON(new String[]{"Grass"},
            new String[]{"Poison", "Ground", "Rock", "Bug"},
            new String[]{"Steel"}),
    GROUND(new String[]{"Poison", "Rock", "Steel", "Fire", "Electric"},
            new String[]{"Bug", "Grass"},
            new String[]{"Flying"}),
    ROCK(new String[]{"Flying", "Bug", "Fire"},
            new String[]{"Fighting", "Ground", "Steel"},
            new String[]{}),
    BUG(new String[]{"Grass", "Psychic", "Dark"},
            new String[]{"Fighting", "Flying", "Poison", "Ghost", "Steel", "Fire"},
            new String[]{}),
    GHOST(new String[]{"Ghost", "Psychic"},
            new String[]{"Steel", "Dark"},
            new String[]{"Normal"}),
    STEEL(new String[]{"Rock", "Ice"},
            new String[]{"Steel", "Fire", "Water", "Electric"},
            new String[]{}),
    FIRE(new String[]{"Grass", "Ice", "Bug"},
            new String[]{"Fire", "Water", "Rock", "Dragon"},
            new String[]{}),
    WATER(new String[]{"Fire", "Rock", "Ground"},
            new String[]{"Water", "Grass", "Dragon"},
            new String[]{}),
    GRASS(new String[]{"Water", "Ground", "Rock"},
            new String[]{"Fire", "Grass", "Poison", "Flying", "Bug", "Dragon", "Steel"},
            new String[]{}),
    ELECTRIC(new String[]{"Water", "Flying"},
            new String[]{"Grass", "Electric", "Dragon"},
            new String[]{"Ground"}),
    PSYCHIC(new String[]{"Fighting", "Poison"},
            new String[]{"Psychic", "Steel"},
            new String[]{"Dark"}),
    ICE(new String[]{"Grass", "Ground", "Flying", "Dragon"},
            new String[]{"Fire", "Water", "Ice", "Steel"},
            new String[]{}),
    DRAGON(new String[]{"Dragon"},
            new String[]{"Steel"},
            new String[]{}),
    DARK(new String[]{"Psychic", "Ghost"},
            new String[]{"Fighting", "Dark", "Steel"},
            new String[]{});

    private final String[] SUPER_EFFECTIVE, NOT_VERY_EFFECTIVE, NO_EFFECT;

    MOVE_TYPE(String[] superEffective, String[] notVeryEffective, String[] noEffect) {
        SUPER_EFFECTIVE = superEffective;
        NOT_VERY_EFFECTIVE= notVeryEffective;
        NO_EFFECT = noEffect;
    }

    private boolean contains(String[] t, MOVE_TYPE p) {
        if(t.length == 0) {
            return false;
        }

        for(String type : t) {
            if(type.equalsIgnoreCase(p + "")) {
                return true;
            }
        }
        return false;
    }

    private MOVE_TYPE[] convert(String[] types) {
    	MOVE_TYPE[] t = new MOVE_TYPE[types.length];

        for(int i = 0; i < t.length; i++) {
            t[i] = valueOf(types[i]);
        }
        return t;
    }

    public boolean isSuperEffectiveAgainst(MOVE_TYPE t) {
        return contains(SUPER_EFFECTIVE, t);
    }

    public boolean isNotVeryEffectiveAgainst(MOVE_TYPE t) {
        return contains(NOT_VERY_EFFECTIVE, t);
    }

    public boolean hasNoEffectOn(MOVE_TYPE t) {
        return contains(NO_EFFECT, t);
    }

    public boolean isNormalAgainst(MOVE_TYPE t) {
        return !isSuperEffectiveAgainst(t) &&
                !isNotVeryEffectiveAgainst(t) &&
                !hasNoEffectOn(t);
    }

    public MOVE_TYPE[] getTypesSuperEffectiveAgainst() {
        return convert(SUPER_EFFECTIVE);
    }

    public MOVE_TYPE[] getTypesNotVeryEffectiveAgainst() {
        return convert(NOT_VERY_EFFECTIVE);
    }

    public MOVE_TYPE[] getTypesNoEffectAgainst() {
        return convert(NO_EFFECT);
    }
}
