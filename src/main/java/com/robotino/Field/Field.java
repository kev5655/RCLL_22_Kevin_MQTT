package com.robotino.Field;

import java.lang.reflect.Array;

public class Field {
     ElementOfField [][] elementOfFields = new ElementOfField[5][5];

    public Field(){
        for(int y = 0; y < elementOfFields.length; y++){
            for(int x = 0; x < elementOfFields[0].length; x++){
                // Befült das Objekt ElementOfField mit den Standart Parameter
                // ACHTUNG NICHT FERTIG STANDART PARAMETER MÜSSEN NOCH BERRECHTNET WERDEN
                elementOfFields[y][x].yPos = 1000;
                elementOfFields[y][x].xPos = 1000;
                elementOfFields[y][x].fieldNr = Integer.parseInt(Integer.toString(y) + Integer.toString(x));
            }
        }
    }
}
