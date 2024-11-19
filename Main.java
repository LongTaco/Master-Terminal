import java.util.*;
import javax.swing.*;


public class Main
{
    static Random randy = new Random();
    static int shuffles = 0;
    static int max = 101;
    static int len = 10;

    public static void main(String[] args)
    {
        HashMap<String, int[]> arrays = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        while (true)
        {
            System.out.print("> ");
            String input = scanner.nextLine();
            if (input.equalsIgnoreCase("exit")||input.equalsIgnoreCase("quit"))
            {
                break;
            }
            String[] parts = input.split(" ");
            String command = parts[0];
            
            try
            {
                switch(command)
                {
                    case "fill":
                    String arrayName = parts[1];
                    int[] array = arrays.get(arrayName);
                    if (array != null)
                    {
                        fillArray(array);
                        printArray(array);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "print":
                    arrayName = parts[1];
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        printArray(array);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "search":
                    arrayName = parts[1];
                    int searchValue = Integer.parseInt(parts[2]);
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        binarySearch(array, searchValue);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "isSorted":
                    arrayName = parts[1];
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        System.out.println(isSorted(array));
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "shuffle":
                    arrayName = parts[1];
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        shuffleArray(array);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "sort":
                    arrayName = parts[1];
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        selectionSort(array);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "randomSort":
                    arrayName = parts[1];
                    array = arrays.get(arrayName);
                    if (array != null)
                    {
                        randomSort(array);
                    }
                    else
                    {
                        System.out.println("Array not found");
                    }
                    break;

                    case "length":
                    len = Integer.parseInt(parts[1]);
                    break;

                    case "newArray":
                    arrays.put(parts[1], new int[Integer.parseInt(parts[2])]);
                    break;

                    case "remove":
                    arrays.remove(parts[1]);
                    break;

                    case "window":
                    String name = parts[1];
                    SwingUtilities.invokeLater(() -> {
                        JFrame frame = new JFrame(name);
                        frame.setSize(300, 300);
                        frame.setLocationRelativeTo(null);  
                        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
                        frame.setVisible(true);
                    });
                    break;
                    
                    //Errors
                    default:
                    System.out.println("Unknown command: " + parts[0]);
                    break;
                }

            }
            catch (Exception e){
            }
        }
        scanner.close();
    }

    public static void fillArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            array[i] = randy.nextInt(max);
        }
    }

    public static void printArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i]);
            System.out.print(" ");
        }
        System.out.print("\n");
    }

    public static void searchArray(int [] array, int x)
    {
        if (isSorted(array))
        {
            binarySearch(array, x);
        }
        else
        {
            selectionSort(array);
            binarySearch(array, x);
        }
    }

    public static void binarySearch(int[] array, int x)
    {
        boolean found = false;
        int first = 0;
        int last = array.length - 1;
        int middle = (first + last)/2;
        while (first <= last && !found)
        {
            middle = (first + last)/2;
            if (array[middle] == x)
            {
                found = true;
                System.out.println("Found at index: " + middle);
            }

            else if (x > array[middle])
            {
                first = middle + 1;
            }

            else if (x < array[middle])
            {
                last = middle - 1;
            }
        }

        if (!found)
        {
            System.out.println("Not in array");
        }
    }

    public static boolean isSorted(int[] array)
    {
        for (int i = 0; i < array.length - 1;)
        {
            if (array[i] <= array[i + 1])
            {
                i++;
            }
            else
            {
                return false;
            }
        }
        return true;
    }

    public static void shuffleArray(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            int j = randy.nextInt(array.length - 1);
            int temp = array[j];
            array[j] = array[i];
            array[i] = temp;
            shuffles++;
        }
    }

    public static void selectionSort(int[] array)
    {
        for (int i = 0; i < array.length; i++)
        {
            int smallest = array[i];
            int smallestIndex = i;
            for (int j = i + 1; j < array.length; j++)
            {
                if (array[j] < smallest)
                {
                    smallest = array[j];
                    smallestIndex = j;
                }
            }
            int temp = array[i];
            array[i] = smallest;
            array[smallestIndex] = temp;
        }
    }

    public static void randomSort(int[] array)
    {
        while (!isSorted(array))
        {
            shuffleArray(array);
        }
        System.out.print("\r");
    }
}