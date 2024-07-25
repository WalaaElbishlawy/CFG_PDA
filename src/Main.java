import java.io.*;
import java.util.*;
public class Main
{
    public static void main(String[] args)
    {
        try {
            File inputFile = new File("input_cfg.txt");
            File outputFile = new File("output_cfg.txt");
            PrintWriter writer = new PrintWriter(outputFile);

            Scanner scanner = new Scanner(inputFile);
            while (scanner.hasNextLine())
            {
                String problemNumberLine = scanner.nextLine().trim();
                if (problemNumberLine.isEmpty()) continue;
                int problemNumber = Integer.parseInt(problemNumberLine);
                List<String> inputs = new ArrayList<>();
                while (scanner.hasNextLine())
                {
                    String input = scanner.nextLine().trim();
                    if (input.equals("end")) break;
                    inputs.add(input);
                }
                switch (problemNumber)
                {
                    case 1:
                        writer.println(problemNumber);
                        for (String s : inputs)
                        {
                            writer.println(CFG1(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 2:
                        writer.println(problemNumber);
                        for (String s : inputs)
                        {
                            writer.println(CFG2(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 3:
                        writer.println(problemNumber);
                        for (String s : inputs)
                        {
                            writer.println(CFG3(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 4:
                        writer.println(problemNumber);
                        for (String s : inputs)
                        {
                            writer.println(CFG4(s) ? "accepted" : "not accepted");
                        }
                        break;
                    default:
                        break;
                }
                if (problemNumber!=5)
                {
                    writer.println("End");
                }
            }
            scanner.close();
            writer.close();
            System.out.println("Output written to output.txt successfully.");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    // CFG for accepting an equal number of a's and b's
    public static boolean CFG1(String s)
    {
        return cfgRecursive(s, 0, 0);
    }
    private static boolean cfgRecursive(String s, int index, int count)
    {

        if (index == s.length())
            return count == 0;
        char ch = s.charAt(index);
        if (ch == 'a')
        {
            return cfgRecursive(s, index + 1, count + 1);
        }
        else if (ch == 'b' && count > 0)
        {
            return cfgRecursive(s, index + 1, count - 1);
        }
        else
        {
            return false;
        }
    }

    //CFG for accepting a number of a's is twice the number of b's.
    public static boolean CFG2(String s)
    {
        int aCount = 0, bCount = 0;
        for (char c : s.toCharArray())
        {
            if (c == 'a')
                aCount++;
            else if (c == 'b')
                bCount++;
        }
        return aCount == 2 * bCount;
    }

    //CFG for accepting a palindrome Σ = {a,b}.
    public static boolean CFG3(String s)
    {
        int left = 0;
        int right = s.length() - 1;
        while (left < right)
        {
            if (s.charAt(left) != s.charAt(right))
            {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    // CFG for accepting a language {𝑎2𝑛+3𝑏𝑛 | n>=0}
    public static boolean CFG4(String s)
    {
        int countA = 0;
        int countB = 0;
        boolean foundB = false;

        for (char ch : s.toCharArray())
        {
            if (ch == 'a')
            {
                countA++;
                if (foundB)
                {
                    return false; // a shouldn't appear after b
                }
            }
            else if (ch == 'b')
            {
                countB++;
                foundB = true; // true when 'b' is encountered
            }
            else
            {
                return false;
            }
        }
        return countA == 2 * countB + 3 && foundB;
    }

}
