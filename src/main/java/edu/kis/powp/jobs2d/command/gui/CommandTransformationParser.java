package edu.kis.powp.jobs2d.command.gui;

import edu.kis.powp.jobs2d.transformations.*;

public abstract class CommandTransformationParser {
    private static Transformation transformation;
    public static Transformation parseText(String input) {
        String[] transformArray = input.split(" ");
        try {
            switch (transformArray[0]) {
                case "scale":
                    transformation = new Scale(Integer.parseInt(transformArray[1]));
                    break;
                case "rotate":
                    transformation = new Rotation(Integer.parseInt(transformArray[1]),Integer.parseInt(transformArray[2]));
                    break;
                case "flip":
                    transformation = new Flip(Integer.parseInt(transformArray[1]),Integer.parseInt(transformArray[2]));
                    break;
                case "move":
                    transformation = new Move(Integer.parseInt(transformArray[1]),Integer.parseInt(transformArray[2]));
                    break;
                default:
                    System.out.println("Wrong input");
            }
        } catch (NumberFormatException e) {
            System.out.println("Wrong input");
        }
        return transformation;
    }
}
