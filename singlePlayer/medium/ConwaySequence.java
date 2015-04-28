import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/**
 * Conway Sequence
 **/
class Solution {

    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        // initialize
        int originalNumber = in.nextInt();
        int nbLines = in.nextInt();
        Queue<Integer> sequence = new LinkedList<Integer>();
        Queue<Integer> sequenceTmp = new LinkedList<Integer>();
        sequence.add(originalNumber);
        int prevNumber;
        int nbOcc;

        for (int i = 1; i < nbLines; i++) {
            prevNumber = sequence.poll();
            nbOcc = 1;
            for (Integer currNumber : sequence) {
                if (currNumber != prevNumber) {
                    // save the sequence when the current number change
                    sequenceTmp.add(nbOcc);
                    sequenceTmp.add(prevNumber);
                    // update for new sequence
                    prevNumber = currNumber;
                    nbOcc = 1;
                }
                else {
                    nbOcc++;
                }
            }
            // save last sequence
            sequenceTmp.add(nbOcc);
            sequenceTmp.add(prevNumber);
            // update sequences before each loop
            sequence = new LinkedList(sequenceTmp);
            sequenceTmp.clear();
        }
        // format ouput
        String answer = String.valueOf(sequence.poll());
        for (Integer num : sequence) {
            answer += " " + num;
        }
        System.out.println(answer);
    }
}