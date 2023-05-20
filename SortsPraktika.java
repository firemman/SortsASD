import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class SortsPraktika {
    private static void printFirstArray(ArrayList<Integer> arr) {   // Функция вывода изначального массива
        for (int el: arr) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
    public static void bubbleSort(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray); // создаём копию массива и изменяем уже её
        System.out.print("\nСортировка массива пузырьком (метод всплытия, наименьший влево):\n");
        printFirstArray(arr);

        for (int out = 0; out < arr.size() - 1; out++){     // внешний цикл с первого элемента до последнего
            for (int in = arr.size() - 1; in >= 1; in--){       // внутренний цикл с последнего элемента до предпоследнего
                if (arr.get(in) < arr.get(in - 1)) {        // если текущий элемент меньше следующего, то
                    Collections.swap(arr, in, in - 1);      // вызываем метод меняющий их местами
                    for (int el: arr) {
                        System.out.print(el + " ");     // выводим текущую итерацию
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void sinkingSort(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray);     // Создаём копию массива и изменяем уже её
        System.out.print("\nСортировка массива пузырьком (метод погружения, наибольший вправо):\n");
        printFirstArray(arr);

        for (int out = arr.size() - 1; out >= 1; out--){        // внешний цикл с конца массива в начало
            for (int in = 0; in < out; in++){       // внутренний цикл с нулевого элемента до текущего значения внешнего цикла
                if (arr.get(in) > arr.get(in + 1)) {        // если текущий элемент больше следующего, то
                    Collections.swap(arr, in, in + 1);      // вызываем метод, меняющий их местами
                    for (int el: arr) {
                        System.out.print(el + " ");     // выводим текущую итерацию
                    }
                    System.out.println();
                }
            }
        }
    }

    public static void sinkingSortWithOptimization(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray);     // Создаём копию массива и изменяем уже её
        System.out.print("\nСортировка массива пузырьком (оптимизация по количеству пузырьков):\n");
        printFirstArray(arr);

        int p;

        for (int out = arr.size() - 1; out >= 1; out--){        // внешний цикл с конца массива в начало
            p = 0;  // количество сравнений за шаг приравниваем нулю
            for (int in = 0; in < out; in++){       // внутренний цикл с нулевого элемента до текущего значения внешнего цикла
                if (arr.get(in) > arr.get(in + 1)) {        // если текущий элемент больше следующего, то
                    Collections.swap(arr, in, in + 1);      // вызываем метод, меняющий их местами
                    p = 1;  // устанавливаем, что хотя бы один обмен был произведён
                    for (int el: arr) {
                        System.out.print(el + " ");     // выводим текущую итерацию
                    }
                    System.out.println();
                }
            }
            if (p == 0) {   // если обменов за текущий шаг не было произведено
                break;  // то выходим из внешнего цикла
            }
        }
    }

    public static void shakerSort(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray);     // Создаём копию массива и изменяем уже её
        System.out.print("\nШейкерная сортировка массива (один шаг - всплытие, другой - погружение):\n");
        printFirstArray(arr);

        int left = 0;   // устанавливаем левую границу
        int right = arr.size() - 1;     // устанавливаем правую границу
        while (left <= right) {
            for (int i = right; i > left; i--) {
                if (arr.get(i - 1) > arr.get(i)) {
                    Collections.swap(arr, i - 1, i);
                    for (int el: arr) {
                        System.out.print(el + " ");
                    }
                    System.out.println();
                }
            }
            left++;
            for(int i = left; i < right; i++) {
                if (arr.get(i) > arr.get(i + 1)) {
                    Collections.swap(arr, i, i + 1);
                    for (int el: arr) {
                        System.out.print(el + " ");
                    }
                    System.out.println();
                }
            }
            right--;
        }
    }

    public static void sortShell1(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray);
        System.out.print("\nСортировка массива алгоритмом Шелла (1-я строка - изначальный массив):\n");
        printFirstArray(arr);

        int i, j, temp, h;
        for (h = arr.size() / 2; h > 0; h /= 2) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr.set(j, arr.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                    } else break;   // иначе выходим из цикла
                }
                arr.set(j, temp);   // значение текущего элемента устанавливаем равным temp
//                for (int el: arr) {
//                    System.out.print(el + " ");
//                }
//                System.out.println();
            }
            for (int el: arr) {
                System.out.print(el + " ");
            }
            System.out.println();
        }
    }
    public static void sortShell2() {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        System.out.print("\nСортировка массива алгоритмом Шелла (1-я строка - изначальный массив):\nВведите размерность массива (степень двойки) = ");
        int pov = console.nextInt();
        for (int i = 0; i < Math.pow(2, pov); i++) {
            int rand = (int) (Math.random() * 100);
            arr.add(rand);
        }
        printFirstArray(arr);

        long countCompare = 0, countTransfer = 0;

        int i, j, temp, h;
        for (h = arr.size() / 2; h > 0; h /= 2) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr.set(j, arr.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                        countTransfer++;
                        countCompare++;
                    } else {
                        countCompare++;
                        break;   // иначе выходим из цикла
                    }
                }
                arr.set(j, temp);   // значение текущего элемента устанавливаем равным temp
            }
        }

        for (int el: arr) {
            System.out.print(el + " ");
        }
        System.out.println("\nКол-во сравнений = " + countCompare + ". Кол-во пересылок = " + countTransfer + ".");
    }
    public static void sortShell3() {
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner console = new Scanner(System.in);
        System.out.print("Сортировка массива алгоритмом Шелла (1-я строка - изначальный массив):\nВведите размерность массива: ");
        int size = console.nextInt();
        for (int i = 0; i < size; i++) {
            int rand = (int) (Math.random() * 100);
            arr.add(rand);
        }
        System.out.print("\nВведите количество шагов сортировки: ");
        int sizeShagov = console.nextInt();
        ArrayList<Integer> arrShagov = new ArrayList<>();

        System.out.print("Введите ваши произвольные шаги в одну строчку разделяя одним пробелом: ");
        for (int i = 0; i < sizeShagov; i++) {
            int proizvolShag = console.nextInt();
            arrShagov.add(proizvolShag);
        }
        printFirstArray(arr);

        int i, j, temp;
        for (Integer h: arrShagov) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr.set(j, arr.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                    } else break;   // иначе выходим из цикла
                }
                arr.set(j, temp);   // значение текущего элемента устанавливаем равным temp
            }
        }

        for (int el: arr) {
            System.out.print(el + " ");
        }
        System.out.println();
    }
    public static void sortShell4(int pow) {
        ArrayList<Integer> arr2 = new ArrayList<>();
        int pov = pow;
        for (int i = 0; i < Math.pow(2, pov); i++) {
            int rand = (int) (Math.random() * 10000);
            arr2.add(rand);
        }

        long countCompare2 = 0, countTransfer2 = 0;

        int i, j, temp, h;
        for (h = arr2.size() / 2; h > 0; h /= 2) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr2.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr2.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr2.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr2.set(j, arr2.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                        countTransfer2++;
                        countCompare2++;
                    } else {
                        countCompare2++;
                        break;   // иначе выходим из цикла
                    }
                }
                arr2.set(j, temp);   // значение текущего элемента устанавливаем равным temp
            }
        }
