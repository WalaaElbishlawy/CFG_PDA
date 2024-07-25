import java.io.*;
import java.util.*;

public class PDA 
{
    public static void main(String[] args) 
    {
        try 
        {
            File inputFile = new File("input_pda.txt");
            File outputFile = new File("output_pda.txt");
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
                            writer.println(PDA1(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 2:
                        writer.println(problemNumber);
                        for (String s : inputs) 
                        {
                            writer.println(PDA2(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 3:
                        writer.println(problemNumber);
                        for (String s : inputs) 
                        {
                            writer.println(PDA3(s) ? "accepted" : "not accepted");
                        }
                        break;
                    case 4:
                        writer.println(problemNumber);
                        for (String s : inputs) 
                        {
                            writer.println(PDA4(s) ? "accepted" : "not accepted");
                        }
                        break;
                    default:
                        break;
                }
                if (problemNumber != 5) 
                {
                    writer.println("End");
                }
            }
            scanner.close();
            writer.close();
            System.out.println("Output written to output.txt successfully.");
        } catch (FileNotFoundException e) 
        {
            e.printStackTrace();
        }
    }

    // Design a PDA for accepting a language {a^nb^n n>=0}
    public static boolean PDA1(String s) 
    {
        Stack<Character> stack = new Stack<>();
        char[] chars = s.toCharArray();
        char Z = 'Z';
        String currentState = "q0";

        for (char c : chars) 
        {
            switch (currentState) 
            {
                case "q0":
                    if (c == 'a') 
                    {
                        stack.push(Z);
                        currentState = "q1";
                    } 
                    else if (c == 'b') 
                    {
                        return false;
                    }
                    break;
                case "q1":
                    if (c == 'a') 
                    {
                        stack.push(Z);
                    } 
                    else if (c == 'b' && !stack.isEmpty() && stack.peek() == Z) 
                    {
                        stack.pop();
                        currentState = "q2";
                    } 
                    else 
                    {
                        return false;
                    }
                    break;
                case "q2":
                    if (c == 'b' && !stack.isEmpty() && stack.peek() == Z) 
                    {
                        stack.pop();
                    } 
                    else 
                    {
                        return false;
                    }
                    break;
            }
        }

        return currentState.equals("q2") && stack.isEmpty();
    }

    //Design a PDA for accepting a language {a^2nb^3n n>=1}
    public static boolean PDA2(String s) {
        int count = 0;
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == 'a') {
                stack.push(c);
            } else if (c == 'b') {
                count++;
                if (count == 3) {
                    for (int i = 0; i < 2; i++) {
                        if (!stack.isEmpty() && stack.peek() == 'a') {
                            stack.pop();
                        } else {
                            return false;
                        }
                    }
                    count = 0;
                }
            }
        }
        return stack.isEmpty();
    }


    // PDA for accepting a language that consists of strings of balanced left and right brackets.
    public static boolean PDA3(String s) 
    {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) 
        {
            if (c == '{') 
            {
                stack.push(c);
            } 
            else if (c == '}') 
            {
                char top = stack.pop();
                if (top != '{') 
                {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    // PDA for accepting a language {an+mbncm| n , m>=1}.
    public static boolean PDA4(String s) 
    {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) 
        {
            if (c == 'a') 
            {
                stack.push(c);
            } 
            else if (c == 'b') 
            {
                if (!stack.isEmpty() && stack.peek() == 'a') 
                {
                    stack.pop();
                } 
                else 
                {
                    return false;
                }
            } 
            else if (c == 'c') 
            {
                if (!stack.isEmpty() && stack.peek() == 'a') 
                {
                    stack.pop();
                } 
                else 
                {
                    return false;
                }
            } 
            else 
            {
                return false;
            }
        }
        return stack.isEmpty();
    }
}
