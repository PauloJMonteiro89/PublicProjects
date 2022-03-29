package org.academiadecodigo.argicultores;

import java.util.HashMap;
import java.util.Map;

public class GameEngine {

    private int level;
    private int maxLevels;
    private HashMap<String, Integer> levels;

    public GameEngine(int maxLevels) {
        this.maxLevels = maxLevels;
        this.level = 1;
        levels = new HashMap<>();
        levelGenerator(1);
        levelGenerator(2);
    }

    public String levelGenerator(int nivel) {

        String question = "";

        switch (nivel) {
            case 1:

                int firstOp = getRandom(2, 10);
                int secondOp = getRandom(2, 10);
                int result = firstOp + secondOp;
                question = firstOp + " + " + secondOp + " ? ";
                levels.put(question, result);
                break;

            case 2:

                int firstOp2 = getRandom(5, 15);
                int secondOp2 = getRandom(2, 10);
                int thirdOp2 = getRandom(2, 10);
                int result2 = firstOp2 + secondOp2 + thirdOp2;
                question = firstOp2 + " + " + secondOp2 + " + " + thirdOp2 + " ? ";

                levels.put(question, result2);
                break;

            case 3:

                int firstOp3 = getRandom(10, 15);
                int secondOp3 = getRandom(10, 15);
                int thirdOp3 = getRandom(10, 15);
                int result3 = firstOp3 + secondOp3 + thirdOp3;
                question = firstOp3 + " + " + secondOp3 + " + " + thirdOp3 + " ? ";

                levels.put(question, result3);
                break;

            case 4:

                int firstOp4 = getRandom(10, 15);
                int secondOp4 = getRandom(15, 20);
                int thirdOp4 = getRandom(10, 15);
                int forthOp4 = getRandom(5, 15);
                int result4 = firstOp4 + secondOp4 + thirdOp4 - forthOp4;
                question = firstOp4 + " + " + secondOp4 + " + " + thirdOp4 + " - " + forthOp4 + " ? ";

                levels.put(question, result4);
                break;

            case 5:

                int firstOp5 = getRandom(5, 15);
                int secondOp5 = getRandom(5, 15);
                int thirdOp5 = getRandom(10, 20);
                int forthOp5 = getRandom(5, 10);
                int fifthOp5 = getRandom(10, 20);
                int result5 = firstOp5 + secondOp5 + thirdOp5 - forthOp5 + fifthOp5;
                question = firstOp5 + " + " + secondOp5 + " + " + thirdOp5 + " - " + forthOp5 + " + " + fifthOp5 + " ? ";

                levels.put(question, result5);
                break;

            case 6:

                int firstOp6 = getRandom(2, 10);
                int secondOp6 = getRandom(2, 10);
                int thirdOp6 = getRandom(10, 15);
                int forthOp6 = getRandom(5, 15);
                int fifthOp6 = getRandom(5, 15);
                int result6 = (firstOp6 * secondOp6) + thirdOp6 - forthOp6 + fifthOp6;
                question = firstOp6 + " * " + secondOp6 + " + " + thirdOp6 + " - " + forthOp6 + " + " + fifthOp6 + " ? ";

                levels.put(question, result6);
                break;
        }

        return question;

    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel() {
        this.level++;
    }

    public int getRandom(int min, int max) {
        return (int) (Math.ceil(Math.random() * (max - min))) + min;
    }

    public HashMap<String, Integer> getLevels() {
        return levels;
    }

    public int getMaxLevels() {
        return maxLevels;
    }
}
