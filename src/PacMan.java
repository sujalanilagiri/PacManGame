import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;


public class PacMan {

    static final ArrayList<String> POSSIBLE_DIRECTIONS = new ArrayList<>(Arrays.asList("NORTH", "EAST", "SOUTH", "WEST"));
    //Enum to recognize all the instrctions possible
    private int xCoOrdinate = -1;
    private int yCoOrdinate = -1;
    private String direction;

    public static void main(String[] args) {
        PacMan pacMan = new PacMan();
        pacMan.playGame();
    }

    public void playGame() {
        BufferedReader br = null;
        try {
            br = new BufferedReader((new InputStreamReader(System.in)));
            while (true) {
                String currentLineInput = br.readLine();
                //Break if we reach end of input
                if (currentLineInput == null) {
                    break;
                }
                executeCurrentLine(currentLineInput);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void executeCurrentLine(String currentLineInput) {
        //we do not honour any instruction before #instrucntions.PLACE.
        if (currentLineInput.startsWith(instructions.PLACE.name())) {
            String coOrdinateString = currentLineInput.split(" ")[1];
            String[] values = coOrdinateString.split(",");
            xCoOrdinate = Integer.valueOf(values[0]);
            yCoOrdinate = Integer.valueOf(values[1]);
            direction = values[2];
        }
        if (currentLineInput.startsWith(instructions.MOVE.name())) {
            if (direction.equalsIgnoreCase("NORTH") && yCoOrdinate + 1 < 5) {
                ++yCoOrdinate;
            } else if (direction.equalsIgnoreCase("EAST") && xCoOrdinate + 1 < 5) {
                ++xCoOrdinate;
            } else if (direction.equalsIgnoreCase("SOUTH") && yCoOrdinate - 1 > 0) {
                --yCoOrdinate;
            } else if (direction.equalsIgnoreCase("WEST") && xCoOrdinate - 1 > 0) {
                --xCoOrdinate;
            }
        } else if (currentLineInput.startsWith(instructions.LEFT.name())) {
            direction = POSSIBLE_DIRECTIONS.get(setDirectionAfterTurn(direction, instructions.LEFT));
        } else if (currentLineInput.startsWith(instructions.RIGHT.name())) {
            direction = POSSIBLE_DIRECTIONS.get(setDirectionAfterTurn(direction, instructions.RIGHT));
        }
        else if (currentLineInput.startsWith(instructions.REPORT.name())) {
            System.out.print(xCoOrdinate + "," + yCoOrdinate + "," + direction);
        }

    }

    private  int setDirectionAfterTurn(String direction, instructions instructions) {
        int dir = POSSIBLE_DIRECTIONS.indexOf(direction);
        if (instructions.equals(instructions.LEFT))
            dir = (4 + dir - 1) % 4;
        else
            dir = (4 + dir + 1) % 4;

        return dir;
    }

    public enum instructions {
        PLACE,
        MOVE,
        LEFT,
        RIGHT,
        REPORT
    }

}

