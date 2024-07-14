import java.util.*;

class Student {
    int id;
    String name;
    int marks[] = new int[5];

    public Student(int id, String name, int marks[]) {
        this.id = id;
        this.name = name;

        for (int i = 0; i < 5; i++) {
            this.marks[i] = marks[i];
        }
    }
}

class MarksSort {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        Student arr[] = new Student[n];

        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            String name = sc.next();
            int marks[] = new int[5];

            for (int j = 0; j < 5; j++)
                marks[j] = sc.nextInt();
            arr[i] = new Student(id, name, marks);
        }

        int res[] = marksSort(n, arr);

        for (int j : res) {
            System.out.print(j + " ");
        }
    }

    static int[] marksSort(int n, Student arr[]) {
        Arrays.sort(arr, new Comparator<Student>() {
            @Override
            public int compare(Student s1, Student s2) {
                // Compare by total marks in descending order
                int totalMarksComparison = Integer.compare(Arrays.stream(s2.marks).sum(), Arrays.stream(s1.marks).sum());
                if (totalMarksComparison != 0) {
                    return totalMarksComparison;
                }

                // Compare by name in ascending order
                int nameComparison = s1.name.compareTo(s2.name);
                if (nameComparison != 0) {
                    return nameComparison;
                }

                // Compare by ID in ascending order
                return Integer.compare(s1.id, s2.id);
            }
        });

        // Extract and return sorted IDs
        int[] sortedIds = new int[n];
        for (int i = 0; i < n; i++) {
            sortedIds[i] = arr[i].id;
        }

        return sortedIds;
    }
}
