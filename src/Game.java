import java.util.Scanner;

public class Game {
    int dimension = 3;
    int arr[];

    public Game() {
        this.arr = new int[9];
    }

    public int startGame() {
        drawCanvas();
        int index;
        boolean isX = true;
        int countSteps = 0;
        for (int i = 0; i < arr.length; i++) {
            countSteps++;
            index = inputFromConsole(isX);
            if (countSteps >= 5 && isGameOver(index, isX)) {
                if (isX) {
                    System.out.println("Игрок Х выиграл");
                    break;
                } else {
                    System.out.println("Игрок О выиграл");
                    break;
                }


            }
            if (countSteps == arr.length) {
                System.out.println("Ничья");
                break;
            }

            isX = !isX;
        }

//        if (countSteps == 9) {
//            System.out.println("Ничья");
//        }
        return countSteps;

    }

    private void drawCanvas() {
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i + 1;
//
//
//        }
        String a;
//        arr[4] = 1;
//        arr[6] = 2;
        for (int i = 0; i < arr.length; i++) {
            switch (arr[i]) {
                case 1:
                    a = "X";
                    break;
                case 2:
                    a = "O";
                    break;
                default:
                    a = String.valueOf((i + 1));
                    break;


            }
            if ((i + 1) % dimension == 0) {
                if (i == 8) {
                    System.out.print(" " + a + "  ");
                } else {
                    System.out.print("_" + a + "_");
                }

                System.out.println();


            } else if (i > 5) {
                System.out.print(" " + a + " |");

            } else {
                System.out.print("_" + a + "_" + "|");
            }

        }


    }

    private int inputFromConsole(boolean isX) {
        Scanner number = new Scanner(System.in);
        if (isX == true) {
            System.out.println("_____Ход игрока Х______");

        } else {
            System.out.println("_____Ход игрока О_____");
        }
//    int player = number.nextInt();
        int index = 999999;
        boolean isNotCorrect = true;
        do {
            try {
                String input = number.nextLine();
                int player = Integer.parseInt(input); // Парсер числа из строки (метод меняет тип)
                if (player > 9 || player < 0) {
                    System.out.println("Данное число вне диапозона");
                    isNotCorrect = true;
                } else if (arr[player - 1] != 0) {
                    System.out.println("Ячейка занята");
                    isNotCorrect = true;
                } else {
                    index = player - 1;
                    if (isX) {
                        arr[index] = 1;
                        drawCanvas();
                    } else {
//            System.out.println("_____Ход игрока О_____");
                        arr[index] = 2;
                        drawCanvas();
                    }
                    isNotCorrect = false;
                }
            } catch (Exception ex) {
                System.out.println("Введен неверный символ\n" + "Попробуйте еще раз!");
                isNotCorrect = true;
            }
        } while (isNotCorrect);


        return index;
    }

    private boolean isGameOver(int index, boolean isX) {
        int rowNum = index / dimension;
        int columnNum = index % dimension;
        boolean winFlag = true;
        boolean mainDiagonal = rowNum == columnNum;
        boolean secondaryDiagonal = columnNum + rowNum == dimension -1;
        for (int i = rowNum * dimension; i <= rowNum * dimension + 2; i++) {
            if (arr[i] != 1 && isX || arr[i] != 2 && !isX) {
                winFlag = false;
                break;
            }

        }
        if (winFlag) {
            return winFlag;
        } else {
            winFlag = !winFlag;
        }
        for (int i = columnNum; i <= columnNum + dimension * (dimension - 1); i = i + 3) {
            if (arr[i] != 1 && isX || arr[i] != 2 && !isX) {
                winFlag = false;
                break;
            }

        }
        if (winFlag) {
            return winFlag;
        } else {
            winFlag = !winFlag;
        }

////        diagonal = index % (dimension + 1) == 0;
//        for (int i = 0; i < arr.length; i = i + 4) {
//            if (arr[i] != 1 && isX || arr[i] != 2 && !isX) {
//                winFlag = false;
//                break;
//            }
//
//        }
//        if (winFlag) {
//            return winFlag;
//        } else {
//            winFlag = !winFlag;
//        }
//
//        for (int i = 2; i <= 6; i = i + 2) {
//            if (arr[i] != 1 && isX || arr[i] != 2 && !isX) {
//                winFlag = false;
//                break;
//            }
//
//        }
//
//
////        требование проверка на побочную диагональ должна выполняться только в том случае
////        если индекс лежит на побочной диагонали, как в примере выше
//
//        return winFlag;


        if (mainDiagonal) {
            for (int i = 0; i < arr.length; i += dimension + 1) {
                if ((isX && arr[i] != 1) || (!isX && arr[i] != 2)) {
                    winFlag = false;
                    break;
                }
            }
            if (winFlag) {
                return true;
            } else winFlag = true;
        }

        //secondaryDiag
        if (secondaryDiagonal) {
            for (int i = dimension - 1; i <= dimension * (dimension - 1); i += dimension - 1) {
                if ((isX && arr[i] != 1) || (!isX && arr[i] != 2)) {
                    winFlag = false;
                    break;
                }
            }
            if (winFlag) {
                return true;
            }
        }
        return false;
    }
}







