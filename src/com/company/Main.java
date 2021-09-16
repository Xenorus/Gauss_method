package com.company;

public class Main {

    public static void main(String[] args) {
    	int a = 4;
		double[][] array = new double[a][a];

		for (int i = 0; i<a; i++) {
	    	for (int b = i; b>=0; b--) {
	        	//System.out.print(i + "," + j + " | ");
				gauss_method(i, b, array, a);
	    	}
			System.out.println(" ");
    	}

    }

    public static void gauss_method(int i, int b, double[][] array, int lengthOfArray) {
    	int line; //строка
		int column = 0; //столбец
		double difference; //здесь храним то, сколько не хватает ячейке, чтобы стать нулем
		double multiplier; //множитель для строки, который мы используем для того,
		// чтобы получить в нужной ячейке f
		double[] stroke = new double[lengthOfArray]; //массив для копирования ОДНОЙ строки массива

    	if (i==0 && b==0) { //первая (нулевая) строка - особенная, поэтому для нее мы
    		//введем исключение
    		line = 1; //номер строки, которую будем вычитать
			//column еще при инициализации равен 0, так что эту переменную
			// здесь можно и не упоминать
		}
    	else {
    		line = i-1; //берем предыдущую строку (где у нас уже точно есть 0)
			//коэффициэнты, конечно, будут отвратительными, зато у нас не будет проблем
			//с тем, что на уже обработанной строке внезапно появится какое-то число там, где
			//должен быть 0 (проверка и повторные проходы по матрице
			//увеличат количество циклов в программе, и, соответственно,
			//временную сложность алгоритма)
    		column = b; //и берем тот же самый столбец
		}

		difference = finding_the_missing(i, b, array);

		//заполняем массив-копию строки, которую мы будем вычитать
		//если мы просто проведем все операции над основным массивом array, то
		//он изменится, и мы получим плохие коэффициэнты
		// (то есть, ЕЩЕ БОЛЕЕ плохие, чем у нас будут по итогу, лол)
		System.arraycopy(array[line], 0, stroke, 0, lengthOfArray);

		multiplier = difference/(array[line][column]); //получили множитель
		subtraction_of_lines(array, line, stroke, multiplier); //получили ноль на необходимом
		// месте в массиве
	}

	public static double finding_the_missing (int i, int b, double[][] array) {

    	//i, b - номер ячейки, для которой мы ищем, сколько нам нужно вычесть
		double difference = 0; //то, сколько не хватает ячейке для того, чтобы стать нулем

		//array[i][b] - ячейка, которую мы превращаем в 0
		if (array[i][b] != 0) { //если ячейка уже не равна нулю, то

			//по-факту, это реализация простейшей системы уравнений с одним неизвестным
			//f - array[i][b] = 0
			//если array[i][b] > 0, то f = 0 - array[i][b]
			if (array[i][b]>0) {
				difference = 0 - array[i][b];
			}
			//если array[i][b] < 0, то f = 0 + |array[i][b]|
			else {
				difference = Math.abs(array[i][b]);
			}

		}
		return difference; //мы нашли, сколько нужно нашей ячейке для того, чтобы стать нулем,
		//и возвращаем это число
	}

	public static void subtraction_of_lines(double[][] array, int line, double[] massiv, double multiplier) {
    	for (int n = 0; n<massiv.length; n++) {
    		array[line][n] = array[line][n] - (massiv[n]*multiplier);
		}
	}
}