//        ShellBest best = new ShellBest(pow);

        System.out.println("\nКол-во сравнений = " + countCompare2 + ". Кол-во пересылок = " + countTransfer2 + ".");

//        System.out.println("\nКол-во сравнений (лучший случай) = " + best.countCompare1 + ". Кол-во пересылок = " + best.countTransfer1 + ".");
//        System.out.println("\nКол-во сравнений (не отсортированный) = " + countCompare2 + ". Кол-во пересылок = " + countTransfer2 + ".");
//        System.out.println("\nКол-во сравнений (худший случай) = " + countCompare3 + ". Кол-во пересылок = " + countTransfer3 + ".\n");
//
//        System.out.println("Срнеднее значение сравнений: ");

    }

    private static void ShellBest(int pow) {
        ArrayList<Integer> arr1 = new ArrayList<>();
        int pov = pow;
        for (int i = 0; i < Math.pow(2, pov); i++) {
            int rand = (int) (Math.random() * 10000);
            arr1.add(rand);
        }

        Collections.sort(arr1);
        long countCompare1 = 0, countTransfer1 = 0;

        int i, j, temp, h;
        for (h = arr1.size() / 2; h > 0; h /= 2) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr1.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr1.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr1.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr1.set(j, arr1.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                        countTransfer1++;
                        countCompare1++;
                    } else {
                        countCompare1++;
                        break;   // иначе выходим из цикла
                    }
                }
                arr1.set(j, temp);   // значение текущего элемента устанавливаем равным temp
                countTransfer1++;
            }
        }
    }
    private static void ShellWorse(int pow) {
        ArrayList<Integer> arr3 = new ArrayList<>();
        int pov = pow;
        for (int i = 0; i < Math.pow(2, pov); i++) {
            int rand = (int) (Math.random() * 10000);
            arr3.add(rand);
        }

        Collections.sort(arr3);
        Collections.reverse(arr3);
        long countCompare3 = 0, countTransfer3 = 0;

        int i, j, temp, h;
        for (h = arr3.size() / 2; h > 0; h /= 2) {  // задаём начальный шаг и каждую итерацию делим его на 2
            for (i = h; i < arr3.size(); i++) {   // цикл проходит по элементам массива начиная с текущего шага (h)
                temp = arr3.get(i);  // задаём временную переменную значением текущего элемента, используется для сравнения с другими элементами массива отделенных шагом (h)
                for (j = i; j >= h; j -= h) {   // в этом цикле обходим элементы массива на расстоянии h от текущего элемента и после каждой итерации уменьшаем j на h
                    if (arr3.get(j - h) > temp) {    // если значение элемента на расстоянии h больше чем значение текущего элемента temp ...
                        arr3.set(j, arr3.get(j - h));   // ... тогда элемент в шаге h от текущего перемещается в текущую позицию
                        countTransfer3++;
                        countCompare3++;
                    } else {
                        countCompare3++;
                        break;   // иначе выходим из цикла
                    }
                }
                arr3.set(j, temp);   // значение текущего элемента устанавливаем равным temp
                countTransfer3++;
            }
        }
    }
    public static void sortExchangeMin(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray); // Создаём копию массива и изменяем уже её
        System.out.print("\nСортировка массива выбором используя SearchMin (1-я строка - изначальный массив):\n");
        printFirstArray(arr);
        for (int i = 0; i < arr.size() - 1; i++) {  // Внешний цикл по всем элементам с первого по порядку (кроме последнего в массиве)
            int minArr = Integer.MAX_VALUE;
            int keyMin = 0;     // Индекс минимального числа в массиве

            for (int j = i; j < arr.size(); j++) {  // Внутренний цикл по всем элементам кроме уже пройденных внешним циклом (то есть цикл по неотсортированной части)
                if (arr.get(j) < minArr) {      // Находим минимальный элемент во внутреннем цикле
                    minArr = arr.get(j);
                    keyMin = j;
                }
            }
            Collections.swap(arr, arr.indexOf(arr.get(i)), arr.indexOf(arr.get(keyMin)));   // Меняем значение текущего элемента с минимальным
            for (int element: arr) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }

    public static void sortExchangeMax(ArrayList<Integer> oldArray) {
        ArrayList<Integer> arr = new ArrayList<>(oldArray); // Создаём копию массива и изменяем уже её
        System.out.print("\nСортировка массива выбором используя SearchMax (1-я строка - изначальный массив): \n");
        printFirstArray(arr);
        for (int i = arr.size() - 1; i > 0; i--) {  // Внешний цикл по всем элементам с последнего по порядку (кроме первого в массиве)
            int maxArr = arr.get(i);    // Задаём максимальное значение первым элементом цикла
            int keyMax = i;     // Индекс максимального числа в массиве

            for (int j = i; j >= 0; j--) {  // Внутренний цикл по всем элементам кроме уже пройденных внешним циклом (то есть цикл по неотсортированной части)
                if (arr.get(j) > maxArr) {  // Находим максимальный элемент во внутреннем цикле
                    maxArr = arr.get(j);
                    keyMax = j;
                }
            }
            Collections.swap(arr, arr.indexOf(arr.get(i)), arr.indexOf(arr.get(keyMax)));   // Меняем значение текущего элемента с максимальным
            for (int element: arr) {
                System.out.print(element + " ");
            }
            System.out.println();
        }
    }
}
