
import java.io.*;
import java.lang.*;
import java.util.*;

@SuppressWarnings("unchecked")
class Example {
    //Steps Taken
    static int stepnumber = 0;
    
    /*
    *   Solves the tower for arg[0] = 2
    */
    static public void Solve2DiscsTOH ( Stack source, Stack temp, Stack dest ) {
        temp.push ( source.pop() );
        stepnumber++;
        PrintStacks();
        dest.push ( source.pop() );
        stepnumber++;
        PrintStacks();
        dest.push ( temp.pop() );
        stepnumber++;
        PrintStacks();
    }

    /*
    *   Solves the tower for n discs
    */
    static public int SolveTOH ( int nDiscs, Stack source, Stack temp, Stack dest ) {
        if ( nDiscs <= 4 ) {
            if ( ( nDiscs % 2 ) == 0 ) {
                Solve2DiscsTOH ( source, temp, dest );
                nDiscs = nDiscs - 1;
                if ( nDiscs == 1 ) {
                    return 1;
                }
                temp.push ( source.pop() );
                stepnumber++;
                PrintStacks();
                Solve2DiscsTOH ( dest, source, temp );
                dest.push ( source.pop() );
                stepnumber++;
                PrintStacks();
                SolveTOH ( nDiscs, temp, source, dest );
            } else {
                if ( nDiscs == 1 ) {
                    return -1;
                }
                Solve2DiscsTOH ( source, dest, temp );
                nDiscs = nDiscs - 1;
                dest.push ( source.pop() );
                stepnumber++;
                PrintStacks();
                Solve2DiscsTOH ( temp, source, dest );
            }
            return 1;
        } else if ( nDiscs >= 5 ) {
            SolveTOH ( nDiscs - 2, source, temp, dest );
            temp.push ( source.pop() );
            stepnumber++;
            PrintStacks();
            SolveTOH ( nDiscs - 2, dest, source, temp );
            dest.push ( source.pop() );
            stepnumber++;
            PrintStacks();
            SolveTOH ( nDiscs - 1, temp, source, dest );
        }
        return 1;
    }

    /*
     * Declare towers as stacks
     */
    static public Stack A = new Stack();
    static public Stack B = new Stack();
    static public Stack C = new Stack();
    static public void PrintStacks() {
        if ( countA != A.size() ||
                countB != B.size() ||
                countC != C.size() ) {
            int diffA = A.size() - countA;
            int diffB = B.size() - countB;
            int diffC = C.size() - countC;
            if ( diffA == 1 ) {
                if ( diffB == -1 ) {
                    System.out.print (  A.peek() + "  B -> A" );
                } else {
                    System.out.print (  A.peek() + "  C -> A" );
                }
            } else if ( diffB == 1 ) {
                if ( diffA == -1 ) {
                    System.out.print (  B.peek() + "  A -> B" );
                } else {
                    System.out.print (  B.peek() + "  C -> B" );
                }
            } else {
                if ( diffA == -1 ) {
                    System.out.print (  C.peek() + "  A -> C" );
                } else {
                    System.out.print (  C.peek() + "  B -> C" );
                }
            }
            countA = A.size();
            countB = B.size();
            countC = C.size();
            System.out.println();
        }
         PrintStack ( A );
        System.out.print ( "\t, " );
        PrintStack ( B );
        System.out.print ( "\t, " );
        PrintStack ( C );
        System.out.print ( "\t, \t" );
    }
    static int countA = 0;
    static int countB = 0;
    static int countC = 0;

    /*
     * Print an individual stack
     */ 
    static public void PrintStack ( Stack s ) {
        
        for ( int i = 0 ; i < 9 ; i ++){
            try{
                if((int) s.get(i) ==  9)  System.out.print ( "|||||||||"  + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  8)  System.out.print ( "|||||||| "   + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  7)  System.out.print ( "|||||||  "    + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  6)  System.out.print ( "||||||   "  + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  5)  System.out.print ( "|||||    "   + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  4)  System.out.print ( "||||     "    + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  3)  System.out.print ( "|||      "  + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  2)  System.out.print ( "||       "   + ((i == s.size()-1) ? "" : " "));
                if((int) s.get(i) ==  1)  System.out.print ( "|        "    + ((i == s.size()-1) ? "" : " "));
            } catch (Exception e) {
                System.out.print("\t");
            }
            
        }
    }
    public static void main ( String[] args ) {
        System.out.println("  A\t\t   B\t\t   C\t\t           MOVES");
        System.out.println("----------------------------------------------------------------------");
        try {
            int maxdisc = 0;
            String inpstring = args[0];
            stepnumber = 0;
            maxdisc = Integer.parseInt ( inpstring );
            if ( maxdisc <= 1 || maxdisc >= 10 ) {
                System.out.println ( "Enter between 2 - 9" );
                return;
            }
            for ( int i = maxdisc; i >= 1; i-- ) {
                A.push ( i );
            }
            countA = A.size();
            countB = B.size();
            countC = C.size();
            PrintStacks();
            SolveTOH ( maxdisc, A, B, C );
            System.out.println("\n----------------------------------------------------------------------");
            //Print the numvber of moves required to complete the puzzle
            System.out.println ( "\n\tSteps Taken = " + stepnumber );
            while ( C.size() > 0 ) {
                C.pop();
            }
        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }
}

