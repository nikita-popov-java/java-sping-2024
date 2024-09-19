package ru.nikitapopov;

import ru.nikitapopov.base.mod1.Tasks;
import ru.nikitapopov.base.mod2.Player;

public class Main {
    public static void main(String[] args) {
        var baseMod1Tasks = new Tasks();
        baseMod1Tasks.task1();
        baseMod1Tasks.task2();
        /////////
        var bot = new Player();
        var user = new Player(Player.Variants.PAPER, "Nikita");
        System.out.println(bot.whoWins(bot, user));
    }
}