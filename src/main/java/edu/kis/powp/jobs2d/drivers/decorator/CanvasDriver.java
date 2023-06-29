package edu.kis.powp.jobs2d.drivers.decorator;

import edu.kis.powp.jobs2d.Job2dDriver;
import edu.kis.powp.jobs2d.features.CanvasFeature;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CanvasDriver extends DriverDecorator {

    int canvaX;
    int canvaY;
    int canvaWidth;
    int canvaHeight;

    public CanvasDriver(Job2dDriver driver) {
        super(driver);
        findCanvaParameters(CanvasFeature.getShape().toString());
        }

    private void findCanvaParameters(String shape) {
        Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(shape);

        List<Integer> canvaParameters = new ArrayList<>();
        while (matcher.find()) {
            canvaParameters.add((int)Math.round(Double.parseDouble(matcher.group())));
        }
        canvaParameters.remove(0);//remove unused argument
        //canvaParameters = [x, y, width, height]
        this.canvaX = canvaParameters.get(0);
        this.canvaY = canvaParameters.get(1);
        this.canvaWidth = canvaParameters.get(2);
        this.canvaHeight = canvaParameters.get(3);
    }

    public void setPosition(int x, int y) {
        if(canvaX + x > canvaX + canvaWidth){
            System.out.println("Set index 'x' out of canvas");
        }
        else if(canvaY + y > canvaY + canvaHeight){
            System.out.println("Set index 'y' out of canvas");
        }
        else super.setPosition(this.canvaX + x, this.canvaY + y);
    }

    @Override
    public void operateTo(int x, int y) {
        if(canvaX + x > canvaX + canvaWidth){
            System.out.println("Destination index 'x' out of canvas");
        }
        else if(canvaY + y > canvaY + canvaHeight){
            System.out.println("Destination index 'y' out of canvas");
        }
        else super.setPosition(this.canvaX + x, this.canvaY + y);
    }

    @Override
    public String toString(){
        return super.toString();
    }
}//ksztalt od canvy obiekt x to x+ canva

