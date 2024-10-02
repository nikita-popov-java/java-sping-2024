package ru.nikitapopov.base.mod2;

import java.util.Random;

public class Player {
    private String name;
    private Variants variant;

    public static enum Variants {
        ROCK, PAPER, SCISSORS;
    }

    public Player(Variants variant, String name) {
        this.variant = variant;
        this.name = name;
    }

    public Player() {
        this(Variants.values()[new Random().nextInt(Variants.values().length)], "Bot");
    }

    public String whoWins(Player p1, Player p2) {
        if (p1.variant.equals(p2.variant)) return "Ничья!";

        return switch (p1.variant) {
            case ROCK -> switch (p2.variant) {
                case PAPER -> p2.name + " выиграл!";
                case SCISSORS -> p1.name + " выиграл!";
                default -> throw new IllegalStateException("Unexpected value: " + p2.variant);
            };

            case PAPER -> switch (p2.variant) {
                case ROCK -> p1.name + " выиграл!";
                case SCISSORS -> p2.name + " выиграл!";
                default -> throw new IllegalStateException("Unexpected value: " + p2.variant);
            };

            case SCISSORS -> switch (p2.variant) {
                case ROCK -> p2.name + " выиграл!";
                case PAPER -> p1.name + " выиграл!";
                default -> throw new IllegalStateException("Unexpected value: " + p2.variant);
            };
        };
    }
}
